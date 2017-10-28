package br.com.chronos.vetfaunasistem.dao;

import br.com.chronos.vetfaunasistem.dao.animal.AnimalDao;
import br.com.chronos.vetfaunasistem.dao.animal.AnimalDaoImpl;
import br.com.chronos.vetfaunasistema.domain.Animal;
import br.com.chronos.vetfaunasistema.domain.Cliente;
import br.com.chronos.vetfaunasistema.domain.Raca;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Sexo;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AnimalDaoTest {

    private AnimalDao<Animal> animalDao;

    @Before
    public void instanceEntiteDao() {
        animalDao = new AnimalDaoImpl(new MyBatisConnectionFactory("vetfauna-sistema-dao-sqlmap-config-mybatis3-junit.xml", true));
    }

    @Test
    public void testeConsulta() {
        Animal animal = new Animal();
        List<Animal> l = animalDao.select(animal);
        System.out.println("asdasd "+l.size());
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessInsert(){
        Animal animal = getEntidade();
        animalDao.insert(animal);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessUpdate(){
        Animal animal = getEntidade();
        animal.setIdAnimal(2);
        animalDao.update(animal);
        Assert.assertTrue(true);
    }
    @Test
    public void testeSucessDelete(){
        Animal animal = getEntidade();
        animal.setIdAnimal(2);
        animalDao.delete(animal);
        Assert.assertTrue(true);
    }

    private Animal getEntidade() {
        Animal p = new Animal();
        Raca r = new Raca();
        Cliente c = new Cliente();
        c.setIdCliente(1);
        r.setIdRaca(1);
        p.setIdAnimal(new Integer(20));
        p.setNome("Teste");
        p.setSexo(Sexo.M);
        p.setRaca(r);
        p.setCliente(c);
        p.setDataNascimento(new Date());
        return p;
    }
}
