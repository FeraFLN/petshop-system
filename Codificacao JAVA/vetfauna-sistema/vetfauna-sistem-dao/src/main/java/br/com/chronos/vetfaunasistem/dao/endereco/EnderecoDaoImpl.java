/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.endereco;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Endereco;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Fernando
 */
public class EnderecoDaoImpl extends GenericDaoImpl<Endereco> implements EnderecoDao<Endereco>  {

    
    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.EnderecoDao";
    private final String nameDomain = "Endereco";
    public EnderecoDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml",false);
    }

    public EnderecoDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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
    public int getIdEndereco() {
        SqlSession sqlSession = getMyBatisConnectionFactory().openSession();
        int id;
        try {
            id = sqlSession.selectOne(getMapper() + ".selectId");
        } finally {
            sqlSession.close();
        }
        return id;
    }
   
}
