/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Align;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class Vacinacao implements GenericDomain {

    @EntiteValidation(action = Action.D_U, nameField = "código do vacinação")
    private Integer idVacinacao;
    
    @TableView(title = "Vacina")
    @EntiteValidation(action = Action.I_U,inField = "idVacina", nameField = "vacina")
    private Vacina vacina;
    
    @TableView(title = "Nome/Marca")
    @EntiteValidation(action = Action.I_U, nameField = "Nome/Marca")
    private String nomeMarca;
    
    @EntiteValidation(action = Action.I_U,inField = "idAnimal", nameField = "animal")
    private Animal animal;
    
    @TableView(title = "Data Vacinação",align= Align.RIGHT, typeMask= TypeMask.DATA)
    @EntiteValidation(action = Action.I_U, nameField = "data da vacinação")
    private Date dataVacinacao;
    
    @TableView(title = "Data da Próxima",align= Align.RIGHT, typeMask= TypeMask.DATA)
    private Date dataProximaVacinacao;
    

    private StatusRegistro statusRegistro= StatusRegistro.EXISTE;
    
    public Vacina getVacina() {
        return vacina;
    }

    public StatusRegistro getStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(StatusRegistro statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

    
    
    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    


    public Integer getIdVacinacao() {
        return idVacinacao;
    }

    public void setIdVacinacao(Integer idVacinacao) {
        this.idVacinacao = idVacinacao;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vacinacao other = (Vacinacao) obj;
        if (!Objects.equals(this.vacina, other.vacina)) {
            return false;
        }
        if (!Objects.equals(this.nomeMarca, other.nomeMarca)) {
            return false;
        }
        if (!Objects.equals(this.animal, other.animal)) {
            return false;
        }
        if (!Objects.equals(this.dataVacinacao, other.dataVacinacao)) {
            return false;
        }
        if (!Objects.equals(this.dataProximaVacinacao, other.dataProximaVacinacao)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.vacina);
        hash = 97 * hash + Objects.hashCode(this.nomeMarca);
        hash = 97 * hash + Objects.hashCode(this.animal);
        hash = 97 * hash + Objects.hashCode(this.dataVacinacao);
        hash = 97 * hash + Objects.hashCode(this.dataProximaVacinacao);
        return hash;
    }

    public Date getDataProximaVacinacao() {
        return dataProximaVacinacao;
    }

    public void setDataProximaVacinacao(Date dataProximaVacinacao) {
        this.dataProximaVacinacao = dataProximaVacinacao;
    }

    public Date getDataVacinacao() {
        return dataVacinacao;
    }

    public void setDataVacinacao(Date dataVacinacao) {
        this.dataVacinacao = dataVacinacao;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }



}
