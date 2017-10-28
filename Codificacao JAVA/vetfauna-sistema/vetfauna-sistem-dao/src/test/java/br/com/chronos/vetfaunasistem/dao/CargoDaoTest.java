package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.cargo.CargoDao;
import br.com.chronos.vetfaunasistem.dao.cargo.CargoDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Cargo;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class CargoDaoTest{
    private CargoDao<Cargo> cargoDao;
    
    @Before
    public void instanceEntiteDao(){
        cargoDao = new CargoDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }
    
    @Test
    public void testeConsulta() throws SQLException{
        Cargo cargo = new Cargo();
        cargo.setDescricao("ble");
        cargoDao.insert(cargo);
        cargo.setDescricao("bli");
        List<Cargo> l = cargoDao.select(cargo);
        cargo = l.get(0);
        System.out.println(l.size());
        cargo.setDescricao("teste");
        cargoDao.update(cargo);
        cargo.setDescricao("bli");
        cargoDao.update(cargo);
        cargoDao.delete(cargo);
        Assert.assertTrue( true );
    }
}
