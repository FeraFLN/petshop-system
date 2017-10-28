/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class Endereco implements GenericDomain, Cloneable {

    @EntiteValidation(action = Action.D_U, nameField = "codigo do Endereco")
    private Integer idEndereco;
    @EntiteValidation(action = Action.I_U, nameField = "cep")
    private String cep;
    @EntiteValidation(action = Action.I_U, inField = "descricao", nameField = "Tipo do Logradouro")
    private TipoLogradouro tipoLogradouro;
    @EntiteValidation(action = Action.I_U, nameField = "Bairro")
    private String bairro;
    @EntiteValidation(action = Action.I_U, nameField = "Logradouro")
    private String logradouro;
    private String numero;
    private String complemento;
    @EntiteValidation(action = Action.I_U, inField = "idMunicipio", nameField = "municipio")
    private Municipio municipio;
    @EntiteValidation(action = Action.I_U, inField = "idEstado", nameField = "estado")
    private Estado estado;

    public Object clone() {
        Endereco obj = new Endereco();
        obj.setBairro(this.bairro );
        obj.setCep(this.cep);
        obj.setComplemento(this.complemento );
        obj.setIdEndereco(this.idEndereco);
        obj.setLogradouro(this.logradouro);
        obj.setMunicipio(this.municipio);
        obj.setNumero(this.numero);
        obj.setTipoLogradouro(this.tipoLogradouro);
        return obj;
    }

    public Endereco() {
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
        
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public Estado getEstado() {
        if (municipio == null) {
            return null;
        }
        return municipio.getEstado();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.idEndereco, other.idEndereco)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.tipoLogradouro, other.tipoLogradouro)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.municipio, other.municipio)) {
            return false;
        }
//        if (!Objects.equals(this.estado, other.estado)) {
//            return false;
//        }
        return true;
    }

    
    

    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idEndereco);
        hash = 89 * hash + Objects.hashCode(this.cep);
        return hash;
    }
    public boolean isEmpty(){
        if((this.bairro == null||this.bairro.isEmpty())&&
           (this.cep == null || this.cep.isEmpty())&&
           (this.complemento == null || this.complemento.isEmpty())&&
           (this.estado == null)&&
           (this.logradouro == null || this.logradouro.isEmpty())&&
           (this.municipio == null)&&
           (this.numero == null || this.numero.isEmpty())&&
           (this.tipoLogradouro == null)){
            return true;
        }
        return false;
    }
}
