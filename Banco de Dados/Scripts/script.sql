CREATE DATABASE vetfaunasistema
  WITH ENCODING='UTF8'
       OWNER=vetfaunasistema
       CONNECTION LIMIT=-1
       TABLESPACE=pg_default;

COMMENT ON DATABASE vetfaunasistema
  IS 'Sistema para clinica veterinaria e petshop.';
REVOKE ALL ON DATABASE vetfaunasistema FROM public;

-- Role: vetfaunasistema

-- DROP ROLE vetfaunasistema;

CREATE ROLE vetfaunasistema LOGIN
  ENCRYPTED PASSWORD 'md5062b3301e7f3e017bbcdb9d22e19158a'
  SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;
COMMENT ON ROLE vetfaunasistema IS 'Usuário master do vetfaunasistema.';



create table vetfaunasistema.cargo(
	id_cargo serial  PRIMARY KEY,
	descricao varchar(150) not null,
	data_exclusao date,
	versao integer DEFAULT (0) not null
);

COMMENT ON TABLE vetfaunasistema.cargo IS 'Tabela responsavel por conter todos os funicionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.cargo.id_cargo IS 'Coluna responsavel por conter o id do cargo.';
COMMENT ON COLUMN vetfaunasistema.cargo.descricao IS 'Coluna responsavel por conter descricao do cargo.';
COMMENT ON COLUMN vetfaunasistema.cargo.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

--DROP TRIGGER trg_cargo_u ON vetfaunasistema.cargo;
--drop function vetfaunasistema.teste();


CREATE OR REPLACE FUNCTION vetfaunasistema.TESTE_VERSAO() 
RETURNS trigger LANGUAGE plpgsql 
AS --$teste$
    'begin 
        if new.versao <> old.versao then
            RAISE EXCEPTION ''Esta versão do registro já foi alterada.'';
        ELSIF    
          new.versao := old.versao +1;
        END IF;  
        return new;
    end;';


CREATE TRIGGER trg_cargo_u before update 
ON  vetfaunasistema.cargo FOR  EACH ROW 
    EXECUTE PROCEDURE vetfaunasistema.TESTE_VERSAO();

-------------------------------------------------------------------------------------------------------------------------------------------

create table vetfaunasistema.funcionario(
	id_funcionario serial  PRIMARY KEY,
	id_cargo integer references vetfaunasistema.cargo(id_cargo)not null,
	nome varchar(150) not null,
	pis varchar(11),
	carteira_trabalho varchar(20),
	rg varchar(20),
	cpf varchar(11),
	salario numeric(7,2) not null,
	data_contratacao date not null,
	dia_pagamento integer not null,
    versao integer DEFAULT (0)
);

COMMENT ON TABLE vetfaunasistema.Funcionario IS 'Tabela responsavel por conter todos os funicionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.id_funcionario IS 'Coluna responsavel por conter o id do funicionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.nome IS 'Coluna responsavel por conter o nome completo do funicionarios da empresa.';
COMMENT ON COLUMN vetfaunasistema.Funcionario.pis IS 'Coluna responsavel por conter dados sobre o pis do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.carteira_trabalho IS 'Coluna responsavel por conter o numero da carteira de trabalho do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.rg IS 'Coluna responsavel por conter o numero do rg do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.cpf IS 'Coluna responsavel por conter o numero do cpf do funcionario (esse campo nao eh obrigatorio).';
COMMENT ON COLUMN vetfaunasistema.Funcionario.salario IS 'Coluna responsavel por conter o valor do salario do funcionario .';
COMMENT ON COLUMN vetfaunasistema.Funcionario.data_contratacao IS 'Coluna responsavel por conter a data da contratacao do funcionario .';
COMMENT ON COLUMN vetfaunasistema.Funcionario.dia_pagamento IS 'Coluna responsavel por conter a data do pagamento do funcionario .';
COMMENT ON COLUMN vetfaunasistema.Funcionario.versao IS 'Coluna responsavel por conter a versao do determinado registro.';

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
-------------------------------------------------------------------------------------------------------------------------------------------

create table vetfaunasistema.telefone_contato(
	id_telefone_contato serial  PRIMARY KEY,
	id_tipo_telefone_contato integer references vetfaunasistema.tipo_telefone_contato(id_tipo_telefone_contato) not null,
	ddd varchar(2) not null,
	numero varchar(9) not null,
        versao integer DEFAULT (0),
        CONSTRAINT unique_telefone_contato UNIQUE(ddd,numero) 
);

COMMENT ON TABLE vetfaunasistema.telefone_contato IS 'Tabela responsavel por conter todos os telefones de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.id_telefone_contato IS 'Coluna responsavel por conter o id do telefone de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.id_tipo_telefone_contato IS 'Coluna responsavel por conter o id do tipo de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.ddd IS 'Coluna responsavel por conter o ddd do contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.numero IS 'Coluna responsavel por conter numero de contato.';
COMMENT ON COLUMN vetfaunasistema.telefone_contato.versao IS 'Coluna responsavel por conter a versao do determinado registro.';
-------------------------------------------------------------------------------------------------------------------------------------------

create table vetfaunasistema.telefone_funcionario(
	id_funcionario integer references vetfaunasistema.funcionario(id_funcionario) not null,
	id_telefone_contato integer references vetfaunasistema.telefone_contato(id_telefone_contato) not null,
        CONSTRAINT unique_telefone_funcionario UNIQUE(id_funcionario,id_telefone_contato) 
);

COMMENT ON TABLE vetfaunasistema.telefone_funcionario IS 'Tabela responsavel por conter todos os telefones de funcionarios.';
COMMENT ON COLUMN vetfaunasistema.telefone_funcionario.id_funcionario IS 'Coluna responsavel por conter o id do funcionario.';
COMMENT ON COLUMN vetfaunasistema.telefone_funcionario.id_telefone_contato IS 'Coluna responsavel por conter o id do contato.';
