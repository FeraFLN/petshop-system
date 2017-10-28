/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.endereco.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class EnderecoBusinessException extends GenericBusinessException {
    public EnderecoBusinessException(String messagem){
        super(messagem);
    }
}
