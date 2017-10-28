/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.pessoasautorizadas;

import br.com.chronos.vetfaunasistem.dao.pessoasautorizadas.PessoasAutorizadasDao;
import br.com.chronos.vetfaunasistem.dao.pessoasautorizadas.PessoasAutorizadasDaoImpl;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.business.pessoasautorizadas.exception.PessoasAutorizadasBusinessException;
import br.com.chronos.vetfaunasistema.domain.PessoasAutorizadas;

/**
 *
 * @author Fernando
 */
public class PessoasAutorizadasBusinessImpl extends GenericBusinessImpl<PessoasAutorizadas, PessoasAutorizadasDao<PessoasAutorizadas>, PessoasAutorizadasBusinessException>  implements PessoasAutorizadasBusiness {

    private PessoasAutorizadasDao<PessoasAutorizadas> pessoasAutorizadasDao;

    public PessoasAutorizadasBusinessImpl() {
        pessoasAutorizadasDao = new PessoasAutorizadasDaoImpl();
    }

    public PessoasAutorizadasBusinessImpl(PessoasAutorizadasDao pessoasAutorizadasDao) {
        this.pessoasAutorizadasDao = pessoasAutorizadasDao;
    }

    @Override
    public PessoasAutorizadasDao<PessoasAutorizadas> getVetfaunaDao() {
        return pessoasAutorizadasDao;
    }
}
