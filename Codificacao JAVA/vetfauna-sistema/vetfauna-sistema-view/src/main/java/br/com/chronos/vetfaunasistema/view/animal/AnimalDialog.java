/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AnimalDialog.java
 *
 * Created on 01/05/2013, 22:13:04
 */
package br.com.chronos.vetfaunasistema.view.animal;

import br.com.chronos.vetfaunasistema.business.animal.AnimalBusiness;
import br.com.chronos.vetfaunasistema.business.animal.AnimalBusinessImpl;
import br.com.chronos.vetfaunasistema.business.animal.exception.AnimalBusinessException;
import br.com.chronos.vetfaunasistema.business.especie.EspecieBusiness;
import br.com.chronos.vetfaunasistema.business.especie.EspecieBusinessImpl;
import br.com.chronos.vetfaunasistema.business.especie.exception.EspecieBusinessException;
import br.com.chronos.vetfaunasistema.business.vacina.VacinaBusiness;
import br.com.chronos.vetfaunasistema.business.vacina.VacinaBusinessImpl;
import br.com.chronos.vetfaunasistema.business.vacina.exception.VacinaBusinessException;
import br.com.chronos.vetfaunasistema.business.vacinacao.VacinacaoBusiness;
import br.com.chronos.vetfaunasistema.business.vacinacao.VacinacaoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.vacinacao.exception.VacinacaoBusinessException;
import br.com.chronos.vetfaunasistema.domain.Animal;
import br.com.chronos.vetfaunasistema.domain.Especie;
import br.com.chronos.vetfaunasistema.domain.Raca;
import br.com.chronos.vetfaunasistema.domain.Vacina;
import br.com.chronos.vetfaunasistema.domain.Vacinacao;
import br.com.chronos.vetfaunasistema.domain.comparator.ComparatorVacinacao;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Sexo;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusRegistro;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusTela;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import br.com.chronos.vetfaunasistema.utilitarios.util.Util;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.InvokeEntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.exceptions.InvokeEntiteValidationException;
import br.com.chronos.vetfaunasistema.view.component.FrameFather;
import br.com.chronos.vetfaunasistema.view.component.jformatter.MaxLengthTextDocument;
import br.com.chronos.vetfaunasistema.view.component.jformatter.NumberJFormatter;
import br.com.chronos.vetfaunasistema.view.component.table.MyTable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class AnimalDialog extends javax.swing.JDialog {

    private FrameFather frameFather;
    private Animal animal;
    private AnimalBusiness animalBusiness;
    private StatusTela statusTela;
    private EspecieBusiness especieBusiness;
    private VacinacaoBusiness vacinacaoBusiness;
    private VacinaBusiness vacinaBusiness;
    private MyTable vacinacaoTable;
    private Vacinacao vacinacao;
    private List<Vacinacao> lista;
    private List<Vacinacao> listFake;
    private List<Vacinacao> listOriginal;

    /** Creates new form AnimalDialog */
    public AnimalDialog(java.awt.Frame parent, boolean modal, FrameFather frameFather) {
        super(parent, modal);
        vacinacao = new Vacinacao();
        this.frameFather = frameFather;
        vacinacaoBusiness = new VacinacaoBusinessImpl();
        vacinaBusiness = new VacinaBusinessImpl();
        instanceAnimalBusiness();
        vacinacaoTable = new MyTable(vacinacao);
        especieBusiness = new EspecieBusinessImpl();
        initComponents();
        btnAdicionarVacina.setIcon(new javax.swing.ImageIcon("pictures\\icones\\ImgArrowPrev.png")); // NOI18N
        btnRemoverVacina.setIcon(new javax.swing.ImageIcon("pictures\\icones\\ImgArrowPrevDown.png")); // NOI18N
        preencherCmbEspecie();
        preencherCmbSexo();

        listFake = new ArrayList<>();

    }

    private void preencherCmbSexo() {
        cmbSexo.addItem(Sexo.M);
        cmbSexo.addItem(Sexo.F);
        cmbSexo.setSelectedIndex(1);
    }

    private AnimalBusiness instanceAnimalBusiness() {
        if (animalBusiness == null) {
            animalBusiness = new AnimalBusinessImpl();
        }
        return animalBusiness;
    }

    private void preencherCmbVacina() {
        try {
            List<Vacina> l = vacinaBusiness.select(new Vacina());
            cmbVacina.removeAllItems();
            cmbVacina.addItem("--Selecione uma opção--");
            for (Vacina vacina : l) {
                cmbVacina.addItem(vacina);
            }
        } catch (VacinaBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    private void setAnimal(Animal animal) {
        this.animal = animal;
        if (this.animal.getIdAnimal() == null) {
            this.animal.setIdAnimal((Integer) animalBusiness.getId());
        }
    }

    private void setCmbRacaEspecie() {
        if (animal.getRaca() != null) {
            if (animal.getRaca().getEspecie() != null) {
                cmbEspecie.setSelectedItem(animal.getRaca().getEspecie());
            } else {
                cmbEspecie.setSelectedIndex(0);
            }
            cmbRaca.setSelectedItem(animal.getRaca());
        } else {
            cmbRaca.setSelectedIndex(0);
            cmbEspecie.setSelectedIndex(0);
        }
    }

    private void setCmbSexo() {
        if (animal.getSexo() != null) {
            cmbSexo.setSelectedItem(animal.getSexo());
        } else {
            cmbSexo.setSelectedIndex(0);
        }
    }

    public void preencheCampos(Animal animal) {
        setAnimal(animal);
        txtNome.setText(animal.getNome());
        txtDataNascimento.setText(Util.formatterDate(animal.getDataNascimento()));
        setCmbRacaEspecie();
        setCmbSexo();
        txtObservacao.setText(animal.getObservacao());
        preencharTblVacinacao();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        cmbEspecie = new javax.swing.JComboBox();
        cmbRaca = new javax.swing.JComboBox();
        txtDataNascimento = new NumberJFormatter(br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask.DATA,10,"")
        ;
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVacinacao = vacinacaoTable;
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cmbVacina = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtNomeMarca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDataVacina = new NumberJFormatter(br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask.DATA,10,"");
        jLabel9 = new javax.swing.JLabel();
        txtDataProximaVacina = new NumberJFormatter(br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask.DATA,10,"");
        btnRemoverVacina = new javax.swing.JButton();
        btnAdicionarVacina = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Animais");
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Animal"));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText("Nome: ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 27, 110, 13);

        txtNome.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 49, ""));
        txtNome.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtNome);
        txtNome.setBounds(10, 40, 170, 23);

        cmbEspecie.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbEspecie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Selecione uma opção--" }));
        cmbEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEspecieActionPerformed(evt);
            }
        });
        jPanel1.add(cmbEspecie);
        cmbEspecie.setBounds(190, 40, 120, 23);

        cmbRaca.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbRaca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Selecione uma opção--" }));
        cmbRaca.setEnabled(false);
        jPanel1.add(cmbRaca);
        cmbRaca.setBounds(320, 40, 130, 23);

        txtDataNascimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDataNascimento.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtDataNascimento);
        txtDataNascimento.setBounds(10, 80, 80, 23);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("Raça:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(320, 27, 60, 13);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText("Espécie:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(190, 27, 70, 13);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("Data Nascimento:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 68, 100, 13);

        txtObservacao.setColumns(20);
        txtObservacao.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtObservacao.setRows(5);
        jScrollPane1.setViewportView(txtObservacao);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 120, 440, 90);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText("Observações: ");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 107, 100, 13);

        tblVacinacao.setModel(vacinacaoTable.getModel());
        jScrollPane2.setViewportView(tblVacinacao);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 310, 440, 140);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Vacina"));
        jPanel2.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel6.setText("Vacina:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 19, 60, 13);

        cmbVacina.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel2.add(cmbVacina);
        cmbVacina.setBounds(10, 32, 120, 23);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText("Marca/Nome:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(140, 19, 110, 13);

        txtNomeMarca.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 29, ""));
        txtNomeMarca.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel2.add(txtNomeMarca);
        txtNomeMarca.setBounds(140, 32, 120, 23);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText("Data Vacinação:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(270, 19, 100, 13);

        txtDataVacina.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDataVacina.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel2.add(txtDataVacina);
        txtDataVacina.setBounds(270, 32, 70, 23);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setText("Data Próxima:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(350, 19, 90, 13);

        txtDataProximaVacina.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDataProximaVacina.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel2.add(txtDataProximaVacina);
        txtDataProximaVacina.setBounds(350, 32, 70, 23);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 210, 440, 73);

        btnRemoverVacina.setIcon(new javax.swing.ImageIcon("C:\\Users\\Fernando\\Desktop\\Projeto thalles\\SVN\\Implementacao\\Codificacao JAVA\\vetfauna-sistema\\vetfauna-sistema-view\\pictures\\icones\\ImgArrowPrevDown.png")); // NOI18N
        btnRemoverVacina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverVacinaActionPerformed(evt);
            }
        });
        jPanel1.add(btnRemoverVacina);
        btnRemoverVacina.setBounds(200, 280, 30, 30);

        btnAdicionarVacina.setIcon(new javax.swing.ImageIcon("C:\\Users\\Fernando\\Desktop\\Projeto thalles\\SVN\\Implementacao\\Codificacao JAVA\\vetfauna-sistema\\vetfauna-sistema-view\\pictures\\icones\\ImgArrowPrev.png")); // NOI18N
        btnAdicionarVacina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarVacinaActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdicionarVacina);
        btnAdicionarVacina.setBounds(230, 280, 30, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel10.setText("Sexo:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(100, 68, 26, 13);

        cmbSexo.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Selecone uma opção--" }));
        jPanel1.add(cmbSexo);
        cmbSexo.setBounds(100, 80, 100, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 460, 460);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(120, 470, 70, 23);

        btnCancelar.setText("Fechar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(280, 470, 70, 23);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(200, 470, 70, 23);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-478)/2, (screenSize.height-550)/2, 478, 550);
    }// </editor-fold>//GEN-END:initComponents

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    setVisible(false, null, null);
}//GEN-LAST:event_btnCancelarActionPerformed

private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
    try {
        if (StatusTela.ATUALIZAR.equals(statusTela)) {
            animalBusiness.update(getAnimal());
            JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso.", "Aviso", 1);
            setVisible(false, null, null);
        } else {
            animalBusiness.insert(getAnimal());
            JOptionPane.showMessageDialog(null, "Registro incluído com sucesso.", "Aviso", 1);
            limpaCampos();
            animal.setIdAnimal((Integer) animalBusiness.getId());
        }
    } catch (AnimalBusinessException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
    }

}//GEN-LAST:event_btnSalvarActionPerformed

