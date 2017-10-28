/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.especie;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Especie;

/**
 *
 * @author Fernando
 */
public class EspecieDaoImpl extends GenericDaoImpl<Especie> implements EspecieDao<Especie>  {

    
    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.EspecieDao";
    private final String nameDomain = "Especie";
    public EspecieDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml",false);
    }

    public EspecieDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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
