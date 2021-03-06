/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MarcaProdutoPanel.java
 *
 * Created on 13/02/2013, 17:59:49
 */
package br.com.chronos.vetfaunasistema.view.marcaproduto;

import br.com.chronos.vetfaunasistema.business.categoriaproduto.CategoriaProdutoBusiness;
import br.com.chronos.vetfaunasistema.business.categoriaproduto.CategoriaProdutoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.categoriaproduto.exception.CategoriaProdutoBusinessException;
import br.com.chronos.vetfaunasistema.business.marcaproduto.MarcaProdutoBusiness;
import br.com.chronos.vetfaunasistema.business.marcaproduto.MarcaProdutoBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.CategoriaProduto;
import br.com.chronos.vetfaunasistema.domain.MarcaProduto;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import br.com.chronos.vetfaunasistema.view.generic.CrudView;
import br.com.chronos.vetfaunasistema.view.generic.GenericViewCrud;
import br.com.chronos.vetfaunasistema.view.component.jformatter.MaxLengthTextDocument;
import br.com.chronos.vetfaunasistema.view.component.table.MyTable;
import br.com.chronos.vetfaunasistema.view.main.TelaPrincipal;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class MarcaProdutoPanel extends GenericViewCrud<MarcaProduto, MarcaProdutoBusiness> {

    private MarcaProduto marcaProduto;
    private MarcaProduto marcaProdutoPesquisa;
    private MarcaProdutoBusiness marcaProdutoBusiness;
    private CategoriaProdutoBusiness categoriaProdutoBusiness;
    private final CategoriaProduto firstCategoriaProduto = new CategoriaProduto("--Selecione uma opção--");
    /** Creates new form MarcaProdutoPanel */
    
    public MarcaProdutoPanel(CrudView crudView,TelaPrincipal telaPrincipal) {
        setTelaPrincipal(telaPrincipal);
        setCrudView(crudView);
        categoriaProdutoBusiness = new CategoriaProdutoBusinessImpl();
        marcaProduto = new MarcaProduto();
        marcaProdutoPesquisa = new MarcaProduto();
        instanceMarcaProdutoBusiness();
        initComponents();
        preencheCategoria();
        actions();
    }

    private MarcaProdutoBusiness instanceMarcaProdutoBusiness() {
        if (marcaProdutoBusiness == null) {
            marcaProdutoBusiness = new MarcaProdutoBusinessImpl();
        }
        return marcaProdutoBusiness;
    }
    private void preencheCategoria() {
        try {
            List<CategoriaProduto> lista = categoriaProdutoBusiness.select(new CategoriaProduto());
            cmbCategoria.removeAllItems();
            cmbCategoriaPesquisa.removeAllItems();
            cmbCategoria.addItem(firstCategoriaProduto);
            cmbCategoriaPesquisa.addItem(firstCategoriaProduto);
            for (CategoriaProduto categoriaProduto : lista) {
                cmbCategoria.addItem(categoriaProduto);
                cmbCategoriaPesquisa.addItem(categoriaProduto);
            }
        } catch (CategoriaProdutoBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    public Component[] getRepaintComponets(){
        return new Component[]{cmbCategoria,cmbCategoriaPesquisa};
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
        tblMarcaProduto = getMyTable();
        jLabel14 = new javax.swing.JLabel();
        txtNomeMarcaProdutoPesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbCategoriaPesquisa = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeMarcaProduto = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox();
        btnFechar = new javax.swing.JButton();

        setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel6.setLayout(null);

        tblMarcaProduto.setModel(getMyTable().getModel());
        jScrollPane2.setViewportView(tblMarcaProduto);

        jPanel6.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 410, 110);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Nome:");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(5, 20, 50, 20);

        txtNomeMarcaProdutoPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtNomeMarcaProdutoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeMarcaProdutoPesquisaKeyPressed(evt);
            }
        });
        jPanel6.add(txtNomeMarcaProdutoPesquisa);
        txtNomeMarcaProdutoPesquisa.setBounds(60, 20, 260, 23);

        btnPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnPesquisa.setText("Pesquisar");
        jPanel6.add(btnPesquisa);
        btnPesquisa.setBounds(330, 20, 90, 21);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Categoria:");
        jPanel6.add(jLabel2);
        jLabel2.setBounds(5, 55, 50, 13);

        cmbCategoriaPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel6.add(cmbCategoriaPesquisa);
        cmbCategoriaPesquisa.setBounds(60, 50, 140, 23);

        add(jPanel6);
        jPanel6.setBounds(0, 0, 430, 200);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText("Nome da marca: ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(12, 27, 110, 13);

        txtNomeMarcaProduto.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 49, ""));
        txtNomeMarcaProduto.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNomeMarcaProduto.setEnabled(false);
        jPanel1.add(txtNomeMarcaProduto);
        txtNomeMarcaProduto.setBounds(12, 40, 210, 23);

        btnNovo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnNovo.setText("Novo");
        jPanel1.add(btnNovo);
        btnNovo.setBounds(20, 100, 73, 23);

        btnAlterar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setEnabled(false);
        jPanel1.add(btnAlterar);
        btnAlterar.setBounds(100, 100, 63, 21);

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(170, 100, 70, 21);

        btnExcluir.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        jPanel1.add(btnExcluir);
        btnExcluir.setBounds(245, 100, 70, 21);

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(320, 100, 80, 21);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setText("Categoria produto:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(230, 27, 120, 13);

        cmbCategoria.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cmbCategoria.setEnabled(false);
        jPanel1.add(cmbCategoria);
        cmbCategoria.setBounds(230, 40, 190, 23);

        add(jPanel1);
        jPanel1.setBounds(0, 200, 430, 150);

        btnFechar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        add(btnFechar);
        btnFechar.setBounds(320, 360, 80, 21);
    }// </editor-fold>//GEN-END:initComponents

private void txtNomeMarcaProdutoPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeMarcaProdutoPesquisaKeyPressed
    int key = evt.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
        setStatusPesquisar();
    }
}//GEN-LAST:event_txtNomeMarcaProdutoPesquisaKeyPressed

private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
    setVisible(false);
    txtNomeMarcaProdutoPesquisa.setText("");
}//GEN-LAST:event_btnFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cmbCategoria;
    private javax.swing.JComboBox cmbCategoriaPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblMarcaProduto;
    private javax.swing.JTextField txtNomeMarcaProduto;
    private javax.swing.JTextField txtNomeMarcaProdutoPesquisa;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setEnabledObjctes(boolean valor) {
        txtNomeMarcaProduto.setEnabled(valor);
        cmbCategoria.setEnabled(valor);
    }

    @Override
    public void setEmptyObjects() {
        txtNomeMarcaProduto.setText("");
        cmbCategoria.setSelectedIndex(0);
    }

    @Override
    public MarcaProduto getGenericPesquisaDomain() {
        marcaProdutoPesquisa.setDescricao(txtNomeMarcaProdutoPesquisa.getText());
        if(cmbCategoriaPesquisa.getSelectedIndex()>0){
            marcaProduto.setCategoriaProduto((CategoriaProduto)cmbCategoriaPesquisa.getSelectedItem());
        }
        return marcaProdutoPesquisa;
    }

    @Override
    protected MarcaProduto getEntidade() {
        marcaProduto.setDescricao(txtNomeMarcaProduto.getText());
        if(cmbCategoria.getSelectedIndex()>0){
            marcaProduto.setCategoriaProduto((CategoriaProduto)cmbCategoria.getSelectedItem());
        }
        return marcaProduto;
    }

    @Override
    protected void setEntidade(MarcaProduto entidade) {
        marcaProduto = (MarcaProduto) entidade;
        txtNomeMarcaProduto.setText(marcaProduto.getDescricao());
        cmbCategoria.setSelectedItem(marcaProduto.getCategoriaProduto());
    }

    @Override
    public MarcaProdutoBusiness getGenericBusiness() {
        return instanceMarcaProdutoBusiness();
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
        tblMarcaProduto = myTable;
    }
}
