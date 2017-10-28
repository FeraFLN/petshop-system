/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class Raca implements GenericDomain{
    private static final long serialVersionUID = 1L;
    
    @EntiteValidation(action= Action.D_U ,nameField="id do raca", message="O código do raca não foi informado.")
    private Integer idRaca;
    
    @EntiteValidation(action= Action.I_U,inField="idEspecie",nameField="especie")
    private Especie especie;
    
    @EntiteValidation(action= Action.I_U,nameField="raça")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Integer getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(Integer idRaca) {
        this.idRaca = idRaca;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Raca other = (Raca) obj;
        if (!Objects.equals(this.idRaca, other.idRaca)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.idRaca);
        return hash;
    }

    public String toString() {
        return descricao;
    }

    
    
}
