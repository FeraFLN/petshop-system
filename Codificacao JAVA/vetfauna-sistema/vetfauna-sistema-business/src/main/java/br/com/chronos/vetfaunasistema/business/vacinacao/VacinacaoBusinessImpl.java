/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.vacinacao;

import br.com.chronos.vetfaunasistem.dao.vacinacao.VacinacaoDao;
import br.com.chronos.vetfaunasistem.dao.vacinacao.VacinacaoDaoImpl;
import br.com.chronos.vetfaunasistema.business.vacinacao.exception.VacinacaoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Vacinacao;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class VacinacaoBusinessImpl extends GenericBusinessImpl<Vacinacao, VacinacaoDao<Vacinacao>, VacinacaoBusinessException>  implements VacinacaoBusiness {

    private VacinacaoDao<Vacinacao> vacinacaoDao;

    public VacinacaoBusinessImpl() {
        vacinacaoDao = new VacinacaoDaoImpl();
    }

    public VacinacaoBusinessImpl(VacinacaoDao vacinacaoDao) {
        this.vacinacaoDao = vacinacaoDao;
    }

    @Override
    public VacinacaoDao<Vacinacao> getVetfaunaDao() {
        return vacinacaoDao;
    }
    public void insert(List<Vacinacao> vacinacaoList){
        vacinacaoDao.insert(vacinacaoList);
    }
    
    public void update(List<Vacinacao> vacinacaoList){
        vacinacaoDao.update(vacinacaoList);
    }
}
