/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.vendedor.exception;

import br.com.chronos.vetfaunasistema.business.funcionario.exception.*;
import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class VendedorBusinessException extends GenericBusinessException {
    public VendedorBusinessException(String messagem){
        super(messagem);
    }
}
