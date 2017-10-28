/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.vendedor;

import br.com.chronos.vetfaunasistem.dao.vendedor.VendedorDao;
import br.com.chronos.vetfaunasistem.dao.vendedor.VendedorDaoImpl;
import br.com.chronos.vetfaunasistema.business.vendedor.exception.VendedorBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Vendedor;

/**
 *
 * @author Fernando
 */
public class VendedorBusinessImpl extends GenericBusinessImpl<Vendedor, VendedorDao<Vendedor>, VendedorBusinessException> implements VendedorBusiness {

    private VendedorDao<Vendedor> vendedorDao;

    public VendedorBusinessImpl() {
        vendedorDao = new VendedorDaoImpl();
    }

    public VendedorBusinessImpl(VendedorDao vendedorDao) {
        this.vendedorDao = vendedorDao;
    }

    @Override
    public VendedorDao<Vendedor> getVetfaunaDao() {
        return vendedorDao;
    }

}
