/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.view.component;

import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import java.util.List;

/**
 *
 * @author fernandoneto
 */
public interface Repaint {
    
    public void repaint(List<? extends GenericDomain> list,Class<?> clazz);
    
}
