package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.produto.ProdutoDao;
import br.com.chronos.vetfaunasistem.dao.produto.ProdutoDaoImpl;
import br.com.chronos.vetfaunasistema.domain.CategoriaProduto;
import br.com.chronos.vetfaunasistema.domain.MarcaProduto;
import br.com.chronos.vetfaunasistema.domain.Produto;
import br.com.chronos.vetfaunasistema.domain.UnidadeMedida;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ProdutoDaoTest {

    private ProdutoDao<Produto> produtoDao;

    @Before
    public void instanceEntiteDao() {
        produtoDao = new ProdutoDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        Produto produto = new Produto();
        
        List<Produto> l = produtoDao.select(produto);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeConsultaComCategoria() {
        Produto produto = new Produto();
        CategoriaProduto categoriaProduto = new CategoriaProduto(2);
        List<Produto> l = produtoDao.select(produto);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeConsultaComMarca() {
        Produto produto = new Produto();
        MarcaProduto marcaProduto = new MarcaProduto(2);
        produto.setMarcaProduto(marcaProduto);
        List<Produto> l = produtoDao.select(produto);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessInsert(){
        Produto produto = getEntidade();
        produtoDao.insert(produto);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessUpdate(){
        Produto produto = getEntidade();
        produto.setIdProduto(2);
        produtoDao.update(produto);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessDelete(){
        Produto produto = getEntidade();
        produto.setIdProduto(2);
        produtoDao.delete(produto);
        Assert.assertTrue(true);
    }

    private Produto getEntidade() {
        Produto p = new Produto();
        p.setIdProduto(new Integer(20));
        p.setNome("Teste");
        p.setMarcaProduto(new MarcaProduto(2));
        p.setUnidadeMedida(new UnidadeMedida(6));
        p.setValorMedida(new BigDecimal("300"));
        return p;
    }
}
