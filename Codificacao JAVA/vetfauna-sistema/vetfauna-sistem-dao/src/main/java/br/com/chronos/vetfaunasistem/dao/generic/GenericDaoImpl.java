/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.generic;

import br.com.chronos.vetfaunasistem.dao.MyBatisConnectionFactory;
import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import br.com.chronos.vetfaunasistema.domain.GenericDomainWithContact;
import br.com.chronos.vetfaunasistema.domain.TelefoneContato;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Fernando
 */
public abstract class GenericDaoImpl<E extends GenericDomain> implements GenericDao<E> {

    private SqlSession sqlSession;

    
    /**
     * Recebe o nome do pacote da entidade a ser utilizado pelo mapper
     * @return
     */
    public abstract String getMapper();
    public abstract String getNameDomain();

    public abstract MyBatisConnectionFactory getMyBatisConnectionFactory();

    @Override
    public void insert(E genericDomain) {
        sqlSession = getMyBatisConnectionFactory().openSession();
        try {
            setIdGenericDomain(genericDomain);
            sqlSession.insert(getMapper() + ".insert", genericDomain);
            actionContato(genericDomain);
            getMyBatisConnectionFactory().commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delete(E genericDomain) {
        sqlSession = getMyBatisConnectionFactory().openSession();
        try {
            actionContato(genericDomain);
            sqlSession.delete(getMapper() + ".delete", genericDomain);
            getMyBatisConnectionFactory().commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(E genericDomain) {
        sqlSession = getMyBatisConnectionFactory().openSession();
        try {
            actionContato(genericDomain);
            sqlSession.update(getMapper() + ".update", genericDomain);
            getMyBatisConnectionFactory().commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<E> select(E genericDomain) {
        sqlSession = getMyBatisConnectionFactory().openSession();
        List<E> list;
        try {
            list = sqlSession.selectList(getMapper() + ".selectLikeDescricao", genericDomain);
        } finally {
            sqlSession.close();
        }
        return list;
    }
    @Override
    public Object getId() {
        sqlSession = getMyBatisConnectionFactory().openSession();
        Object id;
        try {
            id = sqlSession.selectOne(getMapper() + ".selectId");
        } finally {
            sqlSession.close();
        }
        return id;
    }

    private void setIdGenericDomain(E genericDomain) {
        if (genericDomain instanceof GenericDomainWithContact) {
            GenericDomainWithContact domainContact = (GenericDomainWithContact) genericDomain;
            domainContact.setIdGenericDomain(sqlSession.selectOne(getMapper() + ".selectIdDomain"));
        }
    }

    private void actionContato(E genericDomain) {
        if (genericDomain instanceof GenericDomainWithContact) {
            String map = "br.com.chronos.vetfaunasistema.dao.TelefoneContatoDao";
            GenericDomainWithContact domainContact = (GenericDomainWithContact) genericDomain;
            if (domainContact.getTelefoneContato() == null) {
                return;
            }
            for (TelefoneContato telefoneContato : domainContact.getTelefoneContato()) {
                if (telefoneContato.getStatusContato().equals(StatusRegistro.DELETE)) {
                    sqlSession.delete(map + ".deleteContato"+getNameDomain(), telefoneContato);
                    sqlSession.delete(map + ".deleteContato", telefoneContato);
                } else if (telefoneContato.getStatusContato().equals(StatusRegistro.NOVO)) {
                    Integer id = sqlSession.selectOne(map + ".selectIdContato");
                    telefoneContato.setIdTelefoneContato(id);
                    telefoneContato.setGenericDomainWithContact(domainContact);
                    sqlSession.insert(map + ".insertContato", telefoneContato);
                    sqlSession.insert(map + ".insertContato"+getNameDomain(), telefoneContato);
                }

            }
        }
    }
}
