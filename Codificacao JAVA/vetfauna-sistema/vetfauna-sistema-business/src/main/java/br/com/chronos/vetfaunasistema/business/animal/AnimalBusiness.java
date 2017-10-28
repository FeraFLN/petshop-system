/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.animal;

import br.com.chronos.vetfaunasistema.business.animal.exception.AnimalBusinessException;
import br.com.chronos.vetfaunasistema.business.generic.GenericBusiness;
import br.com.chronos.vetfaunasistema.domain.Animal;

/**
 *
 * @author Fernando
 */
public interface AnimalBusiness extends GenericBusiness<Animal, AnimalBusinessException>  {
}
