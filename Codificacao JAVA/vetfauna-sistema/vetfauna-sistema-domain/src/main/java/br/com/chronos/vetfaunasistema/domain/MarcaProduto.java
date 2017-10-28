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
public class MarcaProduto implements GenericDomain{
    private static final long serialVersionUID = 1L;
    @EntiteValidation(action= Action.D_U ,nameField="id da marca do produto")
    private Integer idMarcaProduto;
    @TableView(title = "Nome da marca")
    @EntiteValidation(action= Action.I_U,nameField="nome da marca")
    private String descricao;

    @TableView(title = "Categoria",name="descricao")
    @EntiteValidation(action= Action.I_U,inField="idCategoriaProduto",nameField="categoria")
    private CategoriaProduto categoriaProduto;

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }
    
    public MarcaProduto(Integer idMarcaProduto) {
        this.idMarcaProduto = idMarcaProduto;
    }
    public MarcaProduto(String descricao) {
        this.descricao = descricao;
    }

    public MarcaProduto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Integer getIdMarcaProduto() {
        return idMarcaProduto;
    }

    public void setIdMarcaProduto(Integer idMarcaProduto) {
        this.idMarcaProduto = idMarcaProduto;
    }
    


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MarcaProduto other = (MarcaProduto) obj;
        if (this.idMarcaProduto != other.idMarcaProduto && (this.idMarcaProduto == null || !this.idMarcaProduto.equals(other.idMarcaProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idMarcaProduto != null ? this.idMarcaProduto.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public String toString() {
        return getDescricao();
    }
}
