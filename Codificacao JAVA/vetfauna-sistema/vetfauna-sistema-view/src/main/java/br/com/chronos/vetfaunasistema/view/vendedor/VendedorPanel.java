/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VendedorPanel.java
 *
 * Created on 13/02/2013, 17:59:49
 */
package br.com.chronos.vetfaunasistema.view.vendedor;

import br.com.chronos.vetfaunasistema.business.fornecedor.FornecedorBusiness;
import br.com.chronos.vetfaunasistema.business.fornecedor.FornecedorBusinessImpl;
import br.com.chronos.vetfaunasistema.business.fornecedor.exception.FornecedorBusinessException;
import br.com.chronos.vetfaunasistema.business.vendedor.VendedorBusiness;
import br.com.chronos.vetfaunasistema.business.vendedor.VendedorBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Fornecedor;
import br.com.chronos.vetfaunasistema.domain.Vendedor;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import br.com.chronos.vetfaunasistema.view.generic.ContactJPanel;
import br.com.chronos.vetfaunasistema.view.generic.CrudView;
import br.com.chronos.vetfaunasistema.view.generic.GenericViewCrud;
import br.com.chronos.vetfaunasistema.view.component.jformatter.MaxLengthTextDocument;
import br.com.chronos.vetfaunasistema.view.component.table.MyTable;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class VendedorPanel extends GenericViewCrud<Vendedor, VendedorBusiness> {

    private Vendedor vendedor;
    private Vendedor vendedorPesquisa;
    private VendedorBusiness vendedorBusiness;
    private FornecedorBusiness fornecedorBusiness;
    private ContactJPanel contactJPanel;
    private List<Fornecedor> listaFornecedor;
    private Fornecedor fornecedor;
    private final Fornecedor firstFornecedor = new Fornecedor("--Selecione uma Opção--");
    /** Creates new form VendedorPanel */
    public VendedorPanel(CrudView crudView) {
        setCrudView(crudView);
        fornecedor = new Fornecedor();
        vendedor = new Vendedor();
        vendedorPesquisa = new Vendedor();
        contactJPanel = new ContactJPanel();
        instanceVendedorBusiness();
        instanceFornecedorBusiness();
        initComponents();
        preencherCombosFornecedor();
        actions();
    }

    private FornecedorBusiness instanceFornecedorBusiness() {
        if (fornecedorBusiness == null) {
            fornecedorBusiness = new FornecedorBusinessImpl();
        }
        return fornecedorBusiness;
    }
     public Component[] getRepaintComponets(){
        return new Component[]{cmbFornecedor,cmbFornecedorPesquisa};
    }

    private VendedorBusiness instanceVendedorBusiness() {
        if (vendedorBusiness == null) {
            vendedorBusiness = new VendedorBusinessImpl();
        }
        return vendedorBusiness;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVendedor = getMyTable();
        jLabel14 = new javax.swing.JLabel();
        txtNomeVendedorPesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cmbFornecedorPesquisa = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeVendedor = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlContato = contactJPanel;
        cmbFornecedor = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        btnFechar = new javax.swing.JButton();

        setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel6.setLayout(null);

        tblVendedor.setModel(getMyTable().getModel());
        jScrollPane2.setViewportView(tblVendedor);

        jPanel6.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 530, 130);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel14.setText("Forncedor:");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(10, 50, 70, 20);

        txtNomeVendedorPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtNomeVendedorPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeVendedorPesquisaKeyPressed(evt);
            }
        });
        jPanel6.add(txtNomeVendedorPesquisa);
        txtNomeVendedorPesquisa.setBounds(70, 20, 260, 23);

        btnPesquisa.setText("Pesquisar");
        jPanel6.add(btnPesquisa);
        btnPesquisa.setBounds(400, 50, 90, 23);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel15.setText("Nome:");
        jPanel6.add(jLabel15);
        jLabel15.setBounds(32, 20, 50, 20);

        cmbFornecedorPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel6.add(cmbFornecedorPesquisa);
        cmbFornecedorPesquisa.setBounds(70, 50, 150, 23);

        add(jPanel6);
        jPanel6.setBounds(0, 0, 550, 220);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText("Nome do Fornecedor: ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(12, 27, 110, 13);

        txtNomeVendedor.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 149, ""));
        txtNomeVendedor.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtNomeVendedor.setEnabled(false);
        jPanel1.add(txtNomeVendedor);
        txtNomeVendedor.setBounds(12, 40, 280, 23);

        btnNovo.setText("Novo");
        jPanel1.add(btnNovo);
        btnNovo.setBounds(80, 230, 73, 23);

        btnAlterar.setText("Alterar");
        btnAlterar.setEnabled(false);
        jPanel1.add(btnAlterar);
        btnAlterar.setBounds(160, 230, 65, 23);

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(230, 230, 70, 23);

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        jPanel1.add(btnExcluir);
        btnExcluir.setBounds(305, 230, 70, 23);

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(380, 230, 80, 23);

        pnlContato.setLayout(null);
        jPanel1.add(pnlContato);
        pnlContato.setBounds(20, 70, 520, 150);

        cmbFornecedor.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(cmbFornecedor);
        cmbFornecedor.setBounds(310, 40, 150, 23);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("Fornecedor:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(310, 27, 80, 13);

        add(jPanel1);
        jPanel1.setBounds(0, 230, 550, 270);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        add(btnFechar);
        btnFechar.setBounds(450, 510, 80, 23);
    }// </editor-fold>//GEN-END:initComponents

