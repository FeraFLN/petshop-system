package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.vendedor.VendedorDao;
import br.com.chronos.vetfaunasistem.dao.vendedor.VendedorDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Fornecedor;
import br.com.chronos.vetfaunasistema.domain.TelefoneContato;
import br.com.chronos.vetfaunasistema.domain.TipoTelefoneContato;
import br.com.chronos.vetfaunasistema.domain.Vendedor;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class VendedorDaoTest {

    private VendedorDao<Vendedor> vendedorDao;

    @Before
    public void instanceEntiteDao() {
        vendedorDao = new VendedorDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        Vendedor vendedor = new Vendedor();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(1);
        vendedor.setFornecedor(fornecedor);
        List<Vendedor> l = vendedorDao.select(vendedor);
        Assert.assertEquals(1,l.size());
    }
    
    
    @Test
    public void testeSucessInsert(){
        Vendedor vendedor = getEntidade();
        List<TelefoneContato> list = new ArrayList<>();
        TelefoneContato tc = new TelefoneContato();
        TipoTelefoneContato ttc = new TipoTelefoneContato();
        ttc.setIdTipoContato(1);
        tc.setDdd("85");
        tc.setNumero("98982626");
        tc.setStatusContato(StatusRegistro.NOVO);
        tc.setTipoTelefoneContato(ttc);
        list.add(tc);
        tc = new TelefoneContato();
        tc.setDdd("85");
        tc.setTipoTelefoneContato(ttc);
        tc.setNumero("98982625");
        tc.setStatusContato(StatusRegistro.NOVO);
        list.add(tc);
        vendedor.setTelefoneContato(list);
        vendedorDao.insert(vendedor);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessUpdate(){
        Vendedor vendedor = getEntidade();
        vendedor.setIdVendedor(1);
        vendedorDao.update(vendedor);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessDelete(){
        Vendedor vendedor = getEntidade();
        vendedor.setIdVendedor(1);
        List<TelefoneContato> list = new ArrayList<>();
        TelefoneContato tc = new TelefoneContato();
        TipoTelefoneContato ttc = new TipoTelefoneContato();
        ttc.setIdTipoContato(1);
        tc.setIdTelefoneContato(5);
        tc.setTipoTelefoneContato(ttc);
        tc.setStatusContato(StatusRegistro.DELETE);
        list.add(tc);
        vendedor.setTelefoneContato(list);
        vendedorDao.insert(vendedor);
        vendedorDao.delete(vendedor);
        Assert.assertTrue(true);
    }

    private Vendedor getEntidade() {
        Vendedor f = new Vendedor();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(1);
        f.setFornecedor(fornecedor);
        f.setNome("Fernando");
        return f;
    }
}
