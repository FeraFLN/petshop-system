/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientePanel.java
 *
 * Created on 13/02/2013, 17:59:49
 */
package br.com.chronos.vetfaunasistema.view.cliente;

import br.com.chronos.vetfaunasistema.business.animal.AnimalBusiness;
import br.com.chronos.vetfaunasistema.business.animal.AnimalBusinessImpl;
import br.com.chronos.vetfaunasistema.business.animal.exception.AnimalBusinessException;
import br.com.chronos.vetfaunasistema.business.cliente.ClienteBusiness;
import br.com.chronos.vetfaunasistema.business.cliente.ClienteBusinessImpl;
import br.com.chronos.vetfaunasistema.business.estado.EstadoBusiness;
import br.com.chronos.vetfaunasistema.business.estado.EstadoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.estado.exception.EstadoBusinessException;
import br.com.chronos.vetfaunasistema.business.pessoasautorizadas.PessoasAutorizadasBusiness;
import br.com.chronos.vetfaunasistema.business.pessoasautorizadas.PessoasAutorizadasBusinessImpl;
import br.com.chronos.vetfaunasistema.business.pessoasautorizadas.exception.PessoasAutorizadasBusinessException;
import br.com.chronos.vetfaunasistema.business.tipologradouro.TipoLogradouroBusiness;
import br.com.chronos.vetfaunasistema.business.tipologradouro.TipoLogradouroBusinessImpl;
import br.com.chronos.vetfaunasistema.business.tipologradouro.exception.TipoLogradouroBusinessException;
import br.com.chronos.vetfaunasistema.domain.Animal;
import br.com.chronos.vetfaunasistema.domain.Cliente;
import br.com.chronos.vetfaunasistema.domain.Estado;
import br.com.chronos.vetfaunasistema.domain.Municipio;
import br.com.chronos.vetfaunasistema.domain.PessoasAutorizadas;
import br.com.chronos.vetfaunasistema.domain.TipoLogradouro;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusTela;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import br.com.chronos.vetfaunasistema.view.animal.AnimalDialog;
import br.com.chronos.vetfaunasistema.view.component.FrameFather;
import br.com.chronos.vetfaunasistema.view.generic.ContactJPanel;
import br.com.chronos.vetfaunasistema.view.generic.CrudView;
import br.com.chronos.vetfaunasistema.view.generic.GenericViewCrud;
import br.com.chronos.vetfaunasistema.view.component.jformatter.MaxLengthTextDocument;
import br.com.chronos.vetfaunasistema.view.component.jformatter.NumberJFormatter;
import br.com.chronos.vetfaunasistema.view.component.table.MyTable;
import br.com.chronos.vetfaunasistema.view.pessoasautorizadas.PessoasAutorizadasDialog;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class ClientePanel extends GenericViewCrud<Cliente, ClienteBusiness> implements FrameFather {

    private Cliente cliente;
    private Cliente clientePesquisa;
    private ClienteBusiness clienteBusiness;
    private ContactJPanel contactJPanel;
    private EstadoBusiness estadoBusiness;
    private List<Estado> estado;
    private List<TipoLogradouro> logradouros;
    private TipoLogradouroBusiness logradouroBusiness;
    private MyTable animalTable;
    private MyTable pessoasAutorizadaTable;
    private AnimalDialog animalDialog;
    private PessoasAutorizadasDialog pessoasAutorizadasDialog;
    private PessoasAutorizadasBusiness pessoasAutorizadasBusiness;
    private AnimalBusiness animalBusiness;

    /** Creates new form ClientePanel */
    public ClientePanel(CrudView crudView) {
        setCrudView(crudView);
        animalBusiness = new AnimalBusinessImpl();
        cliente = new Cliente();
        clientePesquisa = new Cliente();
        contactJPanel = new ContactJPanel();
        estadoBusiness = new EstadoBusinessImpl();
        logradouroBusiness = new TipoLogradouroBusinessImpl();
        pessoasAutorizadasBusiness = new PessoasAutorizadasBusinessImpl();
        instanceClienteBusiness();
        animalTable = new MyTable(new Animal());
        pessoasAutorizadaTable = new MyTable(new PessoasAutorizadas());
        initComponents();
        preencherCmbEndereco();
        actions();
        animalDialog = new AnimalDialog(null, true, this);
    }

    private ClienteBusiness instanceClienteBusiness() {
        if (clienteBusiness == null) {
            clienteBusiness = new ClienteBusinessImpl();
        }
        return clienteBusiness;
    }

    private PessoasAutorizadasDialog instancePessoasAutorizadasDialog() {
        if (pessoasAutorizadasDialog == null) {
            pessoasAutorizadasDialog = new PessoasAutorizadasDialog(null, true, this);
        }
        return pessoasAutorizadasDialog;
    }

    private void preencherCmbEndereco() {
        try {
            estado = estadoBusiness.select(new Estado("Ceará"));
            cmbEstado.removeAllItems();
            cmbMunicipio.removeAllItems();
            cmbMunicipio.addItem("--Selecione uma opção--");
            for (Estado estado1 : estado) {
                cmbEstado.addItem(estado1);
                for (Municipio municipio : estado1.getListaMunicipio()) {
                    cmbMunicipio.addItem(municipio);
                }
            }
            preencherCmbLogradouro();
        } catch (EstadoBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR);
        }
    }

    private void preencherCmbLogradouro() {
        try {
            logradouros = logradouroBusiness.select(new TipoLogradouro());
            cmbTipoLogradouro.removeAllItems();
            cmbTipoLogradouro.addItem("--Selecione uma opção--");
            for (TipoLogradouro tipoLogradouro : logradouros) {
                cmbTipoLogradouro.addItem(tipoLogradouro);
            }
        } catch (TipoLogradouroBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR);
        }

    }

    private void preencherTblPessoasAutorizadas(List<PessoasAutorizadas> listaPessoasAutorizadas) {
        pessoasAutorizadaTable.fillTable(listaPessoasAutorizadas);
        pessoasAutorizadaTable.getModel();
    }

    private void preencherTblAnimais(List<Animal> listaAnimais) {
        animalTable.fillTable(listaAnimais);
        animalTable.getModel();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCliente = getMyTable();
        jLabel14 = new javax.swing.JLabel();
        txtNomeClientePesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        rbtAutorizadas = new javax.swing.JRadioButton();
        rbtCliente = new javax.swing.JRadioButton();
        rbtAnimal = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlContato = contactJPanel;
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbTipoLogradouro = new javax.swing.JComboBox();
        txtLogradouro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cmbMunicipio = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtCep = new NumberJFormatter(TypeMask.CEP,9,"");
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPessoasAutorizadas = pessoasAutorizadaTable;
        btnExcluirPessoasAutorizadas = new javax.swing.JButton();
        btnAlterarPessoasAutorizadas = new javax.swing.JButton();
        btnAddPessoasAutorizadas = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnimais = animalTable;
        btnAddAnimais = new javax.swing.JButton();
        btnAlterarAnimais = new javax.swing.JButton();
        btnExcluirAnimais = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel6.setLayout(null);

        tblCliente.setModel(getMyTable().getModel());
        jScrollPane2.setViewportView(tblCliente);

        jPanel6.add(jScrollPane2);
        jScrollPane2.setBounds(10, 50, 570, 100);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel14.setText("Nome:");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(20, 23, 50, 20);

        txtNomeClientePesquisa.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtNomeClientePesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeClientePesquisaKeyPressed(evt);
            }
        });
        jPanel6.add(txtNomeClientePesquisa);
        txtNomeClientePesquisa.setBounds(60, 23, 220, 23);

        btnPesquisa.setText("Pesquisar");
        jPanel6.add(btnPesquisa);
        btnPesquisa.setBounds(487, 23, 90, 23);

        buttonGroup1.add(rbtAutorizadas);
        rbtAutorizadas.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rbtAutorizadas.setText("Autorizadas");
        jPanel6.add(rbtAutorizadas);
        rbtAutorizadas.setBounds(340, 23, 77, 21);

        buttonGroup1.add(rbtCliente);
        rbtCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rbtCliente.setSelected(true);
        rbtCliente.setText("Cliente");
        jPanel6.add(rbtCliente);
        rbtCliente.setBounds(280, 23, 60, 21);

        buttonGroup1.add(rbtAnimal);
        rbtAnimal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rbtAnimal.setText("Animal");
        rbtAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAnimalActionPerformed(evt);
            }
        });
        jPanel6.add(rbtAnimal);
        rbtAnimal.setBounds(420, 23, 70, 21);

        add(jPanel6);
        jPanel6.setBounds(0, 0, 590, 160);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais"));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText("Nome do Cliente: ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(12, 20, 110, 13);

        txtNomeCliente.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 59, ""));
        txtNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtNomeCliente.setEnabled(false);
        jPanel1.add(txtNomeCliente);
        txtNomeCliente.setBounds(12, 33, 250, 23);

        btnNovo.setText("Novo");
        jPanel1.add(btnNovo);
        btnNovo.setBounds(110, 435, 73, 23);

        btnAlterar.setText("Alterar");
        btnAlterar.setEnabled(false);
        jPanel1.add(btnAlterar);
        btnAlterar.setBounds(190, 435, 65, 23);

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(260, 435, 70, 23);

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        jPanel1.add(btnExcluir);
        btnExcluir.setBounds(335, 435, 70, 23);

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(410, 435, 80, 23);

        pnlContato.setLayout(null);
        jPanel1.add(pnlContato);
        pnlContato.setBounds(40, 170, 530, 120);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("E-Mail:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(270, 20, 31, 13);

        txtEmail.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 59, ""));
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtEmail.setEnabled(false);
        jPanel1.add(txtEmail);
        txtEmail.setBounds(270, 33, 310, 23);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));
        jPanel2.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText("Tipo Log.:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 61, 70, 13);

        cmbTipoLogradouro.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbTipoLogradouro.setEnabled(false);
        jPanel2.add(cmbTipoLogradouro);
        cmbTipoLogradouro.setBounds(10, 75, 100, 23);

        txtLogradouro.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 49, ""));
        txtLogradouro.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtLogradouro.setEnabled(false);
        jPanel2.add(txtLogradouro);
        txtLogradouro.setBounds(120, 75, 170, 23);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("Logradouro:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(120, 61, 80, 13);

        txtBairro.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 29, ""));
        txtBairro.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtBairro.setEnabled(false);
        jPanel2.add(txtBairro);
        txtBairro.setBounds(300, 34, 190, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText("Bairro:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(300, 21, 90, 13);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText("Cep:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 21, 22, 13);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText("Estado:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(90, 21, 50, 13);

        cmbEstado.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbEstado.setEnabled(false);
        jPanel2.add(cmbEstado);
        cmbEstado.setBounds(90, 34, 50, 23);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText("Município:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(150, 21, 60, 13);

        cmbMunicipio.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbMunicipio.setEnabled(false);
        jPanel2.add(cmbMunicipio);
        cmbMunicipio.setBounds(150, 34, 140, 23);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText("Complemento:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(300, 61, 100, 13);

        txtComplemento.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 59, ""));
        txtComplemento.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtComplemento.setEnabled(false);
        jPanel2.add(txtComplemento);
        txtComplemento.setBounds(300, 75, 260, 23);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText("Nº:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(500, 20, 16, 13);

        txtNumero.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 5, ""));
        txtNumero.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtNumero.setEnabled(false);
        jPanel2.add(txtNumero);
        txtNumero.setBounds(500, 34, 59, 24);

        txtCep.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCep.setEnabled(false);
        txtCep.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel2.add(txtCep);
        txtCep.setBounds(10, 35, 70, 23);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 55, 570, 120);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoas Autorizadas"));
        jPanel3.setLayout(null);

        tblPessoasAutorizadas.setFont(new java.awt.Font("Tahoma", 0, 10));
        tblPessoasAutorizadas.setModel(pessoasAutorizadaTable.getModel());
        jScrollPane3.setViewportView(tblPessoasAutorizadas);
        tblPessoasAutorizadas.getColumnModel().getColumn(0).setResizable(false);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(5, 20, 270, 90);

        btnExcluirPessoasAutorizadas.setFont(new java.awt.Font("Tahoma", 0, 10));
        btnExcluirPessoasAutorizadas.setText("Excluir");
        btnExcluirPessoasAutorizadas.setEnabled(false);
        btnExcluirPessoasAutorizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirPessoasAutorizadasActionPerformed(evt);
            }
        });
        jPanel3.add(btnExcluirPessoasAutorizadas);
        btnExcluirPessoasAutorizadas.setBounds(190, 110, 63, 21);

        btnAlterarPessoasAutorizadas.setFont(new java.awt.Font("Tahoma", 0, 10));
        btnAlterarPessoasAutorizadas.setText("Alterar");
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnAlterarPessoasAutorizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarPessoasAutorizadasActionPerformed(evt);
            }
        });
        jPanel3.add(btnAlterarPessoasAutorizadas);
        btnAlterarPessoasAutorizadas.setBounds(123, 110, 63, 21);

        btnAddPessoasAutorizadas.setFont(new java.awt.Font("Tahoma", 0, 10));
        btnAddPessoasAutorizadas.setText("Inserir");
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAddPessoasAutorizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPessoasAutorizadasActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddPessoasAutorizadas);
        btnAddPessoasAutorizadas.setBounds(50, 110, 70, 21);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 290, 285, 140);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Animais"));
        jPanel4.setLayout(null);

        tblAnimais.setModel(animalTable.getModel());
        jScrollPane1.setViewportView(tblAnimais);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(5, 20, 270, 90);

        btnAddAnimais.setFont(new java.awt.Font("Tahoma", 0, 10));
        btnAddAnimais.setText("Inserir");
        btnAddAnimais.setEnabled(false);
        btnAddAnimais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAnimaisActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddAnimais);
        btnAddAnimais.setBounds(52, 110, 70, 21);

        btnAlterarAnimais.setFont(new java.awt.Font("Tahoma", 0, 10));
        btnAlterarAnimais.setText("Alterar");
        btnAlterarAnimais.setEnabled(false);
        btnAlterarAnimais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarAnimaisActionPerformed(evt);
            }
        });
        jPanel4.add(btnAlterarAnimais);
        btnAlterarAnimais.setBounds(125, 110, 63, 21);

        btnExcluirAnimais.setFont(new java.awt.Font("Tahoma", 0, 10));
        btnExcluirAnimais.setText("Excluir");
        btnExcluirAnimais.setEnabled(false);
        btnExcluirAnimais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAnimaisActionPerformed(evt);
            }
        });
        jPanel4.add(btnExcluirAnimais);
        btnExcluirAnimais.setBounds(190, 110, 63, 21);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(300, 290, 285, 140);

        add(jPanel1);
        jPanel1.setBounds(0, 160, 590, 470);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        add(btnFechar);
        btnFechar.setBounds(490, 630, 80, 23);
    }// </editor-fold>//GEN-END:initComponents

