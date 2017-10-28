/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.business.generic;

import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;
import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface GenericBusiness<E extends GenericDomain,T extends GenericBusinessException> {

    public void insert(E genericDomain)throws T;

    public void delete(E genericDomain)throws T;

    public void update(E genericDomain)throws T;

    public List<E> select(E genericDomain)throws T;
    
    public Object getId();
}
