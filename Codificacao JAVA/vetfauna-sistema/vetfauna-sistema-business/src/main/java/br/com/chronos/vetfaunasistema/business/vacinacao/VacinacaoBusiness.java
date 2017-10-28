/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.vacinacao;

import br.com.chronos.vetfaunasistema.business.vacinacao.exception.VacinacaoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusiness;
import br.com.chronos.vetfaunasistema.domain.Vacinacao;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface VacinacaoBusiness extends GenericBusiness<Vacinacao, VacinacaoBusinessException> {

    public void insert(List<Vacinacao> vacinacaoList);

    public void update(List<Vacinacao> vacinacaoList);
}
