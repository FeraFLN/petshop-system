/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ContactJPanel.java
 *
 * Created on 12/02/2013, 20:27:40
 */
package br.com.chronos.vetfaunasistema.view.generic;

import br.com.chronos.vetfaunasistema.business.tipotelefonecontato.TipoTelefoneContatoBusiness;
import br.com.chronos.vetfaunasistema.business.tipotelefonecontato.TipoTelefoneContatoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.tipotelefonecontato.exception.TipoTelefoneContatoBusinessException;
import br.com.chronos.vetfaunasistema.domain.TelefoneContato;
import br.com.chronos.vetfaunasistema.domain.TipoTelefoneContato;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import br.com.chronos.vetfaunasistema.view.component.jformatter.NumberJFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fernando
 */
public class ContactJPanel extends javax.swing.JPanel {

    private TipoTelefoneContatoBusiness contatoBusiness;
    private DefaultTableModel defaultTableModel;
    private List<TelefoneContato> listOriginal;
    private List<TelefoneContato> listFake;
    private TelefoneContato t;

    /** Creates new form ContactJPanel */
    public ContactJPanel() {
        initComponents();
        t = new TelefoneContato();
        listOriginal = new ArrayList<>();
        listFake = new ArrayList<>();
        contatoBusiness = new TipoTelefoneContatoBusinessImpl();
        preencheCombo();
        defaultTableModel = new DefaultTableModel(new Object[][]{},
                new Object[]{"Tipo Contato", "Número"});
        tblContato.setModel(defaultTableModel);
    }

    private void preencheCombo() {
        List<TipoTelefoneContato> list;
        try {
            list = contatoBusiness.select(new TipoTelefoneContato());
            cmbTipoContato.removeAllItems();
            cmbTipoContato.addItem("--Selecione uma opção");
            for (TipoTelefoneContato tipoTelefoneContato : list) {
                cmbTipoContato.addItem(tipoTelefoneContato);
            }
        } catch (TipoTelefoneContatoBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    public void setEnabled(boolean valor) {
        txtDdd.setEnabled(valor);
        txtNumero.setEnabled(valor);
        cmbTipoContato.setEnabled(valor);
        tblContato.setEnabled(valor);
        btnIncluir.setEnabled(valor);
        btnRemover.setEnabled(valor);
    }

    public void setEmptyObjects() {
        setEmpty();
        listOriginal.clear();
        listFake.clear();
        defaultTableModel.setRowCount(0);
    }

    private void setEmpty() {
        txtDdd.setText("");
        txtNumero.setText("");
        cmbTipoContato.setSelectedIndex(0);

    }

    public void setTableValue(List<TelefoneContato> list) {
        if (list != null) {
            listOriginal = new ArrayList<>(list);
            listFake = new ArrayList<>(list);
        }
        setTableValue();

    }

    private void setTableValue() {
        defaultTableModel.setRowCount(0);
        for (TelefoneContato telefone : listFake) {
            String tipoContato = telefone.getTipoTelefoneContato().getDescricao();
            String t = "(" + telefone.getDdd() + ") " + telefone.getNumero();
            defaultTableModel.addRow(new String[]{tipoContato, t});
        }
    }

    private TelefoneContato getTelefoneContato() {
        t = new TelefoneContato();
        t.setDdd(txtDdd.getText());
        t.setNumero(txtNumero.getText());
        t.setTipoTelefoneContato((TipoTelefoneContato) cmbTipoContato.getSelectedItem());
        return t;
    }

    private void setTelefoneContato(TelefoneContato telefoneContato, StatusRegistro statusContato) {
        txtDdd.setText(telefoneContato.getDdd());
        txtNumero.setText(telefoneContato.getNumero());
        cmbTipoContato.setSelectedItem(telefoneContato.getTipoTelefoneContato());
        setStatusContato(telefoneContato, statusContato);
    }

    private void setStatusContato(TelefoneContato telefoneContato, StatusRegistro statusContato) {
        if (listOriginal.contains(telefoneContato)) {//SE JÁ ESTÁ NA LISTA ORIGINAL VERIFICAR O STATUS
            for (int i = 0; i < listOriginal.size(); i++) {
                if (listOriginal.get(i).equals(telefoneContato) && statusContato.equals(StatusRegistro.DELETE)) {
                    if (listOriginal.get(i).getStatusContato().equals(StatusRegistro.NOVO)) {//SE O STATUS EH NOVO ENTÃO EU POSSO REMOVER DA LISTA
                        listOriginal.remove(telefoneContato);
                        return;
                    } else if (listOriginal.get(i).getStatusContato().equals(StatusRegistro.EXISTE)) {//SE ELE JA EXISTE SETAR PARA REMOVER DO BANCO
                        listOriginal.get(i).setStatusContato(statusContato);
                        return;
                    }
                }
            }
        } else {
            telefoneContato.setStatusContato(StatusRegistro.NOVO);
            listOriginal.add(telefoneContato);
        }

    }

    public List<TelefoneContato> getListOriginal() {
        return listOriginal;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbTipoContato = new javax.swing.JComboBox();
        txtDdd = new NumberJFormatter(br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask.INTEGER,2,"");
        txtNumero = new NumberJFormatter(br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask.FONE,9,"");
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContato = new javax.swing.JTable();
        btnIncluir = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

        setLayout(null);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Contatos"));
        jPanel5.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setText("DDD:");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(12, 27, 50, 13);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel12.setText("Número:");
        jPanel5.add(jLabel12);
        jLabel12.setBounds(50, 27, 110, 13);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel13.setText("Tipo de Contato:");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(12, 70, 130, 13);

        cmbTipoContato.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel5.add(cmbTipoContato);
        cmbTipoContato.setBounds(12, 84, 140, 23);

        txtDdd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel5.add(txtDdd);
        txtDdd.setBounds(10, 40, 30, 23);

        txtNumero.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel5.add(txtNumero);
        txtNumero.setBounds(50, 40, 100, 23);

        add(jPanel5);
        jPanel5.setBounds(0, 0, 170, 120);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefones para contato"));
        jPanel4.setLayout(null);

        tblContato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Contato", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContato.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblContato);
        tblContato.getColumnModel().getColumn(0).setResizable(false);
        tblContato.getColumnModel().getColumn(1).setResizable(false);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 270, 90);

        add(jPanel4);
        jPanel4.setBounds(230, 0, 290, 120);

        btnIncluir.setText(">");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });
        add(btnIncluir);
        btnIncluir.setBounds(180, 40, 40, 23);

