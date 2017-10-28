/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Align;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class Estoque implements GenericDomain {

    @EntiteValidation(action = Action.D_U, nameField = "código do estoque")
    private Integer idEstoque;
    
    @TableView(title = "Produto",name="nome")
    @EntiteValidation(action = Action.I_U,inField="idProduto", nameField = "código do produto")
    private Produto produto;
    
    @TableView(title = "Data Validade",typeMask= TypeMask.DATA,align= Align.RIGHT)
    private Date dataValidade;
    
    @TableView(title = "Valor Compra",typeMask= TypeMask.MONETARIO,align= Align.RIGHT)
    @EntiteValidation(action = Action.I_U,nameField = "preço da compra")
    private BigDecimal precoCompra;
    
    @TableView(title = "Quantidade",align= Align.RIGHT)
    @EntiteValidation(action = Action.I_U,nameField = "quantidade de produtos")
    private Integer quantidade;
    
    private BigDecimal percLucro;

    public BigDecimal getPercLucro() {
        return percLucro;
    }

    public void setPercLucro(BigDecimal percLucro) {
        this.percLucro = percLucro;
    }
    
    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Integer idEstoque) {
        this.idEstoque = idEstoque;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
    
}
