/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.marcaproduto;

import br.com.chronos.vetfaunasistem.dao.marcaproduto.MarcaProdutoDao;
import br.com.chronos.vetfaunasistem.dao.marcaproduto.MarcaProdutoDaoImpl;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.business.marcaproduto.exception.MarcaProdutoBusinessException;
import br.com.chronos.vetfaunasistema.domain.MarcaProduto;

/**
 *
 * @author Fernando
 */
public class MarcaProdutoBusinessImpl extends GenericBusinessImpl<MarcaProduto, MarcaProdutoDao<MarcaProduto>, MarcaProdutoBusinessException>  implements MarcaProdutoBusiness {

    private MarcaProdutoDao<MarcaProduto> marcaProdutoDao;

    public MarcaProdutoBusinessImpl() {
        marcaProdutoDao = new MarcaProdutoDaoImpl(); 
    }

    public MarcaProdutoBusinessImpl(MarcaProdutoDao marcaProdutoDao) {
        this.marcaProdutoDao = marcaProdutoDao;
    }

    @Override
    public MarcaProdutoDao<MarcaProduto> getVetfaunaDao() {
        return marcaProdutoDao;
    }
}
