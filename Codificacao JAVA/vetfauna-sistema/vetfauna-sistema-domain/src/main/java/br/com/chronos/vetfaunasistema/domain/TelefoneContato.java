/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import java.util.Objects;

/**
 *
 * @author Fernando
 */
public class TelefoneContato implements GenericDomain{
    
    
    private Integer idTelefoneContato;
   
    private TipoTelefoneContato tipoTelefoneContato;
    
    private String ddd;
    
    private String numero;
    
    private GenericDomainWithContact genericDomainWithContact;
    
    private StatusRegistro statusContato= StatusRegistro.EXISTE;

    public StatusRegistro getStatusContato() {
        return statusContato;
    }

    public void setStatusContato(StatusRegistro statusContato) {
        this.statusContato = statusContato;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Integer getIdTelefoneContato() {
        return idTelefoneContato;
    }

    public void setIdTelefoneContato(Integer idTelefoneContato) {
        this.idTelefoneContato = idTelefoneContato;
    }

    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefoneContato getTipoTelefoneContato() {
        return tipoTelefoneContato;
    }

    public void setTipoTelefoneContato(TipoTelefoneContato tipoTelefoneContato) {
        this.tipoTelefoneContato = tipoTelefoneContato;
    }

    public GenericDomainWithContact getGenericDomainWithContact() {
        return genericDomainWithContact;
    }

    public void setGenericDomainWithContact(GenericDomainWithContact genericDomainWithContact) {
        this.genericDomainWithContact = genericDomainWithContact;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TelefoneContato other = (TelefoneContato) obj;
        if (!Objects.equals(this.idTelefoneContato, other.idTelefoneContato)) {
            return false;
        }
        if (!Objects.equals(this.tipoTelefoneContato, other.tipoTelefoneContato)) {
            return false;
        }
        if (!Objects.equals(this.ddd, other.ddd)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        return hash;
    }
    
}
