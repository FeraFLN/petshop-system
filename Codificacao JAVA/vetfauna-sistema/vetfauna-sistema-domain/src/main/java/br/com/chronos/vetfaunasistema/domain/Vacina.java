/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;

/**
 *
 * @author Fernando
 */
public class Vacina implements GenericDomain{
    private static final long serialVersionUID = 1L;
    @EntiteValidation(action= Action.D_U ,nameField="id do vacina", message="O código do vacina não foi informado.")
    private Integer idVacina;
    @TableView(title = "Vacina")
    @EntiteValidation(action= Action.I_U,nameField="descrição")
    private String descricao;

    public Vacina(Integer idVacina) {
        this.idVacina = idVacina;
    }

    public Vacina() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Integer getIdVacina() {
        return idVacina;
    }

    public void setIdVacina(Integer idVacina) {
        this.idVacina = idVacina;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vacina other = (Vacina) obj;
        if (this.idVacina != other.idVacina && (this.idVacina == null || !this.idVacina.equals(other.idVacina))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idVacina != null ? this.idVacina.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public String toString() {
        return getDescricao();
    }
}