private void cmbEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEspecieActionPerformed
    if (cmbEspecie.getSelectedIndex() <= 0) {
        cmbRaca.setEnabled(false);
        return;
    }
    cmbRaca.setEnabled(true);
    preencherCmbRaca(((Especie) cmbEspecie.getSelectedItem()).getRacas());// TODO add your handling code here:
}//GEN-LAST:event_cmbEspecieActionPerformed

private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
    limpaCampos();
}//GEN-LAST:event_btnLimparActionPerformed

private void btnAdicionarVacinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarVacinaActionPerformed
    try {
        getVacinacao();
        InvokeEntiteValidation.insertValidation(vacinacao);
        if (listFake.contains(vacinacao)) {
            JOptionPane.showMessageDialog(null, "Essa vacinação já foi incluída.", "Erro", 0);
            return;
        }
        listFake.add(vacinacao);
        Collections.sort(listFake, new ComparatorVacinacao());
        setStatusRegistro(getVacinacao(), StatusRegistro.NOVO);
        setTableValue();
        setEmptyVacinacao();
    } catch (InvokeEntiteValidationException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
    }
}//GEN-LAST:event_btnAdicionarVacinaActionPerformed

private void btnRemoverVacinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverVacinaActionPerformed
    if (tblVacinacao.getSelectedRows().length != 1) {
        JOptionPane.showMessageDialog(null, "Selecione um registro.", "Erro", 0);
        return;
    }
    setVacinacao(listFake.get(tblVacinacao.getSelectedRow()), StatusRegistro.DELETE);
    listFake.remove(tblVacinacao.getSelectedRow());
    setTableValue();
}//GEN-LAST:event_btnRemoverVacinaActionPerformed

    private void setTableValue() {
        vacinacaoTable.fillTable(listFake);
    }

    private void setStatusRegistro(Vacinacao vacinacao, StatusRegistro statusContato) {
        if (listOriginal.contains(vacinacao)) {//SE JÁ ESTÁ NA LISTA ORIGINAL VERIFICAR O STATUS
            for (int i = 0; i < listOriginal.size(); i++) {
                if (listOriginal.get(i).equals(vacinacao) && statusContato.equals(StatusRegistro.DELETE)) {
                    if (listOriginal.get(i).getStatusRegistro().equals(StatusRegistro.NOVO)) {//SE O STATUS EH NOVO ENTÃO EU POSSO REMOVER DA LISTA
                        listOriginal.remove(vacinacao);
                        return;
                    } else if (listOriginal.get(i).getStatusRegistro().equals(StatusRegistro.EXISTE)) {//SE ELE JA EXISTE SETAR PARA REMOVER DO BANCO
                        listOriginal.get(i).setStatusRegistro(statusContato);
                        return;
                    }
                }
            }
        } else {
            vacinacao.setStatusRegistro(StatusRegistro.NOVO);
            listOriginal.add(vacinacao);
        }

    }

    private Vacinacao getVacinacao() {
        vacinacao = new Vacinacao();
        try {
            vacinacao.setAnimal(animal);
            vacinacao.setDataProximaVacinacao(Util.parseDate(txtDataProximaVacina.getText()));
            vacinacao.setDataVacinacao(Util.parseDate(txtDataVacina.getText()));
            vacinacao.setNomeMarca(txtNomeMarca.getText());
            vacinacao.setVacina(getCmbVacina());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter valor.", "Erro", 0);
        }
        return vacinacao;
    }

    private Animal getAnimal() {
        Animal a = new Animal();
        try {
            a.setIdAnimal(animal.getIdAnimal());
            a.setCliente(animal.getCliente());
            a.setNome(txtNome.getText());
            a.setDataNascimento(Util.formatterDate(txtDataNascimento.getText()));
            a.setRaca(getCmbRaca());
            a.setObservacao(txtObservacao.getText());
            a.setSexo(getCmbSexo());
            a.setVacinacoes(listOriginal);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter valor.", "Erro", 0);
        }
        return a;
    }

    private Vacina getCmbVacina() {
        if (cmbVacina.getSelectedIndex() > 0) {
            return ((Vacina) cmbVacina.getSelectedItem());
        } else {
            return null;
        }
    }

    private Sexo getCmbSexo() {
        if (cmbSexo.getSelectedIndex() > 0) {
            return ((Sexo) cmbSexo.getSelectedItem());
        } else {
            return null;
        }
    }

    private Raca getCmbRaca() {
        if (cmbRaca.getSelectedIndex() > 0) {
            return ((Raca) cmbRaca.getSelectedItem());
        } else {
            return null;
        }
    }

    public void setVisible(boolean valor, Animal animal, StatusTela statusTela) {
        if (valor) {
            this.statusTela = statusTela;
            preencherCmbVacina();
            limpaCampos();
            preencheCampos(animal);
            enableButtons();
        } else {
            this.animal.setIdAnimal(null);
            frameFather.returnValue(null);
        }
        super.setVisible(valor);
    }

    public void dispose() {
        setVisible(false, null, null);
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarVacina;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnRemoverVacina;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cmbEspecie;
    private javax.swing.JComboBox cmbRaca;
    private javax.swing.JComboBox cmbSexo;
    private javax.swing.JComboBox cmbVacina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVacinacao;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JFormattedTextField txtDataProximaVacina;
    private javax.swing.JFormattedTextField txtDataVacina;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeMarca;
    private javax.swing.JTextArea txtObservacao;
    // End of variables declaration//GEN-END:variables

    private void enableButtons() {
        if (StatusTela.NOVO.equals(statusTela)) {
            btnLimpar.setEnabled(true);
        } else {
            btnLimpar.setEnabled(false);
        }
    }

    private void preencherCmbEspecie() {
        try {
            cmbEspecie.removeAllItems();
            cmbEspecie.addItem("--Selecione uma opção");
            List<Especie> l = especieBusiness.select(new Especie());
            for (Especie especie : l) {
                cmbEspecie.addItem(especie);
            }
        } catch (EspecieBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    private void preencherCmbRaca(List<Raca> racas) {
        cmbRaca.removeAllItems();
        cmbRaca.addItem("--Selecione uma opção");
        for (Raca raca : racas) {
            cmbRaca.addItem(raca);
        }
    }

    private void limpaCampos() {
        txtNome.setText("");
        txtDataNascimento.setText("");
        cmbEspecie.setSelectedIndex(0);
        cmbRaca.setSelectedIndex(0);
        cmbRaca.setEnabled(false);
        cmbSexo.setSelectedIndex(0);
        txtObservacao.setText("");
        vacinacaoTable.cleanTable();
        setEmptyVacinacao();
    }

    private void preencharTblVacinacao() {
        try {
            vacinacao = new Vacinacao();
            vacinacao.setAnimal(animal);
            lista = (List<Vacinacao>) vacinacaoBusiness.select(vacinacao);
            if (lista != null) {
                listFake = new ArrayList<>(lista);
                listOriginal = new ArrayList<>(lista);
            }
            vacinacaoTable.fillTable(lista);
        } catch (VacinacaoBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    private void setEmptyVacinacao() {
        txtDataProximaVacina.setText("");
        txtDataVacina.setText("");
        txtNomeMarca.setText("");
        cmbVacina.setSelectedIndex(0);

    }

    private void setVacinacao(Vacinacao vacinacao, StatusRegistro statusRegistro) {
        txtDataProximaVacina.setText(Util.formatterDate(vacinacao.getDataProximaVacinacao()));
        txtDataVacina.setText(Util.formatterDate(vacinacao.getDataVacinacao()));
        if (vacinacao.getVacina() != null) {
            cmbVacina.setSelectedItem(vacinacao.getVacina());
        } else {
            cmbVacina.setSelectedIndex(0);
        }
        txtNomeMarca.setText(vacinacao.getNomeMarca());
        setStatusRegistro(vacinacao, statusRegistro);
    }
}
