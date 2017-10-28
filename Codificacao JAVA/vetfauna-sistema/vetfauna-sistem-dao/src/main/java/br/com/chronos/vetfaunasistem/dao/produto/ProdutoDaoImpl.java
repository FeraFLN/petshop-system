/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.produto;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Produto;

/**
 *
 * @author Fernando
 */
public class ProdutoDaoImpl extends GenericDaoImpl<Produto> implements ProdutoDao<Produto>  {

    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.ProdutoDao";
    private final String nameDomain = "Produto";
    
    public ProdutoDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml", false);
    }

    public ProdutoDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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
