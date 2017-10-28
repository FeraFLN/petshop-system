/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.especie;

import br.com.chronos.vetfaunasistem.dao.especie.EspecieDao;
import br.com.chronos.vetfaunasistem.dao.especie.EspecieDaoImpl;
import br.com.chronos.vetfaunasistema.business.especie.exception.EspecieBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Especie;

/**
 *
 * @author Fernando
 */
public class EspecieBusinessImpl extends GenericBusinessImpl<Especie, EspecieDao<Especie>, EspecieBusinessException> implements EspecieBusiness {

    private EspecieDao<Especie> especieDao;

    public EspecieBusinessImpl() {
        especieDao = new EspecieDaoImpl();
    }

    public EspecieBusinessImpl(EspecieDao especieDao) {
        this.especieDao = especieDao;
    }

    @Override
    public EspecieDao<Especie> getVetfaunaDao() {
        return especieDao;
    }
}
