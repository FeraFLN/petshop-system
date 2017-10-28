/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.funcionario;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Funcionario;

/**
 *
 * @author Fernando
 */
public class FuncionarioDaoImpl extends GenericDaoImpl<Funcionario> implements FuncionarioDao<Funcionario>  {

    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.FuncionarioDao";
    private final String nameDomain = "Funcionario";
    
    public FuncionarioDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml", false);
    }

    public FuncionarioDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
        super();
        this.myBatisConnectionFactory = myBatisConnectionFactory;
    }

    @Override
    public String getMapper() {
        return mapper;
    }

    @Override
    public MyBatisConnectionFactory getMyBatisConnectionFactory() {
        return myBatisConnectionFactory;
    }

    @Override
    public String getNameDomain() {
        return nameDomain;
    }
}
