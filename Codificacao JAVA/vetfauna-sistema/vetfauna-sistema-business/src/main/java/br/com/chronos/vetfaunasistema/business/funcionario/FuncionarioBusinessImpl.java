/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.funcionario;

import br.com.chronos.vetfaunasistem.dao.funcionario.FuncionarioDao;
import br.com.chronos.vetfaunasistem.dao.funcionario.FuncionarioDaoImpl;
import br.com.chronos.vetfaunasistema.business.funcionario.exception.FuncionarioBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Funcionario;

/**
 *
 * @author Fernando
 */
public class FuncionarioBusinessImpl extends GenericBusinessImpl<Funcionario, FuncionarioDao<Funcionario>, FuncionarioBusinessException> implements FuncionarioBusiness {

    private FuncionarioDao<Funcionario> funcionarioDao;

    public FuncionarioBusinessImpl() {
        funcionarioDao = new FuncionarioDaoImpl();
    }

    public FuncionarioBusinessImpl(FuncionarioDao funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    @Override
    public FuncionarioDao<Funcionario> getVetfaunaDao() {
        return funcionarioDao;
    }

}
