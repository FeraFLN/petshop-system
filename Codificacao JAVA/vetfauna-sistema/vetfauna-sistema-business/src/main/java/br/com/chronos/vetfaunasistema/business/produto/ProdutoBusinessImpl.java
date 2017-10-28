/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.produto;

import br.com.chronos.vetfaunasistem.dao.produto.ProdutoDao;
import br.com.chronos.vetfaunasistem.dao.produto.ProdutoDaoImpl;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.business.produto.exception.ProdutoBusinessException;
import br.com.chronos.vetfaunasistema.domain.Produto;

/**
 *
 * @author Fernando
 */
public class ProdutoBusinessImpl extends GenericBusinessImpl<Produto, ProdutoDao<Produto>, ProdutoBusinessException>  implements ProdutoBusiness {

    private ProdutoDao<Produto> ProdutoDao;

    public ProdutoBusinessImpl() {
        ProdutoDao = new ProdutoDaoImpl(); 
    }

    public ProdutoBusinessImpl(ProdutoDao ProdutoDao) {
        this.ProdutoDao = ProdutoDao;
    }

    @Override
    public ProdutoDao<Produto> getVetfaunaDao() {
        return ProdutoDao;
    }
}
