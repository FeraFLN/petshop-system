/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

/**
 *
 * @author Fernando
 */
public class UnidadeMedida implements GenericDomain{
    private static final long serialVersionUID = 1L;
    private Integer idUnidadeMedida;
    private String descricao;
    private String abreviacao;

    public UnidadeMedida(Integer idUnidadeMedida) {
        this.idUnidadeMedida = idUnidadeMedida;
    }

    public UnidadeMedida() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Integer getIdUnidadeMedida() {
        return idUnidadeMedida;
    }

    public void setIdUnidadeMedida(Integer idUnidadeMedida) {
        this.idUnidadeMedida = idUnidadeMedida;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnidadeMedida other = (UnidadeMedida) obj;
        if (this.idUnidadeMedida != other.idUnidadeMedida && (this.idUnidadeMedida == null || !this.idUnidadeMedida.equals(other.idUnidadeMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idUnidadeMedida != null ? this.idUnidadeMedida.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public String toString() {
        return getDescricao();
    }
}
