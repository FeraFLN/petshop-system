/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author Fernando
 */
public class MyBatisConnectionFactory {

    private SqlSessionFactory sqlSessionFactory;
    private boolean transactionRollBack;
    private SqlSession sqlSession;
//    private  String resource = "vetfauna-sistema-dao-sqlmap-config-mybatis3.xml";

    public MyBatisConnectionFactory(String resource,boolean transactionRollBack) {
        this.transactionRollBack = transactionRollBack;
        constructor(resource);
    }

    
    private void constructor(String resource) {
        try {
            Reader reader = Resources.getResourceAsReader(resource);

            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println(fileNotFoundException.getMessage());
        } catch (IOException iOException) {
            System.err.println(iOException.getMessage());
        }
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
    
    public SqlSession openSession(){
        if(sqlSessionFactory!=null){
            sqlSession = sqlSessionFactory.openSession();
        }
        return sqlSession;
    }
    
    public void commit() {
        if(transactionRollBack){
            sqlSession.rollback();
        }else{
            sqlSession.commit();
        }
    }
}