/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.cliente;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Cliente;

/**
 *
 * @author Fernando
 */
public class ClienteDaoImpl extends GenericDaoImpl<Cliente> implements ClienteDao<Cliente>  {

    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.ClienteDao";
    private final String nameDomain = "Cliente";
    
    public ClienteDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml", false);
    }

    public ClienteDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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
