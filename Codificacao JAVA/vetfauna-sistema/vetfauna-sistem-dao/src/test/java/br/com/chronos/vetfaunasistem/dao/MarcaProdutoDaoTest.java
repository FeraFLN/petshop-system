package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.marcaproduto.MarcaProdutoDao;
import br.com.chronos.vetfaunasistem.dao.marcaproduto.MarcaProdutoDaoImpl;
import br.com.chronos.vetfaunasistema.domain.MarcaProduto;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class MarcaProdutoDaoTest{
    private MarcaProdutoDao<MarcaProduto> marcaProdutoDao;
    
    @Before
    public void instanceEntiteDao(){
        marcaProdutoDao = new MarcaProdutoDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }
    
    @Test
    public void testeConsulta() throws SQLException{
        MarcaProduto marcaProduto = new MarcaProduto();
        marcaProduto.setDescricao("ble");
        marcaProdutoDao.insert(marcaProduto);
        List<MarcaProduto> l = marcaProdutoDao.select(marcaProduto);
        marcaProduto = l.get(0);
        System.out.println(l.size());
        marcaProduto.setDescricao("teste");
        marcaProdutoDao.update(marcaProduto);
        marcaProduto.setDescricao("bli");
        marcaProdutoDao.update(marcaProduto);
        marcaProdutoDao.delete(marcaProduto);
        Assert.assertTrue( true );
    }
}
