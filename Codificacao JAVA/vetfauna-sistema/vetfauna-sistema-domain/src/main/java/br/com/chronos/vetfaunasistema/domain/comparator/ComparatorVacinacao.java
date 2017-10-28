/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.domain.comparator;

import br.com.chronos.vetfaunasistema.domain.Vacinacao;
import java.util.Comparator;

/**
 *
 * @author Fernando
 */
public class ComparatorVacinacao implements Comparator<Vacinacao> {

    @Override
    public int compare(Vacinacao o1, Vacinacao o2) {
        if (o1.getDataVacinacao() == null && o2.getDataVacinacao() == null) {
            return 0;
        } else if (o1.getDataVacinacao() != null && o2.getDataVacinacao() == null) {
            return -1;
        } else if (o1.getDataVacinacao() == null && o2.getDataVacinacao() != null) {
            return 1;
        } else {
            if (o1.getDataVacinacao().compareTo(o2.getDataVacinacao()) == 0) {
                if ((o1.getVacina() == null || o1.getVacina().getDescricao() == null)
                        && (o2.getVacina() == null || o1.getVacina().getDescricao() == null)) {
                    return 0;
                } else if ((o1.getVacina() != null && o1.getVacina().getDescricao() != null)
                        && (o2.getVacina() == null || o1.getVacina().getDescricao() == null)) {
                    return 1;
                } else if ((o1.getVacina() == null || o1.getVacina().getDescricao() == null)
                        && (o2.getVacina() != null && o1.getVacina().getDescricao() != null)) {
                    return -1;
                } else {
                    if (o1.getVacina().getDescricao().toLowerCase().compareTo(o2.getVacina().getDescricao().toLowerCase()) == 0) {
                        return o1.getNomeMarca().toLowerCase().compareTo(o2.getNomeMarca().toLowerCase());
                    }
                }
            } else {
                return -o1.getDataVacinacao().compareTo(o2.getDataVacinacao());
            }
        }
        return 0;
    }
}
