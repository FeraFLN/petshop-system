package br.com.chronos.vetfaunasistema.business.generic.exception;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Fernando
 */
public class GenericBusinessException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    public GenericBusinessException(String message) {
        super(message);
    }
}
