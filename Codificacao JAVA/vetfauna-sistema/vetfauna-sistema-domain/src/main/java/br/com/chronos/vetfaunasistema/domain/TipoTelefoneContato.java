/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class TipoTelefoneContato implements GenericDomain{
    
    private Integer idTipoTelefoneContato;
   
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdTipoContato() {
        return idTipoTelefoneContato;
    }

    public void setIdTipoContato(Integer idTipoTelefoneContato) {
        this.idTipoTelefoneContato = idTipoTelefoneContato;
    }

    public String toString() {
        return descricao;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoTelefoneContato other = (TipoTelefoneContato) obj;
        if (!Objects.equals(this.idTipoTelefoneContato, other.idTipoTelefoneContato)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idTipoTelefoneContato);
        return hash;
    }
    
    
}
