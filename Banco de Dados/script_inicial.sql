-- Role: vetfaunasistema

-- DROP ROLE vetfaunasistema;
/*
CREATE ROLE vetfaunasistema LOGIN
  ENCRYPTED PASSWORD 'md5062b3301e7f3e017bbcdb9d22e19158a'
  SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
  */
CREATE ROLE vetfaunasistema LOGIN 
ENCRYPTED PASSWORD 'md55949b3088829d429bba8ac82dbc4f5e9'
  SUPERUSER CREATEDB CREATEROLE REPLICATION
   VALID UNTIL 'infinity';
COMMENT ON ROLE vetfaunasistema IS 'Usuário master do vetfaunasistema.';


CREATE DATABASE vetfaunasistema
  WITH OWNER = vetfaunasistema
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;
GRANT ALL ON DATABASE vetfaunasistema TO vetfaunasistema;


COMMENT ON DATABASE vetfaunasistema
  IS 'Sistema para clinica veterinaria e petshop.';
--REVOKE ALL ON DATABASE vetfaunasistema FROM public;

-- Schema: vetfaunasistema

-- DROP SCHEMA vetfaunasistema;

CREATE SCHEMA vetfaunasistema
  AUTHORIZATION vetfaunasistema;





create table vetfaunasistema.cargo(
	id_cargo serial  PRIMARY KEY,
	descricao varchar(150) not null,
	data_exclusao date,
	versao integer DEFAULT (0) not null,
	CONSTRAINT unique_cargo UNIQUE(descricao) 
);

COMMENT ON TABLE vetfaunasistema.cargo IS 'Tabela responsavel por conter todos os funcionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.cargo.id_cargo IS 'Coluna responsavel por conter o id do cargo.';
COMMENT ON COLUMN vetfaunasistema.cargo.descricao IS 'Coluna responsavel por conter descricao do cargo.';
COMMENT ON COLUMN vetfaunasistema.cargo.versao IS 'Coluna responsavel por conter a versao do determinado registro.';
DROP TRIGGER trg_cargo_u ON vetfaunasistema.cargo;
drop function vetfaunasistema.fnc_cargo_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cargo_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        select count(1)
          into valor
          from cargo
         where upper(descricao) = upper(new.descricao) ; 
          
        if valor > 0 and upper(new.descricao) <> upper(old.descricao) then
            RAISE EXCEPTION ''VETFAUNAERROR:Este cargo já existe.'';
        END IF; 
        
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF;  
        return new;
    end;';


CREATE TRIGGER trg_cargo_u before update 
ON  vetfaunasistema.cargo FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_cargo_u();


DROP TRIGGER trg_cargo_i ON vetfaunasistema.cargo;
drop function vetfaunasistema.fnc_cargo_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cargo_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        new.versao := 0;
       select ((select count(1)
                  from cargo
                 where upper(descricao) = upper(new.descricao)) +
               (select count(1)
                  from cargo
                 where upper(descricao) = upper(new.descricao)
                   and data_exclusao is null))
          into valor;
          
        if valor = 1  then
            update cargo
               set data_exclusao = null
             where upper(descricao) = upper(new.descricao);
             return old;
        elsif valor =2 then
            RAISE EXCEPTION ''VETFAUNAERROR:Este cargo já existe.'';
        END IF;         
        return new;
    end;';

CREATE TRIGGER trg_cargo_i before insert 
ON  vetfaunasistema.cargo FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_cargo_i();

DROP TRIGGER trg_cargo_d ON vetfaunasistema.cargo;
drop function vetfaunasistema.fnc_cargo_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cargo_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin  
			select count(1)
			into valor
			from funcionario
			where id_cargo = old.id_cargo
			and coalesce(data_exclusao,CURRENT_DATE+1) > CURRENT_DATE;
			if valor >= 1 then
				RAISE EXCEPTION ''VETFAUNAERROR:Exclusão cancelada. Cargo em uso.'';
			end if;
			return old;
    end;';

CREATE TRIGGER trg_cargo_d before delete 
ON  vetfaunasistema.cargo FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_cargo_d();	
-------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.funcionario(
	id_funcionario integer  PRIMARY KEY,
	id_cargo integer references vetfaunasistema.cargo(id_cargo)not null,
	nome varchar(150) not null,
	pis varchar(11),
	carteira_trabalho varchar(20),
	rg varchar(20),
	cpf varchar(11),
	salario numeric(7,2) not null,
	data_nascimento date not null,
	data_contratacao date not null,
	dia_pagamento integer not null,
	data_exclusao date,
        versao integer DEFAULT (0),
        CONSTRAINT unique_cpf UNIQUE(cpf) 
);

COMMENT ON TABLE vetfaunasistema.Funcionario IS 'Tabela responsavel por conter todos os funcionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.id_funcionario IS 'Coluna responsavel por conter o id do funcionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.nome IS 'Coluna responsavel por conter o nome completo do funcionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.pis IS 'Coluna responsavel por conter dados sobre o pis do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.carteira_trabalho IS 'Coluna responsavel por conter o numero da carteira de trabalho do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.rg IS 'Coluna responsavel por conter o numero do rg do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.cpf IS 'Coluna responsavel por conter o numero do cpf do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.salario IS 'Coluna responsavel por conter o valor do salario do funcionario.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.data_contratacao IS 'Coluna responsavel por conter a data da contratacao do funcionario.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.data_contratacao IS 'Coluna responsavel por conter a data de nascimento do funcionario.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.dia_pagamento IS 'Coluna responsavel por conter a data do pagamento do funcionario.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.data_exclusao IS 'Coluna responsavel por conter a data o funcionario foi excluído.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

drop sequence vetfaunasistema.id_funcionario; 
create sequence vetfaunasistema.id_funcionario start 1; 

DROP TRIGGER trg_funcionario_i ON vetfaunasistema.funcionario;
drop function vetfaunasistema.fnc_funcionario_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_funcionario_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'begin 
        new.versao := 0;
        return new;
    end;';

CREATE TRIGGER trg_funcionario_i before insert 
ON  vetfaunasistema.funcionario FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_funcionario_i();

DROP TRIGGER trg_funcionario_u ON vetfaunasistema.funcionario;
drop function vetfaunasistema.fnc_funcionario_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_funcionario_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_funcionario_u before update 
ON  vetfaunasistema.funcionario FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_funcionario_u();

DROP TRIGGER trg_funcionario_d ON vetfaunasistema.funcionario;
drop function vetfaunasistema.fnc_funcionario_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_funcionario_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     begin 
            update funcionario
               set data_exclusao = CURRENT_DATE
             where id_funcionario = old.id_funcionario;
             return old;
    end;';

CREATE TRIGGER trg_funcionario_d before delete 
ON  vetfaunasistema.funcionario FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_funcionario_d();	

-------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.tipo_telefone_contato(
	id_tipo_telefone_contato serial  PRIMARY KEY,
	descricao varchar(150) not null,
    versao integer DEFAULT (0)
);

