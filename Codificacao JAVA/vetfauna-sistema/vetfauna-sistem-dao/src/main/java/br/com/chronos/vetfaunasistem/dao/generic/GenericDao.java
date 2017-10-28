/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistem.dao.generic;

import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface GenericDao<T extends GenericDomain> {

    public void insert(T genericDomain);

    public void delete(T genericDomain);

    public void update(T genericDomain);

    public List<T> select(T genericDomain);

    public Object getId();
}
