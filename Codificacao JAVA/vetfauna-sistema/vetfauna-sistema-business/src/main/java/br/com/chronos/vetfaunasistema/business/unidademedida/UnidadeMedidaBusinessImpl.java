/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.unidademedida;

import br.com.chronos.vetfaunasistem.dao.unidademedida.UnidadeMedidaDao;
import br.com.chronos.vetfaunasistem.dao.unidademedida.UnidadeMedidaDaoImpl;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.business.unidademedida.exception.UnidadeMedidaBusinessException;
import br.com.chronos.vetfaunasistema.domain.UnidadeMedida;

/**
 *
 * @author Fernando
 */
public class UnidadeMedidaBusinessImpl extends GenericBusinessImpl<UnidadeMedida, UnidadeMedidaDao<UnidadeMedida>, UnidadeMedidaBusinessException> implements UnidadeMedidaBusiness {

    private UnidadeMedidaDao<UnidadeMedida> funcionarioDao;

    public UnidadeMedidaBusinessImpl() {
        funcionarioDao = new UnidadeMedidaDaoImpl();
    }

    public UnidadeMedidaBusinessImpl(UnidadeMedidaDao funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    @Override
    public UnidadeMedidaDao<UnidadeMedida> getVetfaunaDao() {
        return funcionarioDao;
    }

}
