/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.produto.exception;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class ProdutoBusinessException extends GenericBusinessException {
    public ProdutoBusinessException(String messagem){
        super(messagem);
    }
}
