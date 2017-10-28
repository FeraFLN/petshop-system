/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.endereco;

import br.com.chronos.vetfaunasistem.dao.endereco.EnderecoDao;
import br.com.chronos.vetfaunasistem.dao.endereco.EnderecoDaoImpl;
import br.com.chronos.vetfaunasistema.business.endereco.exception.EnderecoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Endereco;

/**
 *
 * @author Fernando
 */
public class EnderecoBusinessImpl extends GenericBusinessImpl<Endereco, EnderecoDao<Endereco>, EnderecoBusinessException>  implements EnderecoBusiness {

    private EnderecoDao<Endereco> enderecoDao;

    public EnderecoBusinessImpl() {
        enderecoDao = new EnderecoDaoImpl();
    }

    public EnderecoBusinessImpl(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    @Override
    public EnderecoDao<Endereco> getVetfaunaDao() {
        return enderecoDao;
    }
    
    public int getIdEndereco(){
        return enderecoDao.getIdEndereco();
    }
}
