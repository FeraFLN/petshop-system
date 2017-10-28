/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.vacinacao.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class VacinacaoBusinessException extends GenericBusinessException {
    public VacinacaoBusinessException(String messagem){
        super(messagem);
    }
}
