package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.unidademedida.UnidadeMedidaDao;
import br.com.chronos.vetfaunasistem.dao.unidademedida.UnidadeMedidaDaoImpl;
import br.com.chronos.vetfaunasistema.domain.UnidadeMedida;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class UnidadeMedidaDaoTest{
    private UnidadeMedidaDao<UnidadeMedida> unidadeMedidaDao;
    
    @Before
    public void instanceEntiteDao(){
        unidadeMedidaDao = new UnidadeMedidaDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }
    
    @Test
    public void testeConsulta() throws SQLException{
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setDescricao("Uni");
        List<UnidadeMedida> l = unidadeMedidaDao.select(unidadeMedida);
        unidadeMedida = l.get(0);
        Assert.assertTrue( true );
    }
}
