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
public class Vendedor implements GenericDomainWithContact {

    @EntiteValidation(action = Action.D_U, nameField = "codigo do vendedor")
    private Integer idVendedor;
    
    @TableView(title = "Nome")
    @EntiteValidation(action = Action.I_U, nameField = "nome do vendedor")
    private String nome;
    
    @TableView(title = "Fornecedor",name = "nome")
    @EntiteValidation(action = Action.I_U,inField = "idFornecedor", nameField = "fornecedor")
    private Fornecedor fornecedor;
    
    private List<TelefoneContato> telefoneContato;

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    
    public List<TelefoneContato> getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(List<TelefoneContato> telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }



    @Override
    public void setIdGenericDomain(Object o) {
        idVendedor = Integer.parseInt(o.toString());
    }

    @Override
    public Object getIdGenericDomain() {
        return idVendedor;
    }
}
