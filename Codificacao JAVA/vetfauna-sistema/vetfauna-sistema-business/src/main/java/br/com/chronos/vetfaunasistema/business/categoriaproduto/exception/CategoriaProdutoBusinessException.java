/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.categoriaproduto.exception;

import br.com.chronos.vetfaunasistema.business.marcaproduto.exception.*;
import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class CategoriaProdutoBusinessException extends GenericBusinessException {
    public CategoriaProdutoBusinessException(String messagem){
        super(messagem);
    }
}
