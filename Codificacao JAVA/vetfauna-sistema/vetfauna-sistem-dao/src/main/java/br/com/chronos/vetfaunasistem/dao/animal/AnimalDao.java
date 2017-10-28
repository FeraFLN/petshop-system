/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.animal;

import br.com.chronos.vetfaunasistem.dao.generic.GenericDao;
import br.com.chronos.vetfaunasistema.domain.Animal;
import br.com.chronos.vetfaunasistema.domain.GenericDomain;

/**
 *
 * @author Fernando
 */
public interface AnimalDao <E extends GenericDomain> extends GenericDao<E> {
     
     public void getId(Animal animal);
}
