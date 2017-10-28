package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.fornecedor.FornecedorDao;
import br.com.chronos.vetfaunasistem.dao.fornecedor.FornecedorDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Fornecedor;
import br.com.chronos.vetfaunasistema.domain.TelefoneContato;
import br.com.chronos.vetfaunasistema.domain.TipoTelefoneContato;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class FornecedorDaoTest {

    private FornecedorDao<Fornecedor> fornecedorDao;

    @Before
    public void instanceEntiteDao() {
        fornecedorDao = new FornecedorDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        Fornecedor fornecedor = new Fornecedor();
        List<Fornecedor> l = fornecedorDao.select(fornecedor);
        Assert.assertEquals(1,l.size());
    }
    @Test
    public void testeSucessInsert(){
        Fornecedor fornecedor = getEntidade();
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
        fornecedor.setTelefoneContato(list);
        fornecedorDao.insert(fornecedor);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessUpdate(){
        Fornecedor fornecedor = getEntidade();
        fornecedor.setIdFornecedor(1);
        fornecedorDao.update(fornecedor);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessDelete(){
        Fornecedor fornecedor = getEntidade();
        fornecedor.setIdFornecedor(1);
        List<TelefoneContato> list = new ArrayList<>();
        TelefoneContato tc = new TelefoneContato();
        TipoTelefoneContato ttc = new TipoTelefoneContato();
        ttc.setIdTipoContato(1);
        tc.setIdTelefoneContato(5);
        tc.setTipoTelefoneContato(ttc);
        tc.setStatusContato(StatusRegistro.DELETE);
        list.add(tc);
        fornecedor.setTelefoneContato(list);
        fornecedorDao.insert(fornecedor);
        fornecedorDao.delete(fornecedor);
        Assert.assertTrue(true);
    }

    private Fornecedor getEntidade() {
        Fornecedor f = new Fornecedor();
        f.setNome("Fernando");
        return f;
    }
}
