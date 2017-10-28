/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.pessoasautorizadas;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.PessoasAutorizadas;

/**
 *
 * @author Fernando
 */
public class PessoasAutorizadasDaoImpl extends GenericDaoImpl<PessoasAutorizadas> implements PessoasAutorizadasDao<PessoasAutorizadas>  {

    
    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.PessoasAutorizadasDao";
    private final String nameDomain = "PessoasAutorizadas";
    public PessoasAutorizadasDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml",false);
    }

    public PessoasAutorizadasDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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
