/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.exception;

import org.apache.ibatis.exceptions.PersistenceException;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Fernando
 */
public class UtilExceprion {

    public static String extractMessageDB(PersistenceException persistenceException) {        
        if (persistenceException.getCause() instanceof PSQLException) {
            PSQLException pSQLException = (PSQLException) persistenceException.getCause();
            String message = pSQLException.getMessage();
            if (message.indexOf("VETFAUNAERROR:") != -1) {
                return message.split("VETFAUNAERROR:")[1];
            }
        }
        return "Erro de persistência.";
    }
}
