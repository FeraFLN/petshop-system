/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.estoque;

import br.com.chronos.vetfaunasistem.dao.estoque.EstoqueDao;
import br.com.chronos.vetfaunasistem.dao.estoque.EstoqueDaoImpl;
import br.com.chronos.vetfaunasistema.business.estoque.exception.EstoqueBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Estoque;

/**
 *
 * @author Fernando
 */
public class EstoqueBusinessImpl extends GenericBusinessImpl<Estoque, EstoqueDao<Estoque>, EstoqueBusinessException>  implements EstoqueBusiness {

    private EstoqueDao<Estoque> estoqueDao;

    public EstoqueBusinessImpl() {
        estoqueDao = new EstoqueDaoImpl();
    }

    public EstoqueBusinessImpl(EstoqueDao estoqueDao) {
        this.estoqueDao = estoqueDao;
    }

    
    @Override
    public EstoqueDao<Estoque> getVetfaunaDao() {
        return estoqueDao;
    }
}
