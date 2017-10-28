/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.cliente;

import br.com.chronos.vetfaunasistem.dao.cliente.ClienteDao;
import br.com.chronos.vetfaunasistem.dao.cliente.ClienteDaoImpl;
import br.com.chronos.vetfaunasistema.business.cliente.exception.ClienteBusinessException;
import br.com.chronos.vetfaunasistema.business.endereco.EnderecoBusiness;
import br.com.chronos.vetfaunasistema.business.endereco.EnderecoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.endereco.exception.EnderecoBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Cliente;

/**
 *
 * @author Fernando
 */
public class ClienteBusinessImpl extends GenericBusinessImpl<Cliente, ClienteDao<Cliente>, ClienteBusinessException> implements ClienteBusiness {

    private ClienteDao<Cliente> clienteDao;
    private EnderecoBusiness enderecoBusiness;

    public ClienteBusinessImpl() {
        clienteDao = new ClienteDaoImpl();
        enderecoBusiness = new EnderecoBusinessImpl();
    }

    public ClienteBusinessImpl(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public ClienteDao<Cliente> getVetfaunaDao() {
        return clienteDao;
    }

    public void insert(Cliente cliente) throws ClienteBusinessException {
        if (!cliente.getEndereco().isEmpty()) {
            try {
                Integer id = enderecoBusiness.getIdEndereco();
                cliente.getEndereco().setIdEndereco(id);
                enderecoBusiness.insert(cliente.getEndereco());
            } catch (EnderecoBusinessException ex) {
                throw new ClienteBusinessException(ex.getMessage());
            }
        }
        super.insert(cliente);
    }

    public void update(Cliente cliente) throws ClienteBusinessException {
        try {
            if (cliente.getEndereco().getIdEndereco() != null
                    && cliente.getEndereco().getIdEndereco() != 0
                    && !cliente.getEndereco().isEmpty()) {
                enderecoBusiness.update(cliente.getEndereco());

            } else if (cliente.getEndereco().getIdEndereco() != null
                    && cliente.getEndereco().getIdEndereco() != 0
                    && cliente.getEndereco().isEmpty()) {

                cliente.getEndereco().setIdEndereco(null);
            } else if ((cliente.getEndereco().getIdEndereco() == null
                    || cliente.getEndereco().getIdEndereco() == 0)
                    && !cliente.getEndereco().isEmpty()) {
                Integer id = enderecoBusiness.getIdEndereco();
                cliente.getEndereco().setIdEndereco(id);
                enderecoBusiness.insert(cliente.getEndereco());
            }
            super.update(cliente);
        } catch (EnderecoBusinessException ex) {
            throw new ClienteBusinessException(ex.getMessage());
        }
    }
}
