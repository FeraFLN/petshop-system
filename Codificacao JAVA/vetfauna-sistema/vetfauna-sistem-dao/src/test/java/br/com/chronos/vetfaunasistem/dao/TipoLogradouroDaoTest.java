package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.tipologradouro.TipoLogradouroDao;
import br.com.chronos.vetfaunasistem.dao.tipologradouro.TipoLogradouroDaoImpl;
import br.com.chronos.vetfaunasistema.domain.TipoLogradouro;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class TipoLogradouroDaoTest{
    private TipoLogradouroDao<TipoLogradouro> tipoLogradouroDao;
    
    @Before
    public void instanceEntiteDao(){
        tipoLogradouroDao = new TipoLogradouroDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }
    
    @Test
    public void testeConsulta() throws SQLException{
        TipoLogradouro tipoLogradouro = new TipoLogradouro();
        List<TipoLogradouro> l = tipoLogradouroDao.select(tipoLogradouro);
        tipoLogradouro = l.get(0);
        System.out.println(l.size());
        Assert.assertTrue( true );
    }
}
