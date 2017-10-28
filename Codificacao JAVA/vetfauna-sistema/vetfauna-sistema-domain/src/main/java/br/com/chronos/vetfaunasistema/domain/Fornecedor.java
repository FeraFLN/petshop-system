/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class Fornecedor implements GenericDomainWithContact {

    @EntiteValidation(action = Action.D_U,nameField = "codigo do fornecedor")
    private Integer idFornecedor;
    
    @TableView(title = "Nome")
    @EntiteValidation(action = Action.I_U, nameField = "nome do fornecedor")
    private String nome;
    
    private List<TelefoneContato> telefoneContato;

    public Fornecedor() {
    }

    public Fornecedor(String nome) {
        this.nome = nome;
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

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }


    @Override
    public void setIdGenericDomain(Object o) {
        idFornecedor = Integer.parseInt(o.toString());
    }

    @Override
    public Object getIdGenericDomain() {
        return idFornecedor;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.idFornecedor, other.idFornecedor)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.idFornecedor);
        return hash;
    }

    public String toString() {
        return nome ;
    }
    
}
