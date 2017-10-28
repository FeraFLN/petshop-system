/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.view.component.table;

import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Align;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Align;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.TableReflection;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.exceptions.InvokeEntiteValidationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando
 */
public class MyTable extends JTable {

    private DefaultTableModel defaultTableModel;
    private Boolean[] canEdit;
    private String[] title;
    private GenericDomain domain;
    private TableReflection tableReflection;
    private List<? extends GenericDomain> list;

    public MyTable() {
    }

    public MyTable(GenericDomain domain) {
        try {
            title = tableReflection.titleTable(domain);
            defaultTableModel = new DefaultTableModel(new Object[][]{},
                    title);
            setModel(defaultTableModel);
            canEdit();
            alignColumn();
        } catch (InvokeEntiteValidationException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    private void canEdit() {
        canEdit = new Boolean[title.length];

        for (int i = 0; i < title.length; i++) {
            canEdit[i] = false;

        }
    }

    private void alignColumn() {
        DefaultTableCellRenderer rightRenderer;
        for (int i = 0; i < title.length; i++) {
            rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(Align.value(TableReflection.getAlign()[i]));
            getColumnModel().getColumn(i).setCellRenderer(rightRenderer);

        }
    }

    public GenericDomain getDomain() {
        return getDomain(this.getSelectedRow());
    }

    public GenericDomain getDomain(int index) {
        if (list != null && !list.isEmpty()) {
            return list.get(index);
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex].booleanValue();
    }

    public void fillTable(List<? extends GenericDomain> list) {
        try {
            this.list = list;
            defaultTableModel.setRowCount(0);
            for (GenericDomain genericDomain : list) {
                defaultTableModel.addRow(tableReflection.valueTable(genericDomain, title.length));
            }
        } catch (InvokeEntiteValidationException ex) {
            Logger.getLogger(MyTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cleanTable() {
        defaultTableModel.setRowCount(0);
    }
}
