/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.fornecedor;

import br.com.chronos.vetfaunasistem.dao.fornecedor.FornecedorDao;
import br.com.chronos.vetfaunasistem.dao.fornecedor.FornecedorDaoImpl;
import br.com.chronos.vetfaunasistema.business.fornecedor.exception.FornecedorBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Fornecedor;

/**
 *
 * @author Fernando
 */
public class FornecedorBusinessImpl extends GenericBusinessImpl<Fornecedor, FornecedorDao<Fornecedor>, FornecedorBusinessException> implements FornecedorBusiness {

    private FornecedorDao<Fornecedor> fornecedorDao;

    public FornecedorBusinessImpl() {
        fornecedorDao = new FornecedorDaoImpl();
    }

    public FornecedorBusinessImpl(FornecedorDao fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }

    @Override
    public FornecedorDao<Fornecedor> getVetfaunaDao() {
        return fornecedorDao;
    }

}
