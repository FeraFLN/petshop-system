/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.animal;

import br.com.chronos.vetfaunasistem.dao.animal.AnimalDao;
import br.com.chronos.vetfaunasistem.dao.animal.AnimalDaoImpl;
import br.com.chronos.vetfaunasistema.business.animal.exception.AnimalBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusinessImpl;
import br.com.chronos.vetfaunasistema.business.vacinacao.VacinacaoBusiness;
import br.com.chronos.vetfaunasistema.business.vacinacao.VacinacaoBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Animal;

/**
 *
 * @author Fernando
 */
public class AnimalBusinessImpl extends GenericBusinessImpl<Animal, AnimalDao<Animal>, AnimalBusinessException>  implements AnimalBusiness {

    private AnimalDao<Animal> animalDao;
    private VacinacaoBusiness vacinacaoBusiness;

    public AnimalBusinessImpl() {
        animalDao = new AnimalDaoImpl();
        vacinacaoBusiness = new VacinacaoBusinessImpl();
    }

    public AnimalBusinessImpl(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @Override
    public AnimalDao<Animal> getVetfaunaDao() {
        return animalDao;
    }
    public void insert(Animal animal) throws AnimalBusinessException{
        super.insert(animal);
        vacinacaoBusiness.insert(animal.getVacinacoes());
    }
    public void update(Animal animal) throws AnimalBusinessException{
        super.update(animal);
        vacinacaoBusiness.update(animal.getVacinacoes());
    }
}
