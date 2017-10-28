/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.especie.exception;

import br.com.chronos.vetfaunasistema.business.estado.exception.*;
import br.com.chronos.vetfaunasistema.business.cargo.exception.*;
import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class EspecieBusinessException extends GenericBusinessException {
    public EspecieBusinessException(String messagem){
        super(messagem);
    }
}
