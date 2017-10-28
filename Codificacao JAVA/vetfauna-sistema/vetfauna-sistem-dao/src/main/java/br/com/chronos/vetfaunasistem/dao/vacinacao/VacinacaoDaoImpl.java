/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.vacinacao;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistem.dao.generic.GenericDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Vacinacao;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Fernando
 */
public class VacinacaoDaoImpl extends GenericDaoImpl<Vacinacao> implements VacinacaoDao<Vacinacao> {

    private MyBatisConnectionFactory myBatisConnectionFactory;
    private final String mapper = "br.com.chronos.vetfaunasistema.dao.VacinacaoDao";
    private final String nameDomain = "Vacinacao";

    public VacinacaoDaoImpl() {
        super();
        myBatisConnectionFactory = new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3.xml", false);
    }

    public VacinacaoDaoImpl(MyBatisConnectionFactory myBatisConnectionFactory) {
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

    public void insert(List<Vacinacao> vacinacaoList) {
        SqlSession sqlSession = getMyBatisConnectionFactory().openSession();
        try {
            for (Vacinacao vacinacao : vacinacaoList) {
                sqlSession.insert(getMapper() + ".insert", vacinacao);
            }
            getMyBatisConnectionFactory().commit();
        } finally {
            sqlSession.close();
        }
    }

    public void update(List<Vacinacao> vacinacaoList) {
        SqlSession sqlSession = getMyBatisConnectionFactory().openSession();
        try {
            for (Vacinacao vacinacao : vacinacaoList) {
                if (StatusRegistro.NOVO.equals(vacinacao.getStatusRegistro())) {
                    sqlSession.insert(getMapper() + ".insert", vacinacao);
                } else if (StatusRegistro.EXISTE.equals(vacinacao.getStatusRegistro())) {
                    sqlSession.update(getMapper() + ".update", vacinacao);
                }else if(StatusRegistro.DELETE.equals(vacinacao.getStatusRegistro())){
                    sqlSession.delete(getMapper() + ".delete", vacinacao);
                }
            }
            getMyBatisConnectionFactory().commit();
        } finally {
            sqlSession.close();
        }
    }
}