private void txtNomeClientePesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeClientePesquisaKeyPressed
    int key = evt.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
        setStatusPesquisar();
    }
}//GEN-LAST:event_txtNomeClientePesquisaKeyPressed

private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
    setVisible(false);
    txtNomeClientePesquisa.setText("");
}//GEN-LAST:event_btnFecharActionPerformed

private void btnAddAnimaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAnimaisActionPerformed
    Animal a = new Animal();
    a.setCliente(cliente);
    animalDialog.setVisible(true, a, StatusTela.NOVO);
}//GEN-LAST:event_btnAddAnimaisActionPerformed

private void btnAddPessoasAutorizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPessoasAutorizadasActionPerformed
    PessoasAutorizadas p = new PessoasAutorizadas();
    p.setCliente(cliente);
    instancePessoasAutorizadasDialog().setVisible(true, p, StatusTela.NOVO);
}//GEN-LAST:event_btnAddPessoasAutorizadasActionPerformed

private void btnAlterarPessoasAutorizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarPessoasAutorizadasActionPerformed
    if (pessoasAutorizadaTable.getSelectedRow() < 0) {
        JOptionPane.showMessageDialog(null, "Selecione uma pessoa autrizada.", "Aviso", 1);
        return;
    }
    PessoasAutorizadas p = (PessoasAutorizadas)pessoasAutorizadaTable.getDomain();
    p.setCliente(cliente);
    instancePessoasAutorizadasDialog().setVisible(true, p, StatusTela.ATUALIZAR);
}//GEN-LAST:event_btnAlterarPessoasAutorizadasActionPerformed

private void btnExcluirPessoasAutorizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirPessoasAutorizadasActionPerformed
     if (pessoasAutorizadaTable.getSelectedRow() < 0) {
        JOptionPane.showMessageDialog(null, "Selecione uma pessoa autrizada.", "Aviso", 1);
        return;
    }
     int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir esse(s) registro(s)?", "Aviso", 0);
        if (resposta == 0) {
            int[] valor = pessoasAutorizadaTable.getSelectedRows();
            for (int i = 0; i < valor.length; i++) {
                try {
                    pessoasAutorizadasBusiness.delete((PessoasAutorizadas) pessoasAutorizadaTable.getDomain(valor[i]));
                } catch (PessoasAutorizadasBusinessException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
                }
            }
            reload();
            JOptionPane.showMessageDialog(null, "Registro(s) excluído(s) com sucesso.", "Aviso", 1);
        }
}//GEN-LAST:event_btnExcluirPessoasAutorizadasActionPerformed

private void btnAlterarAnimaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarAnimaisActionPerformed
    if (animalTable.getSelectedRow() < 0) {
        JOptionPane.showMessageDialog(null, "Selecione um animal.", "Aviso", 1);
        return;
    }
    Animal a = cliente.getAnimals().get(animalTable.getSelectedRow());
    a.setCliente(cliente);
    animalDialog.setVisible(true, a, StatusTela.ATUALIZAR);
}//GEN-LAST:event_btnAlterarAnimaisActionPerformed

