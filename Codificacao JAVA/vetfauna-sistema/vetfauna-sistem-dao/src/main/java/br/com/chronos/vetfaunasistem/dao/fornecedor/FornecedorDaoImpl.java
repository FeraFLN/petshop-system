/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.fornecedor;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Fornecedor;

/**
 *
 * @author Fernando
 */
public class FornecedorDaoImpl extends GenericDaoImpl<Fornecedor> implements FornecedorDao<Fornecedor>  {

    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.FornecedorDao";
    private final String nameDomain = "Fornecedor";
    
    public FornecedorDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml", false);
    }

    public FornecedorDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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
