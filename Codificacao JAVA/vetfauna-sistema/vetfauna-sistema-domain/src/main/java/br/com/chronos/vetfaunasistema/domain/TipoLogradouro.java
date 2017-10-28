/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

/**
 *
 * @author Fernando
 */
public class TipoLogradouro implements GenericDomain{
    private static final long serialVersionUID = 1L;
    private Integer idTipoLogradouro;
    private String descricao;
    private Integer versao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdTipoLogradouro() {
        return idTipoLogradouro;
    }

    public void setIdTipoLogradouro(Integer idTipoLogradouro) {
        this.idTipoLogradouro = idTipoLogradouro;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoLogradouro other = (TipoLogradouro) obj;
        if (this.idTipoLogradouro != other.idTipoLogradouro && (this.idTipoLogradouro == null || !this.idTipoLogradouro.equals(other.idTipoLogradouro))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idTipoLogradouro != null ? this.idTipoLogradouro.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public String toString() {
        return getDescricao();
    }
}
