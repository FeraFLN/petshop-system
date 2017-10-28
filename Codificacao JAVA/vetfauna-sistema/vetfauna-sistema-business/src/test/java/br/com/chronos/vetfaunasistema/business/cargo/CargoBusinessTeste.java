package br.com.chronos.vetfaunasistema.business.cargo;

import br.com.chronos.vetfaunasistem.dao.cargo.CargoDao;
import br.com.chronos.vetfaunasistema.business.cargo.exception.CargoBusinessException;
import br.com.chronos.vetfaunasistema.domain.Cargo;
import static org.mockito.Mockito.*;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CargoBusinessTeste
        extends TestCase {

    private CargoBusiness cargoBusiness;
    private CargoDao<Cargo> cargoDao = mock(CargoDao.class);

    public CargoBusinessTeste() {
        // cargoBusiness = new CargoBusinessImpl();
        cargoBusiness = new CargoBusinessImpl(cargoDao);
    }

    @Test
    public void testeSucessInsertCargo() {
        Cargo c = new Cargo();
        c.setDescricao("teste");
        try {
            cargoBusiness.insert(c);
        } catch (CargoBusinessException ex) {
            Assert.fail("Teste da entidade CargoBusiness (insert) deu erro.");
        }
    }

    @Test
    public void testeFailInsertCargoNull() {
        try {
            cargoBusiness.insert(null);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("Entidade está nula.", ex.getMessage());
        }
    }

    @Test
    public void testeFailInsertCargoDescricaoNull() {
        Cargo c = new Cargo();
        try {
            cargoBusiness.insert(c);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("O cargo não foi informado.", ex.getMessage());
        }
    }
    @Test
    public void testeFailInsertCargoDescricaoVazia() {
        Cargo c = new Cargo();
        try {
            c.setDescricao("");
            cargoBusiness.insert(c);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("O cargo não foi informado.", ex.getMessage());
        }
    }

    @Test
    public void testeSucessSelectLikeDescricao() {
        Cargo c = new Cargo();
        try {
            cargoBusiness.select(c);
        } catch (CargoBusinessException ex) {
            Assert.fail("Teste da entidade CargoBusiness (SelectLikeDescricao) deu erro.");
        }
    }
    
    @Test
    public void testeSucessUpdatetCargo() {
        Cargo c = new Cargo();
        c.setDescricao("teste");
        c.setIdCargo(new Integer(1));
        try {
            cargoBusiness.update(c);
        } catch (CargoBusinessException ex) {
            Assert.fail("Teste da entidade CargoBusiness (update) deu erro.");
        }
    }

    @Test
    public void testeFailUpdateCargoNull() {
        try {
            cargoBusiness.update(null);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("Entidade está nula.", ex.getMessage());
        }
    }

    @Test
    public void testeFailUpdateCargoDescricaoNull() {
        Cargo c = new Cargo();
        try {
            cargoBusiness.insert(c);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("O cargo não foi informado.", ex.getMessage());
        }
    }
    @Test
    public void testeFailUpdateCargoDescricaoVazia() {
        Cargo c = new Cargo();
        try {
            c.setDescricao("");
            cargoBusiness.insert(c);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("O cargo não foi informado.", ex.getMessage());
        }
    }
    @Test
    public void testeFailUpdateCargoIdNull() {
        Cargo c = new Cargo();
        try {
            c.setDescricao("teste");
            cargoBusiness.insert(c);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("O codigo do cargo não foi informado.", ex.getMessage());
        }
    }
    
    @Test
    public void testeSucessDeleteCargo() {
        Cargo c = new Cargo();
        c.setIdCargo(1);
        c.setIdCargo(new Integer(1));
        try {
            cargoBusiness.delete(c);
        } catch (CargoBusinessException ex) {
            Assert.fail("Teste da entidade CargoBusiness (delete) deu erro.");
        }
    }

    @Test
    public void testeFailDeleteCargoNull() {
        try {
            cargoBusiness.insert(null);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("Entidade está nula.", ex.getMessage());
        }
    }

    @Test
    public void testeFailDeleteCargoIdNull() {
        Cargo c = new Cargo();
        try {
            cargoBusiness.delete(c);
        } catch (CargoBusinessException ex) {
            Assert.assertEquals("O código do cargo não foi informado.", ex.getMessage());
        }
    }
    
}
