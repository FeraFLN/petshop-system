package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.estoque.EstoqueDao;
import br.com.chronos.vetfaunasistem.dao.estoque.EstoqueDaoImpl;
import br.com.chronos.vetfaunasistema.domain.CategoriaProduto;
import br.com.chronos.vetfaunasistema.domain.Estoque;
import br.com.chronos.vetfaunasistema.domain.MarcaProduto;
import br.com.chronos.vetfaunasistema.domain.Produto;
import br.com.chronos.vetfaunasistema.domain.UnidadeMedida;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class EstoqueDaoTest {

    private EstoqueDao<Estoque> estoqueDao;

    @Before
    public void instanceEntiteDao() {
        estoqueDao = new EstoqueDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        Estoque estoque = new Estoque();
        
        List<Estoque> l = estoqueDao.select(estoque);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeConsultaComCategoria() {
        Estoque estoque = new Estoque();
        List<Estoque> l = estoqueDao.select(estoque);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeConsultaComMarca() {
        Estoque estoque = new Estoque();
        List<Estoque> l = estoqueDao.select(estoque);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessInsert(){
        Estoque estoque = getEntidade();
        estoqueDao.insert(estoque);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessUpdate(){
        Estoque estoque = getEntidade();
        estoque.setIdEstoque(2);
        estoqueDao.update(estoque);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessDelete(){
        Estoque estoque = getEntidade();
        estoque.setIdEstoque(2);
        estoqueDao.delete(estoque);
        Assert.assertTrue(true);
    }
    private Estoque getEntidade(){
        Estoque estoque = new Estoque();
        estoque.setDataValidade(new GregorianCalendar(2013, 2, 24).getTime());
        estoque.setPrecoCompra(new BigDecimal("25"));
        estoque.setProduto(getProduto());
        estoque.setQuantidade(new Integer(25));
        return estoque;
    } 
    private Produto getProduto() {
        Produto p = new Produto();
        p.setIdProduto(new Integer(1));
        p.setNome("Teste");
        p.setMarcaProduto(new MarcaProduto(2));
        p.setUnidadeMedida(new UnidadeMedida(6));
        p.setValorMedida(new BigDecimal("300"));
        return p;
    }
    
}