COMMENT ON TABLE vetfaunasistema.tipo_telefone_contato IS 'Tabela responsavel por conter os tipos de contato de telefone ex.: Telefone residencial, celular...';
COMMENT ON COLUMN vetfaunasistema.tipo_telefone_contato.id_tipo_telefone_contato IS 'Coluna responsavel por conter o id do tipo de contato.';
COMMENT ON COLUMN vetfaunasistema.tipo_telefone_contato.descricao IS 'Coluna responsavel por conter descricao do tipo de contato.';
COMMENT ON COLUMN vetfaunasistema.tipo_telefone_contato.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

insert into vetfaunasistema.tipo_telefone_contato (descricao) values('Celular');
insert into vetfaunasistema.tipo_telefone_contato (descricao) values('Fax');
insert into vetfaunasistema.tipo_telefone_contato (descricao) values('Fixo Residencial');
insert into vetfaunasistema.tipo_telefone_contato (descricao) values('Fixo Trabalho');
commit;
-------------------------------------------------------------------------------------------------------------------------------------------

create table vetfaunasistema.telefone_contato(
	id_telefone_contato integer  PRIMARY KEY,
	id_tipo_telefone_contato integer references vetfaunasistema.tipo_telefone_contato(id_tipo_telefone_contato) not null,
	ddd varchar(2) not null,
	numero varchar(9) not null,
        versao integer DEFAULT (0)
);

COMMENT ON TABLE vetfaunasistema.telefone_contato IS 'Tabela responsavel por conter todos os telefones de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.id_telefone_contato IS 'Coluna responsavel por conter o id do telefone de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.id_tipo_telefone_contato IS 'Coluna responsavel por conter o id do tipo de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.ddd IS 'Coluna responsavel por conter o ddd do contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.numero IS 'Coluna responsavel por conter numero de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

drop sequence vetfaunasistema.id_telefone_contato;
CREATE sequence vetfaunasistema.id_telefone_contato START 1;


-------------------------------------------------------------------------------------------------------------------------------------------

create table vetfaunasistema.telefone_funcionario(
	id_funcionario integer references vetfaunasistema.funcionario(id_funcionario) not null,
	id_telefone_contato integer references vetfaunasistema.telefone_contato(id_telefone_contato) not null,
        CONSTRAINT unique_telefone_funcionario UNIQUE(id_funcionario,id_telefone_contato) 
);

COMMENT ON TABLE vetfaunasistema.telefone_funcionario IS 'Tabela responsavel por conter todos os telefones de funcionarios.';
COMMENT ON COLUMN vetfaunasistema.telefone_funcionario.id_funcionario IS 'Coluna responsavel por conter o id do funcionario.';
COMMENT ON COLUMN vetfaunasistema.telefone_funcionario.id_telefone_contato IS 'Coluna responsavel por conter o id do contato.';
--------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.fornecedor(
	id_fornecedor integer  PRIMARY KEY,
	nome varchar(150) not null,
        data_exclusao date,
        versao integer DEFAULT (0)
);

COMMENT ON TABLE vetfaunasistema.Fornecedor IS 'Tabela responsavel por conter todos os funcionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Fornecedor.id_Fornecedor IS 'Coluna responsavel por conter o id do Fornecedores da empresa.';
COMMENT ON COLUMN vetfaunasistema.Fornecedor.nome IS 'Coluna responsavel por conter o nome completo do Fornecedores da empresa.';
COMMENT ON COLUMN vetfaunasistema.Fornecedor.versao IS 'Coluna responsavel por conter a versao do determinado registro.';
COMMENT ON COLUMN vetfaunasistema.Fornecedor.data_exclusao IS 'Coluna responsavel por conter a data o Fornecedor foi excluído.';

drop sequence vetfaunasistema.id_Fornecedor; 
create sequence vetfaunasistema.id_Fornecedor start 1; 

DROP TRIGGER trg_Fornecedor_i ON vetfaunasistema.Fornecedor;
drop function vetfaunasistema.fnc_Fornecedor_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_Fornecedor_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'begin 
        new.versao := 0;
        return new;
    end;';

CREATE TRIGGER trg_Fornecedor_i before insert 
ON  vetfaunasistema.Fornecedor FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_Fornecedor_i();

DROP TRIGGER trg_Fornecedor_u ON vetfaunasistema.Fornecedor;
drop function vetfaunasistema.fnc_Fornecedor_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_Fornecedor_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_Fornecedor_u before update 
ON  vetfaunasistema.Fornecedor FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_Fornecedor_u();

DROP TRIGGER trg_Fornecedor_d ON vetfaunasistema.Fornecedor;
drop function vetfaunasistema.fnc_Fornecedor_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_Fornecedor_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
		valor integer;
	 begin 
			select count(1)
			into valor
			from vendedor
			where id_fornecedor = old.id_fornecedor
			and coalesce(data_exclusao,CURRENT_DATE+1) > CURRENT_DATE;
			if valor >=1 then
				RAISE EXCEPTION ''VETFAUNAERROR:Exclusão cancelada. Fornecedor em uso.'';
			end if;  
			return old;
    end;';

CREATE TRIGGER trg_Fornecedor_d before delete 
ON  vetfaunasistema.Fornecedor FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_Fornecedor_d();	

-------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.Vendedor(
	id_Vendedor integer  PRIMARY KEY,
        id_fornecedor integer references vetfaunasistema.fornecedor(id_fornecedor) not null,
	nome varchar(150) not null,
        data_exclusao date,
        versao integer DEFAULT (0)
);

COMMENT ON TABLE vetfaunasistema.Vendedor IS 'Tabela responsavel por conter todos os funcionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Vendedor.id_Vendedor IS 'Coluna responsavel por conter o id do Vendedores da empresa.';
COMMENT ON COLUMN vetfaunasistema.Vendedor.id_fornecedor IS 'Coluna responsavel por conter o id do fornecedor.';
COMMENT ON COLUMN vetfaunasistema.Vendedor.nome IS 'Coluna responsavel por conter o nome completo do Vendedores da empresa.';
COMMENT ON COLUMN vetfaunasistema.Vendedor.versao IS 'Coluna responsavel por conter a versao do determinado registro.';
COMMENT ON COLUMN vetfaunasistema.Vendedor.data_exclusao IS 'Coluna responsavel por conter a data o Vendedor foi excluído.';

drop sequence vetfaunasistema.id_Vendedor; 
create sequence vetfaunasistema.id_Vendedor start 1; 

DROP TRIGGER trg_Vendedor_i ON vetfaunasistema.Vendedor;
drop function vetfaunasistema.fnc_Vendedor_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_Vendedor_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'begin 
        new.versao := 0;
        return new;
    end;';

CREATE TRIGGER trg_Vendedor_i before insert 
ON  vetfaunasistema.Vendedor FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_Vendedor_i();

DROP TRIGGER trg_Vendedor_u ON vetfaunasistema.Vendedor;
drop function vetfaunasistema.fnc_Vendedor_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_Vendedor_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_Vendedor_u before update 
ON  vetfaunasistema.Vendedor FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_Vendedor_u();

DROP TRIGGER trg_Vendedor_d ON vetfaunasistema.Vendedor;
drop function vetfaunasistema.fnc_Vendedor_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_Vendedor_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     begin 
            update Vendedor
               set data_exclusao = CURRENT_DATE
             where id_Vendedor = old.id_Vendedor;
             return old;
    end;';

