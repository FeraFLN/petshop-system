/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VacinaPanel.java
 *
 * Created on 13/02/2013, 17:59:49
 */
package br.com.chronos.vetfaunasistema.view.vacina;

import br.com.chronos.vetfaunasistema.business.vacina.VacinaBusiness;
import br.com.chronos.vetfaunasistema.business.vacina.VacinaBusinessImpl;
import br.com.chronos.vetfaunasistema.domain.Vacina;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import br.com.chronos.vetfaunasistema.view.generic.CrudView;
import br.com.chronos.vetfaunasistema.view.generic.GenericViewCrud;
import br.com.chronos.vetfaunasistema.view.component.jformatter.MaxLengthTextDocument;
import br.com.chronos.vetfaunasistema.view.component.table.MyTable;
import br.com.chronos.vetfaunasistema.view.main.TelaPrincipal;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

/**
 *
 * @author Fernando
 */
public class VacinaPanel extends GenericViewCrud<Vacina, VacinaBusiness> {

    private Vacina vacina;
    private Vacina vacinaPesquisa;
    private VacinaBusiness vacinaBusiness;

    /** Creates new form VacinaPanel */
    public VacinaPanel(CrudView crudView,TelaPrincipal telaPrincipal) {
        setCrudView(crudView);
        vacina = new Vacina();
        vacinaPesquisa = new Vacina();
        instanceVacinaBusiness();
        initComponents();
        actions();
        setTelaPrincipal(telaPrincipal);
    }

    private VacinaBusiness instanceVacinaBusiness() {
        if (vacinaBusiness == null) {
            vacinaBusiness = new VacinaBusinessImpl();
        }
        return vacinaBusiness;
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
        tblVacina = getMyTable();
        jLabel14 = new javax.swing.JLabel();
        txtNomePesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeVacina = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel6.setLayout(null);

        tblVacina.setModel(getMyTable().getModel());
        jScrollPane2.setViewportView(tblVacina);

        jPanel6.add(jScrollPane2);
        jScrollPane2.setBounds(10, 60, 410, 130);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel14.setText("Nome:");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(20, 20, 50, 20);

        txtNomePesquisa.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNomePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomePesquisaKeyPressed(evt);
            }
        });
        jPanel6.add(txtNomePesquisa);
        txtNomePesquisa.setBounds(60, 20, 260, 23);

        btnPesquisa.setText("Pesquisar");
        jPanel6.add(btnPesquisa);
        btnPesquisa.setBounds(330, 20, 90, 23);

        add(jPanel6);
        jPanel6.setBounds(0, 0, 430, 200);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Vacina"));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("Nome da vacina: ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(12, 27, 110, 13);

        txtNomeVacina.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 49, ""));
        txtNomeVacina.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNomeVacina.setEnabled(false);
        jPanel1.add(txtNomeVacina);
        txtNomeVacina.setBounds(12, 40, 280, 23);

        btnNovo.setText("Novo");
        jPanel1.add(btnNovo);
        btnNovo.setBounds(20, 100, 73, 23);

        btnAlterar.setText("Alterar");
        btnAlterar.setEnabled(false);
        jPanel1.add(btnAlterar);
        btnAlterar.setBounds(100, 100, 65, 23);

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(170, 100, 70, 23);

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        jPanel1.add(btnExcluir);
        btnExcluir.setBounds(245, 100, 70, 23);

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(320, 100, 80, 23);

        add(jPanel1);
        jPanel1.setBounds(0, 200, 430, 150);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        add(btnFechar);
        btnFechar.setBounds(320, 360, 80, 23);
    }// </editor-fold>//GEN-END:initComponents

private void txtNomePesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomePesquisaKeyPressed
    int key = evt.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
        setStatusPesquisar();
    }
}//GEN-LAST:event_txtNomePesquisaKeyPressed

private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
    setVisible(false);
    txtNomePesquisa.setText("");
}//GEN-LAST:event_btnFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVacina;
    private javax.swing.JTextField txtNomePesquisa;
    private javax.swing.JTextField txtNomeVacina;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setEnabledObjctes(boolean valor) {
        txtNomeVacina.setEnabled(valor);
    }

    @Override
    public void setEmptyObjects() {
        txtNomeVacina.setText("");
    }

    @Override
    public Vacina getGenericPesquisaDomain() {
        vacinaPesquisa.setDescricao(txtNomePesquisa.getText());
        return vacinaPesquisa;
    }

    @Override
    protected Vacina getEntidade() {
        vacina.setDescricao(txtNomeVacina.getText());
        return vacina;
    }

    @Override
    protected void setEntidade(Vacina entidade) {
        vacina = (Vacina) entidade;
        txtNomeVacina.setText(vacina.getDescricao());
    }

    @Override
    public VacinaBusiness getGenericBusiness() {
        return instanceVacinaBusiness();
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
        tblVacina = myTable;
    }
}
