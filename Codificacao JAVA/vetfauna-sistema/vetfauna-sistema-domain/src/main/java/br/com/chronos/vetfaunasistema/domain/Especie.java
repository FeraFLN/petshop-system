/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import java.util.List;

/**
 *
 * @author Fernando
 */
public class Especie implements GenericDomain{
    private static final long serialVersionUID = 1L;
    private Integer idEspecie;
    private String descricao;
    private List<Raca> racas;

    public String getDescricao() {
        return descricao;
    }

    public List<Raca> getRacas() {
        return racas;
    }

    public void setRacas(List<Raca> racas) {
        this.racas = racas;
    }

    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Especie other = (Especie) obj;
        if (this.idEspecie != other.idEspecie && (this.idEspecie == null || !this.idEspecie.equals(other.idEspecie))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idEspecie != null ? this.idEspecie.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public String toString() {
        return getDescricao();
    }
}