CREATE TRIGGER trg_Vendedor_d before delete 
ON  vetfaunasistema.Vendedor FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_Vendedor_d();	

-------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.telefone_Vendedor(
	id_Vendedor integer references vetfaunasistema.Vendedor(id_Vendedor) not null,
	id_telefone_contato integer references vetfaunasistema.telefone_contato(id_telefone_contato) not null,
        CONSTRAINT unique_telefone_Vendedor UNIQUE(id_Vendedor,id_telefone_contato) 
);

COMMENT ON TABLE vetfaunasistema.telefone_Vendedor IS 'Tabela responsavel por conter todos os telefones de Vendedores.';
COMMENT ON COLUMN vetfaunasistema.telefone_Vendedor.id_Vendedor IS 'Coluna responsavel por conter o id do Vendedor.';
COMMENT ON COLUMN vetfaunasistema.telefone_Vendedor.id_telefone_contato IS 'Coluna responsavel por conter o id do contato.';
--------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.telefone_fornecedor(
	id_fornecedor integer references vetfaunasistema.fornecedor(id_fornecedor) not null,
	id_telefone_contato integer references vetfaunasistema.telefone_contato(id_telefone_contato) not null,
        CONSTRAINT unique_telefone_fornecedor UNIQUE(id_fornecedor,id_telefone_contato) 
);

COMMENT ON TABLE vetfaunasistema.telefone_fornecedor IS 'Tabela responsavel por conter todos os telefones de fornecedores.';
COMMENT ON COLUMN vetfaunasistema.telefone_fornecedor.id_fornecedor IS 'Coluna responsavel por conter o id do fornecedor.';
COMMENT ON COLUMN vetfaunasistema.telefone_fornecedor.id_telefone_contato IS 'Coluna responsavel por conter o id do contato.';
---------------------------------------------------------------------------------------------------------------------------------------------

create table vetfaunasistema.categoria_produto(
	id_categoria_produto serial PRIMARY KEY,
	descricao varchar(50) not null,
        data_exclusao date
);

COMMENT ON TABLE vetfaunasistema.categoria_produto IS 'Tabela responsavel por conter as categorias dos produtos.';
COMMENT ON COLUMN vetfaunasistema.categoria_produto.id_categoria_produto IS 'Coluna responsavel por conter o id da categoria do produto.';
COMMENT ON COLUMN vetfaunasistema.categoria_produto.descricao IS 'Coluna responsavel por conter o descrição da categoria.';
COMMENT ON COLUMN vetfaunasistema.categoria_produto.data_exclusao IS 'Coluna responsavel por conter o id do contato.';

DROP TRIGGER trg_categoria_produto_d ON vetfaunasistema.categoria_produto;
drop function vetfaunasistema.fnc_categoria_produto_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_categoria_produto_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
		valor integer;
     begin 
			select count(1)
			into valor
			from marca_produto
			where id_categoria_produto = old.id_categoria_produto
			and coalesce(data_exclusao,CURRENT_DATE+1) > CURRENT_DATE;
			
			if valor >=1 then
				RAISE EXCEPTION ''VETFAUNAERROR:Exclusão cancelada. Categoria em uso.'';
			end if;  
			return old;        
    end;';

CREATE TRIGGER trg_categoria_produto_d before delete 
ON  vetfaunasistema.categoria_produto FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_categoria_produto_d();
------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.marca_produto(
	id_marca_produto serial PRIMARY KEY,
	id_categoria_produto integer references vetfaunasistema.categoria_produto(id_categoria_produto) not null,
	descricao varchar(50) not null,
        data_exclusao date
);

COMMENT ON TABLE vetfaunasistema.marca_produto IS 'Tabela responsavel por conter as marcas de produtos.';
COMMENT ON COLUMN vetfaunasistema.marca_produto.id_marca_produto IS 'Coluna responsavel por conter o id da marca_produto.';
COMMENT ON COLUMN vetfaunasistema.marca_produto.id_categoria_produto IS 'Coluna responsavel por conter o id da categoria do produto.';
COMMENT ON COLUMN vetfaunasistema.marca_produto.descricao IS 'Coluna responsavel por conter o descrição de um produto.';
COMMENT ON COLUMN vetfaunasistema.marca_produto.data_exclusao IS 'Coluna responsavel por conter o id do contato.';

DROP TRIGGER trg_marca_produto_d ON vetfaunasistema.marca_produto;
drop function vetfaunasistema.fnc_marca_produto_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_marca_produto_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
    valor integer;
     begin 
			select count(1)
			into valor
			from produto
			where id_marca_produto = old.id_marca_produto
			and coalesce(data_exclusao,CURRENT_DATE+1) > CURRENT_DATE;
			if valor >=1 then
				RAISE EXCEPTION ''VETFAUNAERROR:Exclusão cancelada. Marca em uso.'';
			end if;  
			return old;      
    end;';

CREATE TRIGGER trg_marca_produto_d before delete 
ON  vetfaunasistema.marca_produto FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_marca_produto_d();	
------------------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.unidade_medida(
	id_unidade_medida serial PRIMARY KEY,
        abreviacao varchar(3) not null,
	descricao varchar(20) not null,
        data_exclusao date
);

COMMENT ON TABLE vetfaunasistema.unidade_medida IS 'Tabela responsavel por conter as unidade de medidas dos produtos.';
COMMENT ON COLUMN vetfaunasistema.unidade_medida.id_unidade_medida IS 'Coluna responsavel por conter o id da unidade de medida do produto.';
COMMENT ON COLUMN vetfaunasistema.unidade_medida.abreviacao IS 'Coluna responsavel por conter a abreviacao da unidade de medida.';
COMMENT ON COLUMN vetfaunasistema.unidade_medida.descricao IS 'Coluna responsavel por conter o descrição da unidade de medida.';
COMMENT ON COLUMN vetfaunasistema.unidade_medida.data_exclusao IS 'Coluna responsavel por conter o id do contato.';

DROP TRIGGER trg_unidade_medida_d ON vetfaunasistema.unidade_medida;
drop function vetfaunasistema.fnc_unidade_medida_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_unidade_medida_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     begin 
            update unidade_medida
               set data_exclusao = CURRENT_DATE
             where id_unidade_medida = old.id_unidade_medida;
             return old;
    end;';

CREATE TRIGGER trg_unidade_medida_d before delete 
ON  vetfaunasistema.unidade_medida FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_unidade_medida_d();

