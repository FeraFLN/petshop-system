/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.vacina.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class VacinaBusinessException extends GenericBusinessException {
    public VacinaBusinessException(String messagem){
        super(messagem);
    }
}
