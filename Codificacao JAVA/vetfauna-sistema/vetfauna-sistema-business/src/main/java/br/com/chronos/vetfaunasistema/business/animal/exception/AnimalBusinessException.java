/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.animal.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class AnimalBusinessException extends GenericBusinessException {
    public AnimalBusinessException(String messagem){
        super(messagem);
    }
}
