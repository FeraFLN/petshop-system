/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.tipotelefonecontato.exception;

import br.com.chronos.vetfaunasistema.business.funcionario.exception.*;
import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;

/**
 *
 * @author Fernando
 */
public class TipoTelefoneContatoBusinessException extends GenericBusinessException {
    public TipoTelefoneContatoBusinessException(String messagem){
        super(messagem);
    }
}
