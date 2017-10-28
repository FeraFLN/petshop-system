/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.cliente.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class ClienteBusinessException extends GenericBusinessException {
    public ClienteBusinessException(String messagem){
        super(messagem);
    }
}
