/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class PessoasAutorizadas implements GenericDomain{
    private static final long serialVersionUID = 1L;
    @EntiteValidation(action= Action.D_U ,nameField="id da Pessoa Autorizada", message="O código do Pessoa Autorizada não foi informado.")
    private Integer idPessoasAutorizadas;
    
    @EntiteValidation(action= Action.I_U, inField="idCliente",nameField="Cliente")
    private Cliente cliente;
    
    @TableView(title = "Nome")
    @EntiteValidation(action= Action.I_U,nameField="Nome da Pessoa Autorizada")
    private String nome;

    private String observacao;

    public PessoasAutorizadas(Integer idPessoasAutorizadas) {
        this.idPessoasAutorizadas = idPessoasAutorizadas;
    }

    


    public PessoasAutorizadas() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getIdPessoasAutorizadas() {
        return idPessoasAutorizadas;
    }

    public void setIdPessoasAutorizadas(Integer idPessoasAutorizadas) {
        this.idPessoasAutorizadas = idPessoasAutorizadas;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PessoasAutorizadas other = (PessoasAutorizadas) obj;
        if (!Objects.equals(this.idPessoasAutorizadas, other.idPessoasAutorizadas)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }


    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idPessoasAutorizadas);
        hash = 79 * hash + Objects.hashCode(this.cliente);
        hash = 79 * hash + Objects.hashCode(this.nome);
        return hash;
    }

   
    
    
}
