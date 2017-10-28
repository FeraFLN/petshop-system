package br.com.chronos.vetfaunasistema.business.marcaproduto;

import br.com.chronos.vetfaunasistem.dao.marcaproduto.MarcaProdutoDao;
import br.com.chronos.vetfaunasistema.business.marcaproduto.exception.MarcaProdutoBusinessException;
import br.com.chronos.vetfaunasistema.domain.MarcaProduto;
import static org.mockito.Mockito.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MarcaProdutoBusinessTeste
        extends TestCase {

    private MarcaProdutoBusiness marcaProdutoBusiness;
    private MarcaProdutoDao<MarcaProduto> marcaProdutoDao = mock(MarcaProdutoDao.class);

    public MarcaProdutoBusinessTeste() {
        // marcaProdutoBusiness = new MarcaProdutoBusinessImpl();
        marcaProdutoBusiness = new MarcaProdutoBusinessImpl(marcaProdutoDao);
    }

    @Test
    public void testeSucessInsertMarcaProduto() {
        MarcaProduto c = new MarcaProduto();
        c.setDescricao("teste");
        try {
            marcaProdutoBusiness.insert(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.fail("Teste da entidade MarcaProdutoBusiness (insert) deu erro.");
        }
    }

    @Test
    public void testeFailInsertMarcaProdutoNull() {
        try {
            marcaProdutoBusiness.insert(null);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("Entidade está nula.", ex.getMessage());
        }
    }

    @Test
    public void testeFailInsertMarcaProdutoDescricaoNull() {
        MarcaProduto c = new MarcaProduto();
        try {
            marcaProdutoBusiness.insert(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("O marcaProduto não foi informado.", ex.getMessage());
        }
    }
    @Test
    public void testeFailInsertMarcaProdutoDescricaoVazia() {
        MarcaProduto c = new MarcaProduto();
        try {
            c.setDescricao("");
            marcaProdutoBusiness.insert(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("O marcaProduto não foi informado.", ex.getMessage());
        }
    }

    @Test
    public void testeSucessSelectLikeDescricao() {
        MarcaProduto c = new MarcaProduto();
        try {
            marcaProdutoBusiness.select(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.fail("Teste da entidade MarcaProdutoBusiness (SelectLikeDescricao) deu erro.");
        }
    }
    
    @Test
    public void testeSucessUpdatetMarcaProduto() {
        MarcaProduto c = new MarcaProduto();
        c.setDescricao("teste");
        c.setIdMarcaProduto(new Integer(1));
        try {
            marcaProdutoBusiness.update(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.fail("Teste da entidade MarcaProdutoBusiness (update) deu erro.");
        }
    }

    @Test
    public void testeFailUpdateMarcaProdutoNull() {
        try {
            marcaProdutoBusiness.update(null);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("Entidade está nula.", ex.getMessage());
        }
    }

    @Test
    public void testeFailUpdateMarcaProdutoDescricaoNull() {
        MarcaProduto c = new MarcaProduto();
        try {
            marcaProdutoBusiness.insert(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("O marcaProduto não foi informado.", ex.getMessage());
        }
    }
    @Test
    public void testeFailUpdateMarcaProdutoDescricaoVazia() {
        MarcaProduto c = new MarcaProduto();
        try {
            c.setDescricao("");
            marcaProdutoBusiness.insert(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("O marcaProduto não foi informado.", ex.getMessage());
        }
    }
    @Test
    public void testeFailUpdateMarcaProdutoIdNull() {
        MarcaProduto c = new MarcaProduto();
        try {
            c.setDescricao("teste");
            marcaProdutoBusiness.insert(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("O codigo do marcaProduto não foi informado.", ex.getMessage());
        }
    }
    
    @Test
    public void testeSucessDeleteMarcaProduto() {
        MarcaProduto c = new MarcaProduto();
        c.setIdMarcaProduto(1);
        c.setIdMarcaProduto(new Integer(1));
        try {
            marcaProdutoBusiness.delete(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.fail("Teste da entidade MarcaProdutoBusiness (delete) deu erro.");
        }
    }

    @Test
    public void testeFailDeleteMarcaProdutoNull() {
        try {
            marcaProdutoBusiness.insert(null);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("Entidade está nula.", ex.getMessage());
        }
    }

    @Test
    public void testeFailDeleteMarcaProdutoIdNull() {
        MarcaProduto c = new MarcaProduto();
        try {
            marcaProdutoBusiness.delete(c);
        } catch (MarcaProdutoBusinessException ex) {
            Assert.assertEquals("O código do marcaProduto não foi informado.", ex.getMessage());
        }
    }
    
}
