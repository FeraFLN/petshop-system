/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.endereco;

import br.com.chronos.vetfaunasistema.business.endereco.exception.EnderecoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusiness;
import br.com.chronos.vetfaunasistema.domain.Endereco;

/**
 *
 * @author Fernando
 */
public interface EnderecoBusiness extends GenericBusiness<Endereco, EnderecoBusinessException>  {
    public int getIdEndereco();
}
