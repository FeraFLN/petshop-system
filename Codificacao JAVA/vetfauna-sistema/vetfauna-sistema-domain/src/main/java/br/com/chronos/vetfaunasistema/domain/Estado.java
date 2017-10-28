/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author fernandoneto
 */
public class Estado implements GenericDomain{
    private Integer idEstado;
    private String nome;
    private String abreviacao;
    private List<Municipio> listaMunicipio;

    public Estado(String nome) {
        this.nome = nome;
    }

    public Estado() {
    }

    
    
    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public List<Municipio> getListaMunicipio() {
        return listaMunicipio;
    }

    public void setListaMunicipio(List<Municipio> listaMunicipio) {
        this.listaMunicipio = listaMunicipio;
    }
    
    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.idEstado, other.idEstado)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.idEstado);
        hash = 61 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    public String toString() {
        return abreviacao;
    }
    
    
}
