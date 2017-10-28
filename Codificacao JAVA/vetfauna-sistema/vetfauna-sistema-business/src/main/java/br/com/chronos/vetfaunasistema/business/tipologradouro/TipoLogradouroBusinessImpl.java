/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.tipologradouro;

import br.com.chronos.vetfaunasistem.dao.tipologradouro.TipoLogradouroDao;
import br.com.chronos.vetfaunasistem.dao.tipologradouro.TipoLogradouroDaoImpl;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.business.tipologradouro.exception.TipoLogradouroBusinessException;
import br.com.chronos.vetfaunasistema.domain.TipoLogradouro;

/**
 *
 * @author Fernando
 */
public class TipoLogradouroBusinessImpl extends GenericBusinessImpl<TipoLogradouro, TipoLogradouroDao<TipoLogradouro>, TipoLogradouroBusinessException>  implements TipoLogradouroBusiness {

    private TipoLogradouroDao<TipoLogradouro> TipoLogradouroDao;

    public TipoLogradouroBusinessImpl() {
        TipoLogradouroDao = new TipoLogradouroDaoImpl(); 
    }

    public TipoLogradouroBusinessImpl(TipoLogradouroDao TipoLogradouroDao) {
        this.TipoLogradouroDao = TipoLogradouroDao;
    }

    @Override
    public TipoLogradouroDao<TipoLogradouro> getVetfaunaDao() {
        return TipoLogradouroDao;
    }
}
