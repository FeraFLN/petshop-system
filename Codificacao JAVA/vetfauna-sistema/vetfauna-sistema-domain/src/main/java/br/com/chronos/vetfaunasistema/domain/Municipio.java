/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import java.util.Objects;

/**
 *
 * @author fernandoneto
 */
public class Municipio  implements GenericDomain{
    private Integer idMunicipio;
    private String nome;
    private Estado estado;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String toString() {
        return nome;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Municipio other = (Municipio) obj;
        if (!Objects.equals(this.idMunicipio, other.idMunicipio)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.idMunicipio);
        return hash;
    }
    
    
}
