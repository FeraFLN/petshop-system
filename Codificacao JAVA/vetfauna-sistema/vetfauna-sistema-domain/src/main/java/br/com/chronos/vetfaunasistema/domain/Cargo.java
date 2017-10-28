/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class Cargo implements GenericDomain{
    private static final long serialVersionUID = 1L;
    @EntiteValidation(action= Action.D_U ,nameField="id do cargo", message="O código do cargo não foi informado.")
    private Integer idCargo;
    @TableView(title = "Nome do Cargo")
    @EntiteValidation(action= Action.I_U,nameField="descrição",message="O cargo não foi informado.")
    private String descricao;
    private Date dataexclusao;
    private Integer versao;

    public Cargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Cargo(String descricao) {
        this.descricao = descricao;
    }

    public Cargo() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataexclusao() {
        return dataexclusao;
    }

    public void setDataexclusao(Date dataexclusao) {
        this.dataexclusao = dataexclusao;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
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
        final Cargo other = (Cargo) obj;
        if (this.idCargo != other.idCargo && (this.idCargo == null || !this.idCargo.equals(other.idCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idCargo != null ? this.idCargo.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public String toString() {
        return getDescricao();
    }
}
