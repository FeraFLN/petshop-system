/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.animal;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Animal;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Fernando
 */
public class AnimalDaoImpl extends GenericDaoImpl<Animal> implements AnimalDao<Animal> {

    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.AnimalDao";
    private final String nameDomain = "Animal";

    public AnimalDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml", false);
    }

    public AnimalDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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

    public void getId(Animal animal) {
        SqlSession sqlSession = getMyBatisConnectionFactory().openSession();
        try {
            int i = sqlSession.selectOne(getMapper() + ".selectIdDomain");
            animal.setIdAnimal(i);
        } finally {
            sqlSession.close();
        }
    }
}
