package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.categoriaproduto.CategoriaProdutoDao;
import br.com.chronos.vetfaunasistem.dao.categoriaproduto.CategoriaProdutoDaoImpl;
import br.com.chronos.vetfaunasistema.domain.CategoriaProduto;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class CategoriaProdutoDaoTest{
    private CategoriaProdutoDao<CategoriaProduto> categoriaProdutoDao;
    
    @Before
    public void instanceEntiteDao(){
        categoriaProdutoDao = new CategoriaProdutoDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }
    
    @Test
    public void testeConsulta() throws SQLException{
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        List<CategoriaProduto> l = categoriaProdutoDao.select(categoriaProduto);
        System.out.println(l.size());
        Assert.assertTrue( true ); 
    }
}
