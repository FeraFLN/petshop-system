/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.estado;

import br.com.chronos.vetfaunasistem.dao.estado.EstadoDao;
import br.com.chronos.vetfaunasistem.dao.estado.EstadoDaoImpl;
import br.com.chronos.vetfaunasistema.business.estado.exception.EstadoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Estado;

/**
 *
 * @author Fernando
 */
public class EstadoBusinessImpl extends GenericBusinessImpl<Estado, EstadoDao<Estado>, EstadoBusinessException>  implements EstadoBusiness {

    private EstadoDao<Estado> estadoDao;

    public EstadoBusinessImpl() {
        estadoDao = new EstadoDaoImpl();
    }

    public EstadoBusinessImpl(EstadoDao estadoDao) {
        this.estadoDao = estadoDao;
    }

    @Override
    public EstadoDao<Estado> getVetfaunaDao() {
        return estadoDao;
    }
}
