/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class CategoriaProduto implements GenericDomain{
    private static final long serialVersionUID = 1L;
    @EntiteValidation(action= Action.D_U ,nameField="id da categoria do produto")
    private Integer idCategoriaProduto;
    @TableView(title = "Nome da categoria")
    @EntiteValidation(action= Action.I_U,nameField="nome da categoria")
    private String descricao;

    private List<MarcaProduto> marcaProdutos;
    
    public CategoriaProduto(Integer idCategoriaProduto) {
        this.idCategoriaProduto = idCategoriaProduto;
    }

    public List<MarcaProduto> getMarcaProdutos() {
        return marcaProdutos;
    }

    public void setMarcaProdutos(List<MarcaProduto> marcaProdutos) {
        this.marcaProdutos = marcaProdutos;
    }

    public CategoriaProduto(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaProduto() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Integer getIdCategoriaProduto() {
        return idCategoriaProduto;
    }

    public void setIdCategoriaProduto(Integer idCategoriaProduto) {
        this.idCategoriaProduto = idCategoriaProduto;
    }
    


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CategoriaProduto other = (CategoriaProduto) obj;
        if (this.idCategoriaProduto != other.idCategoriaProduto && (this.idCategoriaProduto == null || !this.idCategoriaProduto.equals(other.idCategoriaProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.idCategoriaProduto != null ? this.idCategoriaProduto.hashCode() : 0);
        return hash;
    }
    
    
    @Override
    public String toString() {
        return getDescricao();
    }
}
