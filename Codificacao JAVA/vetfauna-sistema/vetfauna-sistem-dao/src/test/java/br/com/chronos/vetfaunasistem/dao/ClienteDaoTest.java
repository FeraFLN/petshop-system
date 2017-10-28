package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.cliente.ClienteDao;
import br.com.chronos.vetfaunasistem.dao.cliente.ClienteDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Cliente;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ClienteDaoTest {

    private ClienteDao<Cliente> clienteDao;

    @Before
    public void instanceEntiteDao() {
        clienteDao = new ClienteDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        Cliente cliente = new Cliente();
        cliente.setNome("fernand");
        List<Cliente> l = clienteDao.select(cliente);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessInsert(){
        Cliente cliente = new Cliente();
        cliente.setNome("teste");
        cliente.setEmail("teste");
        clienteDao.insert(cliente);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessUpdate(){
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setNome("Fernando Limeira");
        cliente.setEmail("Fernando Limeira");
        clienteDao.update(cliente);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessDelete(){
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        clienteDao.delete(cliente);
        Assert.assertTrue(true);
    }

}
