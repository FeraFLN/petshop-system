/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.tipotelefonecontato;

import br.com.chronos.vetfaunasistem.dao.tipotelefonecontato.TipoTelefoneContatoDao;
import br.com.chronos.vetfaunasistem.dao.tipotelefonecontato.TipoTelefoneContatoDaoImpl;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.business.tipotelefonecontato.exception.TipoTelefoneContatoBusinessException;
import br.com.chronos.vetfaunasistema.domain.TipoTelefoneContato;

/**
 *
 * @author Fernando
 */
public class TipoTelefoneContatoBusinessImpl extends GenericBusinessImpl<TipoTelefoneContato, TipoTelefoneContatoDao<TipoTelefoneContato>, TipoTelefoneContatoBusinessException> implements TipoTelefoneContatoBusiness {

    private TipoTelefoneContatoDao<TipoTelefoneContato> tipoTelefoneContatoDao;

    public TipoTelefoneContatoBusinessImpl() {
        tipoTelefoneContatoDao = new TipoTelefoneContatoDaoImpl();
    }

    public TipoTelefoneContatoBusinessImpl(TipoTelefoneContatoDao tipoTelefoneContatoDao) {
        this.tipoTelefoneContatoDao = tipoTelefoneContatoDao;
    }

    @Override
    public TipoTelefoneContatoDao<TipoTelefoneContato> getVetfaunaDao() {
        return tipoTelefoneContatoDao;
    }

}
