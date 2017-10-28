/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.cargo.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class CargoBusinessException extends GenericBusinessException {
    public CargoBusinessException(String messagem){
        super(messagem);
    }
}