private void txtNomeVendedorPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeVendedorPesquisaKeyPressed
    int key = evt.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
        setStatusPesquisar();
    }
}//GEN-LAST:event_txtNomeVendedorPesquisaKeyPressed

private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
    setVisible(false);
    txtNomeVendedorPesquisa.setText("");
    cmbFornecedorPesquisa.setSelectedIndex(0);
}//GEN-LAST:event_btnFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cmbFornecedor;
    private javax.swing.JComboBox cmbFornecedorPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlContato;
    private javax.swing.JTable tblVendedor;
    private javax.swing.JTextField txtNomeVendedor;
    private javax.swing.JTextField txtNomeVendedorPesquisa;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setEnabledObjctes(boolean valor) {
        txtNomeVendedor.setEnabled(valor);
        contactJPanel.setEnabled(valor);
        cmbFornecedor.setEnabled(valor);
    }

    @Override
    public void setEmptyObjects() {
        txtNomeVendedor.setText("");
        contactJPanel.setEmptyObjects();
        cmbFornecedor.setSelectedIndex(0);
    }

    @Override
    public Vendedor getGenericPesquisaDomain() {
        vendedorPesquisa.setNome(txtNomeVendedorPesquisa.getText());
        vendedorPesquisa.setFornecedor(getFornecedor(cmbFornecedorPesquisa));
        return vendedorPesquisa;
    }

    @Override
    protected Vendedor getEntidade() {
        vendedor.setNome(txtNomeVendedor.getText());
        vendedor.setTelefoneContato(contactJPanel.getListOriginal());
        vendedor.setFornecedor(getFornecedor(cmbFornecedor));
        return vendedor;
    }

    @Override
    protected void setEntidade(Vendedor entidade) {
        vendedor = (Vendedor) entidade;
        txtNomeVendedor.setText(vendedor.getNome());
        contactJPanel.setTableValue(vendedor.getTelefoneContato());
        cmbFornecedor.setSelectedItem(vendedor.getFornecedor());
    }

    @Override
    public VendedorBusiness getGenericBusiness() {
        return instanceVendedorBusiness();
    }

    @Override
    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    @Override
    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    @Override
    public JButton getBtnAlterar() {
        return btnAlterar;
    }

    @Override
    public JButton getBtnPesquisar() {
        return btnPesquisa;
    }

    @Override
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    @Override
    public JButton getBtnNovo() {
        return btnNovo;
    }

    @Override
    public void setTable(MyTable myTable) {
        tblVendedor = myTable;
    }

    private void preencherCombosFornecedor() {
        try {
            if (listaFornecedor != null) {
                listaFornecedor.clear();
            }
            listaFornecedor = fornecedorBusiness.select(fornecedor);
            cmbFornecedorPesquisa.removeAllItems();
            cmbFornecedor.removeAllItems();
            cmbFornecedor.addItem(firstFornecedor);
            cmbFornecedorPesquisa.addItem(firstFornecedor);
            for (Fornecedor f : listaFornecedor) {
                cmbFornecedor.addItem(f);
                cmbFornecedorPesquisa.addItem(f);
            }
        } catch (FornecedorBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }

    }

    private Fornecedor getFornecedor(JComboBox comboBox) {
        if (comboBox.getSelectedIndex() > 0) {
            return (Fornecedor) comboBox.getSelectedItem();
        } else {
            return null;
        }
    }
}
