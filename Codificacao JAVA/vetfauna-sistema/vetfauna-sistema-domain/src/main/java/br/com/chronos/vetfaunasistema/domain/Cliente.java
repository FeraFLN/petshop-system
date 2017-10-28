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
public class Cliente implements GenericDomainWithContact{
    private static final long serialVersionUID = 1L;
    
    @EntiteValidation(action= Action.D_U ,nameField="id do Cliente", message="O código do Cliente não foi informado.")
    private Integer idCliente;
    
    @TableView(title = "Nome")
    @EntiteValidation(action= Action.I_U,nameField="Nome do cliente")
    private String nome;
    
    private Endereco endereco;
    
    @TableView(title = "E-Mail")
    private String email;
    
    @TableView(title = "Qtde. Animais")
    private int qtdeAnimais;

    private List<TelefoneContato> telefoneContato;
    
    private List<PessoasAutorizadas> pessoaAutorizadas;
    
    private List<Animal> animals;

    private String nomeAnimal;
    private String nomePessoasAutorizadas;

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getNomePessoasAutorizadas() {
        return nomePessoasAutorizadas;
    }

    public void setNomePessoasAutorizadas(String nomePessoasAutorizadas) {
        this.nomePessoasAutorizadas = nomePessoasAutorizadas;
    }
    
    public Cliente() {
        this.endereco = new Endereco();
    }
    
    
    public String getEmail() {
        return email;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    
    public List<PessoasAutorizadas> getPessoaAutorizadas() {
        return pessoaAutorizadas;
    }

    public void setPessoaAutorizadas(List<PessoasAutorizadas> pessoaAutorizadas) {
        this.pessoaAutorizadas = pessoaAutorizadas;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdeAnimais() {
        if(animals==null){
            qtdeAnimais = 0;
        } else{
            qtdeAnimais = animals.size();
        }
        return qtdeAnimais;
    }
    

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idCliente);
        hash = 89 * hash + Objects.hashCode(this.email);
        return hash;
    }
    
    
    @Override
    public void setIdGenericDomain(Object o) {
        setIdCliente((Integer)o);
    }

    @Override
    public Object getIdGenericDomain() {
        return getIdCliente();
    }

    @Override
    public List<TelefoneContato> getTelefoneContato() {
        return telefoneContato;
    }

    @Override
    public void setTelefoneContato(List<TelefoneContato> telefoneContatos) {
        this.telefoneContato = telefoneContatos;
    }
}
