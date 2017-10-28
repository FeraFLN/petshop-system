/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.util;

import br.com.chronos.vetfaunasistema.utilitarios.validatios.Table;
import java.util.Comparator;

/**
 *
 * @author Fernando
 */
public class TableComparator implements Comparator<Table> {

    public int compare(Table table, Table otherTable) {
        return table.compareTo(otherTable);
    }
}