private void btnExcluirAnimaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAnimaisActionPerformed
    try {
        if (animalTable.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Selecione um animal.", "Aviso", 1);
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir esse(s) registro(s)?", "Aviso", 0);
        if (resposta == 0) {
            int[] valor = animalTable.getSelectedRows();
            for (int i = 0; i < valor.length; i++) {
                animalBusiness.delete((Animal) animalTable.getDomain(valor[i]));
            }
            reload();
            JOptionPane.showMessageDialog(null, "Registro(s) excluído(s) com sucesso.", "Aviso", 1);
        }
    } catch (AnimalBusinessException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
    }


}//GEN-LAST:event_btnExcluirAnimaisActionPerformed

private void rbtAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAnimalActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_rbtAnimalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAnimais;
    private javax.swing.JButton btnAddPessoasAutorizadas;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterarAnimais;
    private javax.swing.JButton btnAlterarPessoasAutorizadas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluirAnimais;
    private javax.swing.JButton btnExcluirPessoasAutorizadas;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbEstado;
    private javax.swing.JComboBox cmbMunicipio;
    private javax.swing.JComboBox cmbTipoLogradouro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlContato;
    private javax.swing.JRadioButton rbtAnimal;
    private javax.swing.JRadioButton rbtAutorizadas;
    private javax.swing.JRadioButton rbtCliente;
    private javax.swing.JTable tblAnimais;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblPessoasAutorizadas;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JFormattedTextField txtCep;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeClientePesquisa;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables

    private TipoLogradouro getTipoLogradouro() {
        if (cmbTipoLogradouro.getSelectedIndex() == 0) {
            return null;
        } else {
            return (TipoLogradouro) cmbTipoLogradouro.getSelectedItem();
        }
    }

    private Municipio getMunicipio() {
        if (cmbMunicipio.getSelectedIndex() == 0) {
            return null;
        } else {
            return (Municipio) cmbMunicipio.getSelectedItem();

        }
    }

    @Override
    public void setEnabledObjctes(boolean valor) {
        txtNomeCliente.setEnabled(valor);
        contactJPanel.setEnabled(valor);
        txtBairro.setEnabled(valor);
        txtCep.setEnabled(valor);
        txtComplemento.setEnabled(valor);
        txtEmail.setEnabled(valor);
        txtLogradouro.setEnabled(valor);
        txtNumero.setEnabled(valor);
        cmbMunicipio.setEnabled(valor);
        cmbTipoLogradouro.setEnabled(valor);
    }

    protected void setStatusPreencher() {
        super.setStatusPreencher();
        btnAddAnimais.setEnabled(true);
        btnAddPessoasAutorizadas.setEnabled(true);
        btnAlterarAnimais.setEnabled(true);
        btnAlterarPessoasAutorizadas.setEnabled(true);
        btnExcluirAnimais.setEnabled(true);
        btnExcluirPessoasAutorizadas.setEnabled(true);
    }

    protected void setStatusNovo() {
        super.setStatusNovo();
        btnAddAnimais.setEnabled(false);
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAlterarAnimais.setEnabled(false);
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnExcluirAnimais.setEnabled(false);
        btnExcluirPessoasAutorizadas.setEnabled(false);
        preencherTblAnimais(null);
        preencherTblPessoasAutorizadas(null);
    }

    protected void setStatusAlterar() {
        super.setStatusAlterar();
        btnAddAnimais.setEnabled(false);
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAlterarAnimais.setEnabled(false);
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnExcluirAnimais.setEnabled(false);
        btnExcluirPessoasAutorizadas.setEnabled(false);
    }

    protected void setStatusSalvar() {
        super.setStatusSalvar();
        btnAddAnimais.setEnabled(false);
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAlterarAnimais.setEnabled(false);
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnExcluirAnimais.setEnabled(false);
        btnExcluirPessoasAutorizadas.setEnabled(false);
    }

    protected void setStatusInicial() {
        super.setStatusInicial();
        btnAddAnimais.setEnabled(false);
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAlterarAnimais.setEnabled(false);
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnExcluirAnimais.setEnabled(false);
        btnExcluirPessoasAutorizadas.setEnabled(false);
    }

    protected void setStatusCancelar() {
        super.setStatusCancelar();
        btnAddAnimais.setEnabled(false);
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAlterarAnimais.setEnabled(false);
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnExcluirAnimais.setEnabled(false);
        btnExcluirPessoasAutorizadas.setEnabled(false);
    }

    protected void setStatusDeletar() {
        super.setStatusDeletar();
        btnAddAnimais.setEnabled(false);
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAlterarAnimais.setEnabled(false);
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnExcluirAnimais.setEnabled(false);
        btnExcluirPessoasAutorizadas.setEnabled(false);
    }

    protected void setStatusPesquisar() {
        super.setStatusPesquisar();
        btnAddAnimais.setEnabled(false);
        btnAddPessoasAutorizadas.setEnabled(false);
        btnAlterarAnimais.setEnabled(false);
        btnAlterarPessoasAutorizadas.setEnabled(false);
        btnExcluirAnimais.setEnabled(false);
        btnExcluirPessoasAutorizadas.setEnabled(false);
    }

    @Override
    public void setEmptyObjects() {
        txtNomeCliente.setText("");
        contactJPanel.setEmptyObjects();
        txtBairro.setText("");
        txtCep.setText("");
        txtComplemento.setText("");
        txtEmail.setText("");
        txtLogradouro.setText("");
        txtNumero.setText("");
        cmbMunicipio.setSelectedIndex(0);
        cmbTipoLogradouro.setSelectedIndex(0);
        animalTable.cleanTable();
        pessoasAutorizadaTable.cleanTable();
    }

    @Override
    public Cliente getGenericPesquisaDomain() {
        if(rbtCliente.isSelected()){
            clientePesquisa.setNome(txtNomeClientePesquisa.getText());
            clientePesquisa.setNomeAnimal("");
            clientePesquisa.setNomePessoasAutorizadas("");
        }else if(rbtAutorizadas.isSelected()){
            clientePesquisa.setNome("");
            clientePesquisa.setNomeAnimal("");
            clientePesquisa.setNomePessoasAutorizadas(txtNomeClientePesquisa.getText());
        }else{
            clientePesquisa.setNome("");
            clientePesquisa.setNomeAnimal(txtNomeClientePesquisa.getText());
            clientePesquisa.setNomePessoasAutorizadas("");
        }
        return clientePesquisa;
    }

    @Override
    protected Cliente getEntidade() {
        if (statusAtual.equals(StatusTela.NOVO)) {
            cliente = new Cliente();
        }
        cliente.setNome(txtNomeCliente.getText());
        cliente.setTelefoneContato(contactJPanel.getListOriginal());
        cliente.setEmail(txtEmail.getText());
        cliente.getEndereco().setBairro(txtBairro.getText());
        cliente.getEndereco().setCep(txtCep.getText());
        cliente.getEndereco().setComplemento(txtComplemento.getText());
        cliente.getEndereco().setLogradouro(txtLogradouro.getText());
        cliente.getEndereco().setNumero(txtNumero.getText());
        cliente.getEndereco().setTipoLogradouro(getTipoLogradouro());
        cliente.getEndereco().setMunicipio(getMunicipio());
        if (cliente.getEndereco().getMunicipio() != null) {
            cliente.getEndereco().getMunicipio().setEstado((Estado) cmbEstado.getSelectedItem());
        }

        return cliente;
    }

    @Override
    protected void setEntidade(Cliente entidade) {
        cliente = (Cliente) entidade;
        txtNomeCliente.setText(cliente.getNome());
        txtEmail.setText(cliente.getEmail());
        preencherTblAnimais(cliente.getAnimals());
        preencherTblPessoasAutorizadas(cliente.getPessoaAutorizadas());
        contactJPanel.setTableValue(cliente.getTelefoneContato());
        txtBairro.setText(cliente.getEndereco().getBairro());
        txtCep.setText(cliente.getEndereco().getCep());
        txtComplemento.setText(cliente.getEndereco().getComplemento());
        txtLogradouro.setText(cliente.getEndereco().getLogradouro());
        txtNomeCliente.setText(cliente.getNome());
        txtNumero.setText(cliente.getEndereco().getNumero());
        TipoLogradouro tl = cliente.getEndereco().getTipoLogradouro();
        if (tl == null) {
            cmbTipoLogradouro.setSelectedIndex(0);
        } else {
            cmbTipoLogradouro.setSelectedItem(tl);
        }
        if (cliente.getEndereco().getMunicipio() == null) {
            cmbMunicipio.setSelectedIndex(0);
            return;
        }
        cmbMunicipio.setSelectedItem(cliente.getEndereco().getMunicipio());

    }

    @Override
    public ClienteBusiness getGenericBusiness() {
        return instanceClienteBusiness();
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
        tblCliente = myTable;
    }

    @Override
    public void returnValue(Object object) {
        reload();
    }

    @Override
    public Object getObject() {
        return null;
    }
}
