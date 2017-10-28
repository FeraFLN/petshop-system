/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.categoriaproduto;

import br.com.chronos.vetfaunasistem.dao.categoriaproduto.CategoriaProdutoDao;
import br.com.chronos.vetfaunasistem.dao.categoriaproduto.CategoriaProdutoDaoImpl;
import br.com.chronos.vetfaunasistema.business.categoriaproduto.exception.CategoriaProdutoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.CategoriaProduto;

/**
 *
 * @author Fernando
 */
public class CategoriaProdutoBusinessImpl extends GenericBusinessImpl<CategoriaProduto, CategoriaProdutoDao<CategoriaProduto>, CategoriaProdutoBusinessException>  implements CategoriaProdutoBusiness {

    private CategoriaProdutoDao<CategoriaProduto> categoriaProdutoDao;

    public CategoriaProdutoBusinessImpl() {
        categoriaProdutoDao = new CategoriaProdutoDaoImpl();
    }

    public CategoriaProdutoBusinessImpl(CategoriaProdutoDao categoriaProdutoDao) {
        this.categoriaProdutoDao = categoriaProdutoDao;
    }

    @Override
    public CategoriaProdutoDao<CategoriaProduto> getVetfaunaDao() {
        return categoriaProdutoDao;
    }
}
