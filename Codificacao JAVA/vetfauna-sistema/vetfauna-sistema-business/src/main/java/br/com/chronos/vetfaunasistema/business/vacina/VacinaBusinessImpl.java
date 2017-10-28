/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.vacina;

import br.com.chronos.vetfaunasistem.dao.vacina.VacinaDao;
import br.com.chronos.vetfaunasistem.dao.vacina.VacinaDaoImpl;
import br.com.chronos.vetfaunasistema.business.vacina.exception.VacinaBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Vacina;

/**
 *
 * @author Fernando
 */
public class VacinaBusinessImpl extends GenericBusinessImpl<Vacina, VacinaDao<Vacina>, VacinaBusinessException>  implements VacinaBusiness {

    private VacinaDao<Vacina> vacinaDao;

    public VacinaBusinessImpl() {
        vacinaDao = new VacinaDaoImpl();
    }

    public VacinaBusinessImpl(VacinaDao vacinaDao) {
        this.vacinaDao = vacinaDao;
    }

    @Override
    public VacinaDao<Vacina> getVetfaunaDao() {
        return vacinaDao;
    }
}