insert into vetfaunasistema.unidade_medida (abreviacao,descricao)values('Kg','Quilograma');
insert into vetfaunasistema.unidade_medida (abreviacao,descricao)values('g','Grama');
insert into vetfaunasistema.unidade_medida (abreviacao,descricao)values('L','Litro');
insert into vetfaunasistema.unidade_medida (abreviacao,descricao)values('ml','Mililitro');
insert into vetfaunasistema.unidade_medida (abreviacao,descricao)values('Uni','Unidade');
commit;
-------------------------------------------------------------------------------------------------------------------------------------------
	create table vetfaunasistema.produto(
		id_produto integer  PRIMARY KEY,
			--id_categoria_produto integer references vetfaunasistema.categoria_produto(id_categoria_produto)not null,
			id_marca_produto integer references vetfaunasistema.marca_produto(id_marca_produto)not null,
			id_unidade_medida integer references vetfaunasistema.unidade_medida(id_unidade_medida)not null,
			codigo_barra varchar(100),
			nome varchar(60) not null,
		valor_medida numeric(5,2) not null,
			preco numeric(6,2),
		quantidade_minima integer,
		antecedencia_vencimento integer,
		data_exclusao date,
			versao integer DEFAULT (0)
	);

	COMMENT ON TABLE vetfaunasistema.Produto IS 'Tabela responsavel por conter todos os produtos da empresa.';
	COMMENT ON COLUMN vetfaunasistema.Produto.id_produto IS 'Coluna responsavel por conter o id do produtos da empresa.';
	--COMMENT ON COLUMN vetfaunasistema.Produto.id_categoria_produto IS 'Coluna responsavel por conter o tipo do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.id_marca_produto IS 'Coluna responsavel por conter marca do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.id_unidade_medida IS 'Coluna responsavel por conter a unidade de medida do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.codigo_barra IS 'Coluna responsavel por conter o codigo de barra do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.nome IS 'Coluna responsavel por conter o nome do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.valor_medida IS 'Coluna responsavel por conter o valor da medida do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.quantidade_minima IS 'Coluna responsavel por conter quantidade minima do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.preco IS 'Coluna responsavel por conter o preco do produtos.';
	COMMENT ON COLUMN vetfaunasistema.Produto.antecedencia_vencimento IS 'Coluna responsavel por conter valor de quantos dias de antecedencia do vencimento do produtos o sistema deve informar.';
	COMMENT ON COLUMN vetfaunasistema.Produto.data_exclusao IS 'Coluna responsavel por conter a data o produto foi excluído.';
	COMMENT ON COLUMN vetfaunasistema.Produto.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

drop sequence vetfaunasistema.id_produto; 
create sequence vetfaunasistema.id_produto start 1; 

DROP TRIGGER trg_produto_i ON vetfaunasistema.produto;
drop function vetfaunasistema.fnc_produto_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_produto_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'begin 
        new.versao := 0;
        return new;
    end;';

CREATE TRIGGER trg_produto_i before insert 
ON  vetfaunasistema.produto FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_produto_i();

DROP TRIGGER trg_produto_u ON vetfaunasistema.produto;
drop function vetfaunasistema.fnc_produto_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_produto_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_produto_u before update 
ON  vetfaunasistema.produto FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_produto_u();

DROP TRIGGER trg_produto_d ON vetfaunasistema.produto;
drop function vetfaunasistema.fnc_produto_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_produto_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	valor integer;
     begin 
			select count(1)
			  into valor
			  from estoque e
			 where e.id_produto = old.id_produto
			   and e.quantidade > 0;
			if valor > 0 then
				RAISE EXCEPTION ''VETFAUNAERROR:Exclusão cancelada. Produto em estoque.'';
			end if;
            update produto
               set data_exclusao = CURRENT_DATE
             where id_produto = old.id_produto;
             return old;
    end;';

CREATE TRIGGER trg_produto_d before delete 
ON  vetfaunasistema.produto FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_produto_d();	
-------------------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.estoque(
		id_estoque serial  PRIMARY KEY,
		id_produto integer references vetfaunasistema.produto(id_produto)not null,
		data_validade date,
		quantidade integer not null,		
                preco_compra numeric(6,2),
		data_zerou date,
		versao integer DEFAULT (0)
	);

	COMMENT ON TABLE vetfaunasistema.estoque IS 'Tabela responsavel por conter todos os estoques da empresa.';
	COMMENT ON COLUMN vetfaunasistema.estoque.id_estoque IS 'Coluna responsavel por conter o id do estoques da empresa.';
	COMMENT ON COLUMN vetfaunasistema.estoque.id_produto IS 'Coluna responsavel por conter o produto em estoques.';
	COMMENT ON COLUMN vetfaunasistema.estoque.data_validade IS 'Coluna responsavel por conter a data de validade do produto.';
	COMMENT ON COLUMN vetfaunasistema.estoque.quantidade IS 'Coluna responsavel por conter a quantidade em estoques do produto.';
	COMMENT ON COLUMN vetfaunasistema.estoque.preco_compra IS 'Coluna responsavel por conter o preco de compra do produto.';
	COMMENT ON COLUMN vetfaunasistema.estoque.data_zerou IS 'Coluna responsavel por conter a data que o produto zerou no estoques.';
	COMMENT ON COLUMN vetfaunasistema.estoque.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

DROP TRIGGER trg_estoque_i ON vetfaunasistema.estoque;
drop function vetfaunasistema.fnc_estoque_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_estoque_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	valor vetfaunasistema.estoque.quantidade%TYPE;
        id_estoque_var vetfaunasistema.estoque.id_estoque%TYPE;
	begin 
		select e.quantidade,
		       e.id_estoque
		into valor,
	             id_estoque_var
		from estoque e
		where CASE 
                      WHEN new.data_validade is null THEN 
                           e.data_validade is null
                      else
                         e.data_validade = new.data_validade
                      end
                  and e.id_produto = new.id_produto    
		  and e.preco_compra = new.preco_compra		  
                  and e.data_zerou is null;
		if(valor is not null) then
			update estoque
			   set quantidade = (new.quantidade+valor)
			 where id_estoque = id_estoque_var;
                        
                         return old;
		end if;
                insert into entrada_estoque(id_estoque,data_entrada)values(new.id_estoque,CURRENT_DATE);
                new.versao := 0;
                return new;
    end;';

CREATE TRIGGER trg_estoque_i before insert 
ON  vetfaunasistema.estoque FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_estoque_i();

DROP TRIGGER trg_estoque_u ON vetfaunasistema.estoque;
drop function vetfaunasistema.fnc_estoque_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_estoque_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin
        if new.quantidade = 0 then
                new.data_zerou = CURRENT_DATE;
        END IF;
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_estoque_u before update 
ON  vetfaunasistema.estoque FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_estoque_u();

DROP TRIGGER trg_estoque_d ON vetfaunasistema.estoque;
drop function vetfaunasistema.fnc_estoque_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_estoque_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     begin 
            update estoque
               set quantidade = 0
             where id_estoque = old.id_estoque;
             return old;
    end;';

CREATE TRIGGER trg_estoque_d before delete 
ON  vetfaunasistema.estoque FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_estoque_d();	

---------------------------------------------------------------------------------------------------
create table vetfaunasistema.entrada_estoque(
		id_entrada_estoque serial  PRIMARY KEY,
		id_estoque integer not null,
		data_entrada date not null
	);

	COMMENT ON TABLE vetfaunasistema.entrada_estoque IS 'Tabela responsavel por conter a data que o produto entrou no estoques.';
	COMMENT ON COLUMN vetfaunasistema.entrada_estoque.data_entrada IS 'Coluna responsavel por conter a data que o produto entrou no estoques.';
	