        btnRemover.setText("<");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        add(btnRemover);
        btnRemover.setBounds(180, 70, 40, 23);
    }// </editor-fold>//GEN-END:initComponents

private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
    if (tblContato.getSelectedRows().length != 1) {
        JOptionPane.showMessageDialog(null, "Selecione um registro.", "Erro", 0);
        return;
    }
    setTelefoneContato(listFake.get(tblContato.getSelectedRow()), StatusRegistro.DELETE);
    listFake.remove(tblContato.getSelectedRow());
    setTableValue();
}//GEN-LAST:event_btnRemoverActionPerformed

    private boolean validation() {
        boolean valor = true;
        valor = testNullOrEmpty(txtDdd.getText(), "DDD");
        if (!valor) {
            return valor;
        }
        valor = testNullOrEmpty(txtNumero.getText(), "Número");
        if (!valor) {
            return valor;
        }
        if (cmbTipoContato.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Campo referente ao tipo de contato deve ser preenchido.", "Erro", 0);
            return false;
        }

        return valor;
    }

    private boolean testNullOrEmpty(String valor, String nome) {
        if (valor == null || valor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo referente ao [nome] deve ser preenchido.".replace("[nome]", nome), "Erro", 0);
            return false;
        }
        return true;

    }
private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
    if (validation()) {
        TelefoneContato telefoneContato = getTelefoneContato();
        if (listFake.contains(telefoneContato)) {
            JOptionPane.showMessageDialog(null, "Esse telefone já foi incluído.", "Erro", 0);
            return;
        }
        listFake.add(telefoneContato);
        setStatusContato(getTelefoneContato(), StatusRegistro.NOVO);
        setTableValue();
        setEmpty();
    }
}//GEN-LAST:event_btnIncluirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox cmbTipoContato;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblContato;
    private javax.swing.JFormattedTextField txtDdd;
    private javax.swing.JFormattedTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
