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

/**
 *
 * @author Fernando
 */
public class Produto implements GenericDomain {

    @EntiteValidation(action = Action.I_D_U, nameField = "codigo do produto")
    private Integer idProduto;
    
    
    @TableView(title = "Categoria",
               name = "descricao")
    private CategoriaProduto categoriaProduto;
    
    @TableView(title = "Marca",
               name = "descricao")
    @EntiteValidation(action = Action.I_U,
                      inField = "idMarcaProduto",
                      nameField = "marca do produto")
    private MarcaProduto marcaProduto;

    @TableView(title = "Nome")
    @EntiteValidation(action = Action.I_U, nameField = "nome do produto")
    private String nome;
        
    @TableView(title = "Medida", align = Align.RIGHT, typeMask= TypeMask.DECIMAL)
    @EntiteValidation(action = Action.I_U, nameField = "valor da medida")
    private BigDecimal valorMedida;
    
    @TableView(title = "Unidade",name="descricao", align = Align.LEFT)
    @EntiteValidation(action = Action.I_U,
                      inField = "idUnidadeMedida",
                      nameField = "unidade de medida")
    private UnidadeMedida unidadeMedida;
    
    private Integer quantidadeMinima;
    
    private Integer antecedenciaVencimento;

    private String codigoBarra;
    
    private BigDecimal preco;
    
    private Integer totalEstoque;

    public Integer getTotalEstoque() {
        return totalEstoque;
    }

    public void setTotalEstoque(Integer totalEstoque) {
        this.totalEstoque = totalEstoque;
    }
    
    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getAntecedenciaVencimento() {
        return antecedenciaVencimento;
    }

    public void setAntecedenciaVencimento(Integer antecedenciaVencimento) {
        this.antecedenciaVencimento = antecedenciaVencimento;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
    
    public CategoriaProduto getCategoriaProduto() {
        if(marcaProduto == null){
            return null;
        }
        return marcaProduto.getCategoriaProduto();
    }

//    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
//        this.categoriaProduto = categoriaProduto;
//    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public MarcaProduto getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(MarcaProduto marcaProduto) {
//        if(this.marcaProduto!=null){
//            categoriaProduto = marcaProduto.getCategoriaProduto();
//        }
        this.marcaProduto = marcaProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getValorMedida() {
        return valorMedida;
    }

    public void setValorMedida(BigDecimal valorMedida) {
        this.valorMedida = valorMedida;
    }

}
