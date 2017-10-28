/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.unidademedida.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class UnidadeMedidaBusinessException extends GenericBusinessException {
    public UnidadeMedidaBusinessException(String messagem){
        super(messagem);
    }
}