---------------------------------------------------------------------------------------------------
create table vetfaunasistema.tipo_logradouro(
		id_tipo_logradouro serial  PRIMARY KEY,
		descricao varchar(60) not null,
		versao integer DEFAULT (0)
	);

	COMMENT ON TABLE vetfaunasistema.tipo_logradouro IS 'Tabela responsavel por conter todos os tipo_logradouros da empresa.';
	COMMENT ON COLUMN vetfaunasistema.tipo_logradouro.id_tipo_logradouro IS 'Coluna responsavel por conter o id dos logradouros.';
	COMMENT ON COLUMN vetfaunasistema.tipo_logradouro.descricao IS 'Coluna responsavel por conter descrição dos logradouros.';
	COMMENT ON COLUMN vetfaunasistema.tipo_logradouro.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

insert into vetfaunasistema.tipo_logradouro (descricao) values('AEROPORTO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('ALAMEDA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('APARTAMENTO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('AVENIDA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('BECO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('BLOCO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('CAMINHO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('ESCADINHA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('ESTAÇÃO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('ESTRADA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('FAZENDA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('FORTALEZA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('GALERIA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('LADEIRA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('LARGO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('PRAÇA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('PARQUE');
insert into vetfaunasistema.tipo_logradouro (descricao) values('PRAIA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('QUADRA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('QUILÔMETRO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('QUINTA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('RODOVIA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('RUA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('SUPER QUADRA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('TRAVESSA');
insert into vetfaunasistema.tipo_logradouro (descricao) values('VIADUTO');
insert into vetfaunasistema.tipo_logradouro (descricao) values('VILA');
commit;
-------------------------------------------------------------------------------------------------------
create table vetfaunasistema.estado(
		id_estado serial  PRIMARY KEY,
		nome varchar(60) not null,
		abreviacao varchar(2) not null
	);

insert into vetfaunasistema.estado(nome,abreviacao)values('Acre','AC');
insert into vetfaunasistema.estado(nome,abreviacao)values('Alagoas','AL');
insert into vetfaunasistema.estado(nome,abreviacao)values('Amapá','AP');
insert into vetfaunasistema.estado(nome,abreviacao)values('Amazonas','AM');
insert into vetfaunasistema.estado(nome,abreviacao)values('Bahia','BA');
insert into vetfaunasistema.estado(nome,abreviacao)values('Ceará','CE');
insert into vetfaunasistema.estado(nome,abreviacao)values('Distrito Federal','DF');
insert into vetfaunasistema.estado(nome,abreviacao)values('Goiás','GO');
insert into vetfaunasistema.estado(nome,abreviacao)values('Espírito Santo','ES');
insert into vetfaunasistema.estado(nome,abreviacao)values('Maranhão','MA');
insert into vetfaunasistema.estado(nome,abreviacao)values('Mato Grosso','MT');
insert into vetfaunasistema.estado(nome,abreviacao)values('Mato Grosso do Sul','MS');
insert into vetfaunasistema.estado(nome,abreviacao)values('Minas Gerais','MG');
insert into vetfaunasistema.estado(nome,abreviacao)values('Pará','PA');
insert into vetfaunasistema.estado(nome,abreviacao)values('Paraiba','PB');
insert into vetfaunasistema.estado(nome,abreviacao)values('Paraná','PR'); 
insert into vetfaunasistema.estado(nome,abreviacao)values('Pernambuco','PE');
insert into vetfaunasistema.estado(nome,abreviacao)values('Piauí','PI');
insert into vetfaunasistema.estado(nome,abreviacao)values('Rio de Janeiro','RJ');
insert into vetfaunasistema.estado(nome,abreviacao)values('Rio Grande do Norte','RN');
insert into vetfaunasistema.estado(nome,abreviacao)values('Rio Grande do Sul','RS');
insert into vetfaunasistema.estado(nome,abreviacao)values('Rondônia','RO');
insert into vetfaunasistema.estado(nome,abreviacao)values('Rorâima','RR');
insert into vetfaunasistema.estado(nome,abreviacao)values('São Paulo','SP');
insert into vetfaunasistema.estado(nome,abreviacao)values('Santa Catarina','SC');
insert into vetfaunasistema.estado(nome,abreviacao)values('Sergipe','SE');
insert into vetfaunasistema.estado(nome,abreviacao)values('Tocantins','TO');
commit;
----------------------------------------------------------------------------------------------
create table vetfaunasistema.municipio(
		id_municipio serial  PRIMARY KEY,
		id_estado integer references vetfaunasistema.estado(id_estado)not null,
		nome varchar(60) not null
		);
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Abaiara');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Acarape');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Acaraú');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Acopiara');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Aiuaba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Alcântaras');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Altaneira');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Alto Santo');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Amontada');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Antonina do Norte');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Apuiarés');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Aquiraz');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Aracati');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Aracoiaba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ararendá');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Araripe');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Aratuba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Arneiroz');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Assaré');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Aurora');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Baixio');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Banabuiú');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Barbalha');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Barreira');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Barro');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Barroquinha');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Baturité');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Beberibe');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Bela Cruz');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Boa Viagem');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Brejo Santo');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Camocim');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Campos Sales');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Canindé');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Capistrano');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Caridade');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Cariré');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Caririaçu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Cariús');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Carnaubal');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Cascavel');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Catarina');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Catunda');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Caucaia');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Cedro');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Chaval');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Choró');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Chorozinho');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Coreaú');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Crateús');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Crato');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Croatá');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Cruz');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Deputado Irapuan Pinheiro');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ererê');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Eusébio');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Farias Brito');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Forquilha');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Fortaleza');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Fortim');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Frecheirinha');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'General Sampaio');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Graça');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Granja');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Granjeiro');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Groaíras');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Guaiúba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Guaraciaba do Norte');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Guaramiranga');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Hidrolândia');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Horizonte');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ibaretama');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ibiapina');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ibicuitinga');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Icapuí');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Icó');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Iguatu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Independência');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ipaporanga');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ipaumirim');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ipu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ipueiras');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Iracema');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Irauçuba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Itaiçaba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Itaitinga');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Itapagé');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Itapipoca');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Itapiúna');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Itarema');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Itatira');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jaguaretama');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jaguaribara');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jaguaribe');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jaguaruana');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jardim');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jati');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jijoca de Jericoacoara');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Juazeiro do Norte');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Jucás');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Lavras da Mangabeira');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Limoeiro do Norte');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Madalena');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Maracanaú');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Maranguape');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Marco');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Martinópole');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Massapê');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Mauriti');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Meruoca');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Milagres');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Milhã');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Miraíma');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Missão Velha');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Mombaça');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Monsenhor Tabosa');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Morada Nova');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Moraújo');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Morrinhos');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Mucambo');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Mulungu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Nova Olinda');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Nova Russas');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Novo Oriente');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ocara');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Orós');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pacajus');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pacatuba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pacoti');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pacujá');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Palhano');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Palmácia');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Paracuru');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Paraipaba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Parambu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Paramoti');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pedra Branca');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Penaforte');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pentecoste');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pereiro');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pindoretama');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Piquet Carneiro');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Pires Ferreira');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Poranga');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Porteiras');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Potengi');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Potiretama');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Quiterianópolis');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Quixadá');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Quixelô');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Quixeramobim');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Quixeré');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Redenção');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Reriutaba');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Russas');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Saboeiro');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Salitre');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Santa Quitéria');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Santana do Acaraú');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Santana do Cariri');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'São Benedito');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'São Gonçalo do Amarante');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'São João do Jaguaribe');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'São Luís do Curu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Senador Pompeu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Senador Sá');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Sobral');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Solonópole');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Tabuleiro do Norte');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Tamboril');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Tarrafas');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Tauá');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Tejuçuoca');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Tianguá');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Trairi');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Tururu');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Ubajara');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Umari');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Umirim');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Uruburetama');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Uruoca');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Varjota');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Várzea Alegre');                                                    
	insert into vetfaunasistema.municipio (id_estado,nome) values((select id_estado from vetfaunasistema.estado where abreviacao ='CE'),'Viçosa do Ceará');     
	commit;
