CREATE OR REPLACE FUNCTION vetfaunasistema.fnc_cargo_u() 
RETURNS trigger LANGUAGE plpgsql 
AS 
    'declare
     valor integer;
     begin 
        select count(1)
          into valor
          from cargo
         where descricao = new.descricao ; 
          
        if valor > 0 and new.descricao <> old.descricao then
            RAISE EXCEPTION ''Este cargo já existe.'';
        END IF; 
        
        if new.versao <> old.versao then
            RAISE EXCEPTION ''Esta versão do registro já foi alterada.'';
        else    
          new.versao := old.versao +1;
        END IF;  
        return new;
    end;';