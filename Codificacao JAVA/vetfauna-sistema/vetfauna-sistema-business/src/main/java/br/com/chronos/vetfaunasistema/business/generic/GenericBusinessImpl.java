/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.generic;

import br.com.chronos.vetfaunasistem.dao.generic.GenericDao;
import br.com.chronos.vetfaunasistema.business.exception.UtilExceprion;
import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;
import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.InvokeEntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.exceptions.InvokeEntiteValidationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author Fernando
 */
public abstract class GenericBusinessImpl<E extends GenericDomain, D extends GenericDao<E>, T extends GenericBusinessException>
        implements GenericBusiness<E, T> {

    public abstract D getVetfaunaDao();

    @Override
    public void insert(E genericDomain) throws T {
        try {
            InvokeEntiteValidation.insertValidation(genericDomain);
            getVetfaunaDao().insert(genericDomain);
        } catch (InvokeEntiteValidationException ex) {
            throw getNewException(ex.getMessage());
        } catch (PersistenceException e) {
            throw getNewException(UtilExceprion.extractMessageDB(e));
        }
    }

    @Override
    public void delete(E genericDomain)throws T {
         try {
            InvokeEntiteValidation.deleteValidation(genericDomain);
            getVetfaunaDao().delete(genericDomain);
        } catch (InvokeEntiteValidationException ex) {
            throw getNewException(ex.getMessage());
        } catch (PersistenceException e) {
            throw getNewException(UtilExceprion.extractMessageDB(e));
        }
    }

    @Override
    public void update(E genericDomain)throws T {
        try {
            InvokeEntiteValidation.updateValidation(genericDomain);
            getVetfaunaDao().update(genericDomain);
        } catch (InvokeEntiteValidationException ex) {
            throw getNewException(ex.getMessage());
        } catch (PersistenceException e) {
            throw getNewException(UtilExceprion.extractMessageDB(e));
        }
    }

    @Override
    public List<E> select(E genericDomain) throws T {
        try {
            InvokeEntiteValidation.validationEntite(genericDomain);
        } catch (InvokeEntiteValidationException ex) {
            throw getNewException(ex.getMessage());
        }
        return getVetfaunaDao().select(genericDomain);
    }
    
    @Override
    public Object getId(){
        return getVetfaunaDao().getId();
    }

    /**
     * Metódo responsável pela criação de uma nova exceção do tipo T
     * @param message
     * @return T
     */
    private T getNewException(String message) {
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class<T> theType = (Class<T>) type.getActualTypeArguments()[2];
            Constructor<T> constructor = theType.getConstructor(String.class);
            return constructor.newInstance(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
