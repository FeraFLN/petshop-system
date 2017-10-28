/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain;

import java.util.List;

/**
 *
 * @author Fernando
 */
public interface  GenericDomainWithContact extends GenericDomain {
    
    public void setIdGenericDomain(Object o);
    
    public Object getIdGenericDomain();
    
    public List<TelefoneContato> getTelefoneContato();
    
    public void setTelefoneContato(List<TelefoneContato> telefoneContatos);
    
}
