/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.tipologradouro;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.TipoLogradouro;

/**
 *
 * @author Fernando
 */
public class TipoLogradouroDaoImpl extends GenericDaoImpl<TipoLogradouro> implements TipoLogradouroDao<TipoLogradouro>  {

    
    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.TipoLogradouroDao";
    private final String nameDomain = "TipoLogradouro";
    public TipoLogradouroDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml",false);
    }

    public TipoLogradouroDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
        super();
        this.myBatisConnectionFactory = myBatisConnectionFactory;
    }



    public MyBatisConnectionFactory getMyBatisConnectionFactory() {
        return myBatisConnectionFactory;
    }

     
    
    public String getMapper() {
        return mapper;
    }

    @Override
    public String getNameDomain() {
        return nameDomain;
    }
}