---------------------------------------------------------------------------------------------------------------       
create table vetfaunasistema.endereco(
		id_endereco integer  PRIMARY KEY,
		id_tipo_logradouro integer references vetfaunasistema.tipo_logradouro(id_tipo_logradouro)not null,
		id_municipio integer references vetfaunasistema.municipio(id_municipio)not null,
		cep varchar(9)not null,
		bairro varchar(30)not null,		
                logradouro varchar(50)not null,
		numero varchar(5),
		
		complemento varchar(150),
		versao integer DEFAULT (0)
	);

	COMMENT ON TABLE vetfaunasistema.endereco IS 'Tabela responsavel por conter os enderecos dos clientes.';
	COMMENT ON COLUMN vetfaunasistema.endereco.id_endereco IS 'Coluna responsavel por conter o id do enderecos.';
	COMMENT ON COLUMN vetfaunasistema.endereco.id_tipo_logradouro IS 'Coluna responsavel por conter o tipo de logradouro.';
	COMMENT ON COLUMN vetfaunasistema.endereco.cep IS 'Coluna responsavel por conter cep.';
	COMMENT ON COLUMN vetfaunasistema.endereco.bairro IS 'Coluna responsavel por conter bairro.';
	COMMENT ON COLUMN vetfaunasistema.endereco.logradouro IS 'Coluna responsavel por conter o logradouro.';
	COMMENT ON COLUMN vetfaunasistema.endereco.numero IS 'Coluna responsavel por conter numero.';
	COMMENT ON COLUMN vetfaunasistema.endereco.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

	drop sequence vetfaunasistema.id_endereco; 
	create sequence vetfaunasistema.id_endereco start 1; 
	
DROP TRIGGER trg_endereco_i ON vetfaunasistema.endereco;
drop function vetfaunasistema.fnc_endereco_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_endereco_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	begin 
                new.versao := 0;
                return new;
    end;';

CREATE TRIGGER trg_endereco_i before insert 
ON  vetfaunasistema.endereco FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_endereco_i();

DROP TRIGGER trg_endereco_u ON vetfaunasistema.endereco;
drop function vetfaunasistema.fnc_endereco_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_endereco_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_endereco_u before update 
ON  vetfaunasistema.endereco FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_endereco_u();
	

---------------------------------------------------------------------------------------
create table vetfaunasistema.cliente(
		id_cliente integer  PRIMARY KEY,
		id_endereco integer references vetfaunasistema.endereco(id_endereco),
		nome varchar(60)not null,
		email varchar(60),
                data_exclusao date,
		versao integer DEFAULT (0)
	);

	COMMENT ON TABLE vetfaunasistema.cliente IS 'Tabela responsavel por conter os dados dos clientes.';
	COMMENT ON COLUMN vetfaunasistema.cliente.id_cliente IS 'Coluna responsavel por conter o id do clientes.';
	COMMENT ON COLUMN vetfaunasistema.cliente.id_endereco IS 'Coluna responsavel por conter o id do endereco.';
	COMMENT ON COLUMN vetfaunasistema.cliente.nome IS 'Coluna responsavel por conter nome.';
	COMMENT ON COLUMN vetfaunasistema.cliente.email IS 'Coluna responsavel por conter email do cliente.';
	COMMENT ON COLUMN vetfaunasistema.cliente.data_exclusao IS 'Coluna responsavel por conter a data que o cliente foi excluido.';
	COMMENT ON COLUMN vetfaunasistema.cliente.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

drop sequence vetfaunasistema.id_cliente; 
create sequence vetfaunasistema.id_cliente start 1; 	
	
DROP TRIGGER trg_cliente_i ON vetfaunasistema.cliente;
drop function vetfaunasistema.fnc_cliente_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cliente_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	begin 
                new.versao := 0;
                return new;
    end;';

CREATE TRIGGER trg_cliente_i before insert 
ON  vetfaunasistema.cliente FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_cliente_i();

DROP TRIGGER trg_cliente_u ON vetfaunasistema.cliente;
drop function vetfaunasistema.fnc_cliente_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cliente_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
		return new;
    end;';


CREATE TRIGGER trg_cliente_u before update 
ON  vetfaunasistema.cliente FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_cliente_u();

	DROP TRIGGER trg_cliente_after_u ON vetfaunasistema.cliente;
drop function vetfaunasistema.fnc_cliente_after_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cliente_after_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin
        if(new.id_endereco is null)then
		delete from vetfaunasistema.endereco where id_endereco = old.id_endereco;
	end if;
        return new;
    end;';


CREATE TRIGGER trg_cliente_after_u after update 
ON  vetfaunasistema.cliente FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_cliente_after_u();

DROP TRIGGER trg_cliente_d ON vetfaunasistema.cliente;
drop function vetfaunasistema.fnc_cliente_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cliente_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     begin 
            update cliente
               set data_exclusao = CURRENT_DATE
             where id_cliente = old.id_cliente;
             return old;
    end;';

CREATE TRIGGER trg_cliente_d before delete 
ON  vetfaunasistema.cliente FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_cliente_d();
---------------------------------------------------------------------------------
create table vetfaunasistema.telefone_cliente(
	id_cliente integer references vetfaunasistema.cliente(id_cliente) not null,
	id_telefone_contato integer references vetfaunasistema.telefone_contato(id_telefone_contato) not null,
        CONSTRAINT unique_telefone_cliente UNIQUE(id_cliente,id_telefone_contato) 
);

COMMENT ON TABLE vetfaunasistema.telefone_cliente IS 'Tabela responsavel por conter todos os telefones de clientes.';
COMMENT ON COLUMN vetfaunasistema.telefone_cliente.id_cliente IS 'Coluna responsavel por conter o id do cliente.';
COMMENT ON COLUMN vetfaunasistema.telefone_cliente.id_telefone_contato IS 'Coluna responsavel por conter o id do contato.';
----------------------------------------------------------------------------------
create table vetfaunasistema.especie(
	id_especie serial primary key,
	descricao varchar(20)not null
);

