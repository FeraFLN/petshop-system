/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.vacinacao;

import br.com.chronos.vetfaunasistem.dao.generic.GenericDao;
import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import br.com.chronos.vetfaunasistema.domain.Vacinacao;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface VacinacaoDao <E extends GenericDomain> extends GenericDao<E> {
     
     public void insert(List<Vacinacao> vacinacaoList);
     public void update(List<Vacinacao> vacinacaoList);
}
