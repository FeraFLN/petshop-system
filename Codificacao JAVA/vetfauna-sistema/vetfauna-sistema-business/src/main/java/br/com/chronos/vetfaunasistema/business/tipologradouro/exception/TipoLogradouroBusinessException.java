/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.tipologradouro.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class TipoLogradouroBusinessException extends GenericBusinessException {
    public TipoLogradouroBusinessException(String messagem){
        super(messagem);
    }
}