COMMENT ON TABLE vetfaunasistema.especie IS 'Tabela responsavel por conter todos os telefones de especies.';
COMMENT ON COLUMN vetfaunasistema.especie.id_especie IS 'Coluna responsavel por conter o id do especie.';
COMMENT ON COLUMN vetfaunasistema.especie.descricao IS 'Coluna responsavel por conter o descricao da especie.';

insert into vetfaunasistema.especie(descricao)values('Caninos');
insert into vetfaunasistema.especie(descricao)values('Felinos');
insert into vetfaunasistema.especie(descricao)values('Outros');
commit;
------------------------------------------------------------------------------------

create table vetfaunasistema.raca(
	id_raca serial primary key,
        id_especie integer references vetfaunasistema.especie(id_especie) not null,
	descricao varchar(60) not null
);

COMMENT ON TABLE vetfaunasistema.raca IS 'Tabela responsavel por conter todos as racas.';
COMMENT ON COLUMN vetfaunasistema.raca.id_raca IS 'Coluna responsavel por conter o id do raca.';
COMMENT ON COLUMN vetfaunasistema.raca.id_especie IS 'Coluna responsavel por conter a especie que a raca pertence.';
COMMENT ON COLUMN vetfaunasistema.raca.descricao IS 'Coluna responsavel por conter o descricao da raca.';

