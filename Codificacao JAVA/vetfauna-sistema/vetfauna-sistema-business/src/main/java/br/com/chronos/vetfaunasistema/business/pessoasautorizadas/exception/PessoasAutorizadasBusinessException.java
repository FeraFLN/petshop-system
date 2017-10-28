/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.pessoasautorizadas.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class PessoasAutorizadasBusinessException extends GenericBusinessException {
    public PessoasAutorizadasBusinessException(String messagem){
        super(messagem);
    }
}
