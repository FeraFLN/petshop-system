package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.estado.EstadoDao;
import br.com.chronos.vetfaunasistem.dao.estado.EstadoDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Estado;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class EstadoDaoTest{
    private EstadoDao<Estado> estadoDao;
    
    @Before
    public void instanceEntiteDao(){
        estadoDao = new EstadoDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }
    
    @Test
    public void testeConsulta() throws SQLException{
        Estado estado = new Estado();
        estado.setNome("Ceará");
        List<Estado> l = estadoDao.select(estado);
        estado = l.get(0);
        System.out.println(l.size());
        Assert.assertTrue( true );
    }
}