insert into vetfaunasistema.raca (descricao, id_especie) values ('Akita Inu',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Beagle',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Beagle Harrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Bobtail',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Boiadeiro de Berna',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Boxer',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Braco Alemão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Braco Francês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Bull Terrier Inglês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Bulldog Americano',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Bulldog Inglês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cairn Terrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cane Corso',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Caniche',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cão de Crista Chinês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cão d’Água Espanhol',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cão Lobo Checoslovaco',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cavalier King Charles Spaniel',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Chihuahua',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Chow-Chow',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cocker Americano',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cocker Spaniel Inglês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Collie',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Dálmata',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Doberman',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Dogue Alemão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Dogue de Bordéus',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Epagneul Bretão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Fila Brasileiro',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Fox Terrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Galgo Afegão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Golden Retriever',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Husky Siberiano',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Ibizan Hound',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Jack Russel Terrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Lhasa Apso',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Mastiff',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Mastim dos Pirenéus',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Mastim Espanhol',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Mastim Napolitano',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Norfolk Terrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Papillon',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pastor Alemão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pastor de Beauce',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pequeno Basset Griffon',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pequeno Brabançon',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pequeno Cão Leão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pequinês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Perdigueiro Português',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pinscher Anão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Pitbull',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Rottweiler',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Samoiedo',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('São Bernardo',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Schnauzer',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Setter Inglês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Setter Irlandês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Shar Pei',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Shih Tzu',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Spaniel Japonês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Spitz Alemão',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Staffordshire Bull Terrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Teckel',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Terranova',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Terrier Brasileiro',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Vizsla',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Waimaraner',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('West Highland White Terrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Yorkshire Terrier',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));           
insert into vetfaunasistema.raca (descricao, id_especie) values ('Outras',(select id_especie from vetfaunasistema.especie e where e.descricao ='Caninos'));  
insert into vetfaunasistema.raca (descricao, id_especie) values ('Siameses',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Abissínios',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Angorá Turco',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Azul da Rússia',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Balinês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Bengal',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Bobtail Japoneses',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Brazilian Shorthair',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('British Shorthair',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Burmilla',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Chartreux',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Chinchila',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Cornish Rex',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Devon Rex',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Esfinge',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Havana',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Himalaios',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Javanês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Korat',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('LaPerm',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Maine Coon',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Manx',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Mau Egípcios',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Norueguês da Floresta',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Ocicat',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Ragdoll',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Sagrado da Birmânia',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Scottish Fold',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Tonquinês',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Toyger',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
insert into vetfaunasistema.raca (descricao, id_especie) values ('Outros',(select id_especie from vetfaunasistema.especie e where e.descricao ='Felinos'));
commit;
-------------------------------------------------------------------------------------------
create table vetfaunasistema.animal(
		id_animal serial  PRIMARY KEY,
		id_cliente integer references vetfaunasistema.cliente(id_cliente)not null,
		id_raca integer references vetfaunasistema.raca(id_raca)not null,
		nome varchar(60) not null,
		sexo char(5)not null check(sexo ='Macho' or sexo ='Fêmea'),
		observacao text,
		data_nascimento date,
		data_exclusao date,
		versao integer DEFAULT (0)
	);

	COMMENT ON TABLE vetfaunasistema.animal IS 'Tabela responsavel por conter todos os animals de um cliente.';
	COMMENT ON COLUMN vetfaunasistema.animal.id_animal IS 'Coluna responsavel por conter o id do animal.';
	COMMENT ON COLUMN vetfaunasistema.animal.id_cliente IS 'Coluna responsavel por conter o cliente do animal.';
	COMMENT ON COLUMN vetfaunasistema.animal.id_raca IS 'Coluna responsavel por conter a raca do animal.';
	COMMENT ON COLUMN vetfaunasistema.animal.nome IS 'Coluna responsavel por conter o nome do animal.';
	COMMENT ON COLUMN vetfaunasistema.animal.sexo IS 'Coluna responsavel por conter o sexo do animal. (M) Macho e (F)Fêmea';
	COMMENT ON COLUMN vetfaunasistema.animal.observacao IS 'Coluna responsavel por conter a observações do animal.';
	COMMENT ON COLUMN vetfaunasistema.animal.data_nascimento IS 'Coluna responsavel por conter a data que o animal nasceu.';
	COMMENT ON COLUMN vetfaunasistema.animal.data_exclusao IS 'Coluna responsavel por conter a data de exclusao do animal nasceu.';
	COMMENT ON COLUMN vetfaunasistema.animal.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

DROP TRIGGER trg_animal_i ON vetfaunasistema.animal;
drop function vetfaunasistema.fnc_animal_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_animal_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	begin 
                new.versao := 0;
                return new;
    end;';

CREATE TRIGGER trg_animal_i before insert 
ON  vetfaunasistema.animal FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_animal_i();

DROP TRIGGER trg_animal_u ON vetfaunasistema.animal;
drop function vetfaunasistema.fnc_animal_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_animal_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin
      
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_animal_u before update 
ON  vetfaunasistema.animal FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_animal_u();

DROP TRIGGER trg_animal_d ON vetfaunasistema.animal;
drop function vetfaunasistema.fnc_animal_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_animal_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     begin 
            update animal
               set data_exclusao = CURRENT_DATE
             where id_animal = old.id_animal;
             return old;
    end;';

CREATE TRIGGER trg_animal_d before delete 
ON  vetfaunasistema.animal FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_animal_d();	
---------------------------------------------------------------------------------
create table vetfaunasistema.pessoas_autorizadas(
		id_pessoas_autorizadas serial  PRIMARY KEY,
		id_cliente integer references vetfaunasistema.cliente(id_cliente)not null,
		nome varchar(60) not null,
		observacao text,
		data_exclusao date,
		versao integer DEFAULT (0)
	);

	COMMENT ON TABLE vetfaunasistema.pessoas_autorizadas IS 'Tabela responsavel por conter todos as pessoas_autorizadass de um cliente.';
	COMMENT ON COLUMN vetfaunasistema.pessoas_autorizadas.id_pessoas_autorizadas IS 'Coluna responsavel por conter o id da pessoas_autorizadas.';
	COMMENT ON COLUMN vetfaunasistema.pessoas_autorizadas.id_cliente IS 'Coluna responsavel por conter o cliente.';
	COMMENT ON COLUMN vetfaunasistema.pessoas_autorizadas.nome IS 'Coluna responsavel por conter o nome da pessoas autorizadas.';
	COMMENT ON COLUMN vetfaunasistema.pessoas_autorizadas.observacao IS 'Coluna responsavel por conter as observacoes da pessoa autorizada.';
	COMMENT ON COLUMN vetfaunasistema.pessoas_autorizadas.data_exclusao IS 'Coluna responsavel por conter a data de exclusao do pessoas_autorizadas.';
	COMMENT ON COLUMN vetfaunasistema.pessoas_autorizadas.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

DROP TRIGGER trg_pessoas_autorizadas_i ON vetfaunasistema.pessoas_autorizadas;
drop function vetfaunasistema.fnc_pessoas_autorizadas_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_pessoas_autorizadas_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	begin 
                new.versao := 0;
                return new;
    end;';

CREATE TRIGGER trg_pessoas_autorizadas_i before insert 
ON  vetfaunasistema.pessoas_autorizadas FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_pessoas_autorizadas_i();

DROP TRIGGER trg_pessoas_autorizadas_u ON vetfaunasistema.pessoas_autorizadas;
drop function vetfaunasistema.fnc_pessoas_autorizadas_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_pessoas_autorizadas_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin
         if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_pessoas_autorizadas_u before update 
ON  vetfaunasistema.pessoas_autorizadas FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_pessoas_autorizadas_u();

DROP TRIGGER trg_pessoas_autorizadas_d ON vetfaunasistema.pessoas_autorizadas;
drop function vetfaunasistema.fnc_pessoas_autorizadas_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_pessoas_autorizadas_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     begin 
            update pessoas_autorizadas
               set data_exclusao = CURRENT_DATE
             where id_pessoas_autorizadas = old.id_pessoas_autorizadas;
             return old;
    end;';

CREATE TRIGGER trg_pessoas_autorizadas_d before delete 
ON  vetfaunasistema.pessoas_autorizadas FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_pessoas_autorizadas_d();	
----------------------------------------------------------------------------------------------------	
 create table vetfaunasistema.vacina(
	id_vacina serial primary key,
	descricao varchar(60) not null,
	versao integer DEFAULT (0)
);

COMMENT ON TABLE vetfaunasistema.vacina IS 'Tabela responsavel por conter todos as vacinas.';
COMMENT ON COLUMN vetfaunasistema.vacina.id_vacina IS 'Coluna responsavel por conter o id do vacina.';
COMMENT ON COLUMN vetfaunasistema.vacina.descricao IS 'Coluna responsavel por conter o descricao da vacina.';                                       	

DROP TRIGGER trg_vacina_i ON vetfaunasistema.vacina;
drop function vetfaunasistema.fnc_vacina_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_vacina_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'begin 
        new.versao := 0;
        return new;
    end;';

CREATE TRIGGER trg_vacina_i before insert 
ON  vetfaunasistema.vacina FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_vacina_i();

DROP TRIGGER trg_vacina_u ON vetfaunasistema.vacina;
drop function vetfaunasistema.fnc_vacina_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_vacina_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_vacina_u before update 
ON  vetfaunasistema.vacina FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_vacina_u();

DROP TRIGGER trg_vacina_d ON vetfaunasistema.vacina;
drop function vetfaunasistema.fnc_vacina_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_vacina_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	valor integer;
     begin 
			select count(1)
			  into valor
			  from vacinacao e
			 where e.id_vacina = old.id_vacina
			   and e.quantidade > 0;
			if valor > 0 then
				RAISE EXCEPTION ''VETFAUNAERROR:Exclusão cancelada. Vacina em uso.'';
			end if;
             return new;
    end;';

CREATE TRIGGER trg_vacina_d before delete 
ON  vetfaunasistema.vacina FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_vacina_d();	
------------------------------------------------------------------------------------------------------------------
create table vetfaunasistema.vacinacao(
	id_vacinacao serial primary key,
	id_vacina integer references vetfaunasistema.vacina(id_vacina)not null,
	id_animal integer references vetfaunasistema.animal(id_animal)not null,
	nome_marca varchar(30) not null,
	data_vacinacao date not null,
	data_proxima_vacinacao date,
	data_exclusao date,
	versao integer DEFAULT (0)
);

COMMENT ON TABLE vetfaunasistema.vacinacao IS 'Tabela responsavel por conter todos as vacinacaos.';
COMMENT ON COLUMN vetfaunasistema.vacinacao.id_vacinacao IS 'Coluna responsavel por conter o id do vacinacao.';
COMMENT ON COLUMN vetfaunasistema.vacinacao.id_animal IS 'Coluna responsavel por conter o id do vacinacao.';
COMMENT ON COLUMN vetfaunasistema.vacinacao.nome_marca IS 'Coluna responsavel por conter o nome ou marca da vacina.';                                       	
COMMENT ON COLUMN vetfaunasistema.vacinacao.data_vacinacao IS 'Coluna responsavel por conter a data que o animal foi vacinado.';                                       	
COMMENT ON COLUMN vetfaunasistema.vacinacao.data_proxima_vacinacao IS 'Coluna responsavel por conter a data da proxima vacinacao.';                                       	
COMMENT ON COLUMN vetfaunasistema.vacinacao.data_exclusao IS 'Coluna responsavel por conter a data de exclusão.';                                       	

DROP TRIGGER trg_vacinacao_i ON vetfaunasistema.vacinacao;
drop function vetfaunasistema.fnc_vacinacao_i();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_vacinacao_i() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'begin 
        new.versao := 0;
        return new;
    end;';

CREATE TRIGGER trg_vacinacao_i before insert 
ON  vetfaunasistema.vacinacao FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_vacinacao_i();

DROP TRIGGER trg_vacinacao_u ON vetfaunasistema.vacinacao;
drop function vetfaunasistema.fnc_vacinacao_u();

CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_vacinacao_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        if new.versao <> old.versao then
            RAISE EXCEPTION ''VETFAUNAERROR:Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF; 
        return new;
    end;';


CREATE TRIGGER trg_vacinacao_u before update 
ON  vetfaunasistema.vacinacao FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_vacinacao_u();

DROP TRIGGER trg_vacinacao_d ON vetfaunasistema.vacinacao;
drop function vetfaunasistema.fnc_vacinacao_d();


CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_vacinacao_d() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
	valor integer;
     begin 
            update vacinacao
               set data_exclusao = CURRENT_DATE
             where id_vacinacao = old.id_vacinacao;
             return old;
    end;';

CREATE TRIGGER trg_vacinacao_d before delete 
ON  vetfaunasistema.vacinacao FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.fnc_vacinacao_d();	
