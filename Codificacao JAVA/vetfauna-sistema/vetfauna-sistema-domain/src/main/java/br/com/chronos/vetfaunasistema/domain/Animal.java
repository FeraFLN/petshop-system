/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Sexo;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class Animal implements GenericDomain{
    private static final long serialVersionUID = 1L;
    
    @EntiteValidation(action= Action.D_U ,nameField="id do animal")
    private Integer idAnimal;
    
    @TableView(title = "Nome")
    @EntiteValidation(action= Action.I_U,nameField="nome")
    private String nome;
    
    @TableView(title = "Raça",name="descricao")
    @EntiteValidation(action= Action.I_U,inField="idRaca",nameField="raça")
    private Raca raca;
    
    @EntiteValidation(action= Action.I_U,inField="idCliente",nameField="Proprietário")
    private Cliente cliente;
    
    private Date dataNascimento;

    private String observacao;
    
    @EntiteValidation(action= Action.I_U,nameField="sexo")
    private Sexo sexo;
    
    private List<Vacinacao> vacinacoes;

    public List<Vacinacao> getVacinacoes() {
        return vacinacoes;
    }

    public void setVacinacoes(List<Vacinacao> vacinacoes) {
        this.vacinacoes = vacinacoes;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (!Objects.equals(this.idAnimal, other.idAnimal)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idAnimal);
        return hash;
    }

   

}
