/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProdutoPanel.java
 *
 * Created on 13/02/2013, 16:55:53
 */
package br.com.chronos.vetfaunasistema.view.produto;

import br.com.chronos.vetfaunasistema.business.categoriaproduto.CategoriaProdutoBusiness;
import br.com.chronos.vetfaunasistema.business.categoriaproduto.CategoriaProdutoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.categoriaproduto.exception.CategoriaProdutoBusinessException;
import br.com.chronos.vetfaunasistema.business.marcaproduto.MarcaProdutoBusiness;
import br.com.chronos.vetfaunasistema.business.marcaproduto.MarcaProdutoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.marcaproduto.exception.MarcaProdutoBusinessException;
import br.com.chronos.vetfaunasistema.business.produto.ProdutoBusiness;
import br.com.chronos.vetfaunasistema.business.produto.ProdutoBusinessImpl;
import br.com.chronos.vetfaunasistema.business.unidademedida.UnidadeMedidaBusiness;
import br.com.chronos.vetfaunasistema.business.unidademedida.UnidadeMedidaBusinessImpl;
import br.com.chronos.vetfaunasistema.business.unidademedida.exception.UnidadeMedidaBusinessException;
import br.com.chronos.vetfaunasistema.domain.CategoriaProduto;
import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import br.com.chronos.vetfaunasistema.domain.MarcaProduto;
import br.com.chronos.vetfaunasistema.domain.Produto;
import br.com.chronos.vetfaunasistema.domain.UnidadeMedida;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import br.com.chronos.vetfaunasistema.utilitarios.util.Util;
import br.com.chronos.vetfaunasistema.view.generic.CrudView;
import br.com.chronos.vetfaunasistema.view.generic.GenericViewCrud;
import br.com.chronos.vetfaunasistema.view.component.jformatter.MaxLengthTextDocument;
import br.com.chronos.vetfaunasistema.view.component.jformatter.NumberJFormatter;
import br.com.chronos.vetfaunasistema.view.component.table.MyTable;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class ProdutoPanel extends GenericViewCrud<Produto, ProdutoBusiness> {

    private Produto produto;
    private Produto produtoPesquisa;
    private ProdutoBusiness produtoBusiness;
    private NumberFormat formatoMoeda = DecimalFormat.getInstance();
    private CategoriaProdutoBusiness categoriaProdutoBusiness;
    private MarcaProdutoBusiness marcaProdutoBusiness;
    private UnidadeMedidaBusiness unidadeMedidaBusiness;
    private Integer idProduto;
    private final CategoriaProduto primeiroCategoriaProduto = new CategoriaProduto("--Selecione uma opção--");
    private final MarcaProduto primeiroMarca = new MarcaProduto("--Selecione uma opção--");

    /** Creates new form ProdutoPanel */
    public ProdutoPanel(CrudView crudView) {
        setCrudView(crudView);
        produto = new Produto();
        categoriaProdutoBusiness = new CategoriaProdutoBusinessImpl();
        marcaProdutoBusiness = new MarcaProdutoBusinessImpl();
        unidadeMedidaBusiness = new UnidadeMedidaBusinessImpl();
        produtoPesquisa = new Produto();
        instanceProdutoBusiness();
        initComponents();
        preencheCategoria();
        cmbMarcaPesquisa.addItem(primeiroMarca);
        cmbMarca.addItem(primeiroMarca);
//        preencheMarca();
        preencheUnidadeMedida();
        actions();
    }

    private void preencheCategoria() {
        try {
            List<CategoriaProduto> lista = categoriaProdutoBusiness.select(new CategoriaProduto());
            cmbCategoria.removeAllItems();
            cmbCategoriaPesquisa.removeAllItems();
            cmbCategoria.addItem(primeiroCategoriaProduto);
            cmbCategoriaPesquisa.addItem(primeiroCategoriaProduto);
            for (CategoriaProduto categoriaProduto : lista) {
                cmbCategoria.addItem(categoriaProduto);
                cmbCategoriaPesquisa.addItem(categoriaProduto);
            }
        } catch (CategoriaProdutoBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    public Component[] getRepaintComponets() {
        return new Component[]{cmbMarca, cmbCategoria, cmbCategoriaPesquisa, cmbMarcaPesquisa};
    }

    @Override
    public void repaint(List<? extends GenericDomain> list, Class<?> clazz) {
        if (MarcaProduto.class.equals(clazz)) {
            preencheMarca();
            preencheMarcaPesquisa();
        } else {
            Util.repaintComponents(list, clazz, getRepaintComponets());
        }
    }
//    private void preencheMarcaPesquisa(List list) {
//        cmbMarcaPesquisa.removeAllItems();
//        cmbMarcaPesquisa.addItem(primeiroMarca);
//        for (Object o : list) {
//            cmbMarcaPesquisa.addItem(o);
//        }
//    }
//    

    public void preencheMarca() {
        try {
            MarcaProduto mp = new MarcaProduto();
            mp.setCategoriaProduto((CategoriaProduto) cmbCategoria.getSelectedItem());
            List<MarcaProduto> lista = marcaProdutoBusiness.select(mp);
            cmbMarca.removeAllItems();
            cmbMarca.addItem(primeiroMarca);
            for (MarcaProduto marcaEstoque : lista) {
                cmbMarca.addItem(marcaEstoque);
            }
        } catch (MarcaProdutoBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    public void preencheMarcaPesquisa() {
        try {
            MarcaProduto mp = new MarcaProduto();
            mp.setCategoriaProduto((CategoriaProduto) cmbCategoriaPesquisa.getSelectedItem());
            List<MarcaProduto> lista = marcaProdutoBusiness.select(mp);
            cmbMarcaPesquisa.removeAllItems();
            cmbMarcaPesquisa.addItem(primeiroMarca);
            for (MarcaProduto marcaEstoque : lista) {
                cmbMarcaPesquisa.addItem(marcaEstoque);
            }
        } catch (MarcaProdutoBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }
//    private void preencheMarca() {
//        try {
//            List<MarcaProduto> lista = marcaProdutoBusiness.select(new MarcaProduto());
//            cmbMarca.removeAllItems();
//            cmbMarcaPesquisa.removeAllItems();
//            cmbMarcaPesquisa.addItem(primeiroMarca);
//            cmbMarca.addItem(primeiroMarca);
//            for (MarcaProduto marcaProduto : lista) {
//                cmbMarca.addItem(marcaProduto);
//                cmbMarcaPesquisa.addItem(marcaProduto);
//            }
//        } catch (MarcaProdutoBusinessException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
//        }
//    }

    private void preencheUnidadeMedida() {
        try {
            List<UnidadeMedida> lista = unidadeMedidaBusiness.select(new UnidadeMedida());
            cmbUnidadeMedida.removeAllItems();
            cmbUnidadeMedida.addItem("--Selecione--");
            for (UnidadeMedida unidadeMedida : lista) {
                cmbUnidadeMedida.addItem(unidadeMedida);
            }
        } catch (UnidadeMedidaBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    private ProdutoBusiness instanceProdutoBusiness() {
        if (produtoBusiness == null) {
            produtoBusiness = new ProdutoBusinessImpl();
        }
        return produtoBusiness;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFechar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtIdProduto = new NumberJFormatter(br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask.INTEGER,20,"");
        cmbCategoria = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cmbMarca = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtValorMedida = new NumberJFormatter(TypeMask.DECIMAL,6,"0,00");
        cmbUnidadeMedida = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtQuantidadeEstoque = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodigoBarra = new NumberJFormatter(TypeMask.NUMERIC_STRING,100,"");
        txtAntecedenciaVencimento = new NumberJFormatter(TypeMask.INTEGER,3,"0");
        txtQuantidadeMinima = new NumberJFormatter(TypeMask.INTEGER,3,"0");
        jLabel6 = new javax.swing.JLabel();
        txtPreco = new NumberJFormatter(TypeMask.DECIMAL,8,"0,00");
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduto = getMyTable();
        jLabel14 = new javax.swing.JLabel();
        txtNomeProdutoPesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        cmbCategoriaPesquisa = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cmbMarcaPesquisa = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtIdProdutoPesquisa = new NumberJFormatter(TypeMask.INTEGER,10,"");

        setLayout(null);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });
        add(btnFechar);
        btnFechar.setBounds(540, 540, 80, 23);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro"));
        jPanel1.setLayout(null);

        btnNovo.setText("Novo");
        jPanel1.add(btnNovo);
        btnNovo.setBounds(130, 215, 73, 23);

        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        jPanel1.add(btnSalvar);
        btnSalvar.setBounds(280, 215, 70, 23);

        btnAlterar.setText("Alterar");
        btnAlterar.setEnabled(false);
        jPanel1.add(btnAlterar);
        btnAlterar.setBounds(210, 215, 65, 23);

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(430, 215, 80, 23);

        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        jPanel1.add(btnExcluir);
        btnExcluir.setBounds(355, 215, 70, 23);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel5.setText("Código do Produto:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 27, 110, 13);

        txtIdProduto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIdProduto.setEnabled(false);
        jPanel1.add(txtIdProduto);
        txtIdProduto.setBounds(20, 40, 140, 22);

        cmbCategoria.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbCategoria.setEnabled(false);
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbCategoria);
        cmbCategoria.setBounds(20, 85, 140, 23);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel13.setText("Categoria:");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 70, 70, 13);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel15.setText("Marca:");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(175, 70, 100, 13);

        cmbMarca.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbMarca.setEnabled(false);
        jPanel1.add(cmbMarca);
        cmbMarca.setBounds(175, 85, 140, 23);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel16.setText("Nome do Produto:");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(330, 70, 110, 13);

        txtNomeProduto.setDocument(new MaxLengthTextDocument(TypeMask.TEXT, 59, ""));
        txtNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtNomeProduto.setEnabled(false);
        jPanel1.add(txtNomeProduto);
        txtNomeProduto.setBounds(330, 85, 300, 23);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel17.setText("Medida:");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(215, 117, 50, 13);

        txtValorMedida.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorMedida.setEnabled(false);
        txtValorMedida.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtValorMedida);
        txtValorMedida.setBounds(215, 130, 80, 23);

        cmbUnidadeMedida.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbUnidadeMedida.setEnabled(false);
        jPanel1.add(cmbUnidadeMedida);
        cmbUnidadeMedida.setBounds(310, 130, 90, 23);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText("Uni. Medida");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(310, 117, 70, 13);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel7.setText("Dias Ant.:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(490, 116, 60, 13);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("Qtd. Estoque:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 161, 70, 13);

        txtQuantidadeEstoque.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQuantidadeEstoque.setEnabled(false);
        txtQuantidadeEstoque.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtQuantidadeEstoque);
        txtQuantidadeEstoque.setBounds(20, 174, 40, 23);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("Código de Barra:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 117, 90, 13);

        txtCodigoBarra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCodigoBarra.setEnabled(false);
        txtCodigoBarra.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtCodigoBarra);
        txtCodigoBarra.setBounds(20, 130, 180, 23);

        txtAntecedenciaVencimento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAntecedenciaVencimento.setToolTipText("Dias de antecedência do vencimento");
        txtAntecedenciaVencimento.setEnabled(false);
        txtAntecedenciaVencimento.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtAntecedenciaVencimento);
        txtAntecedenciaVencimento.setBounds(490, 130, 60, 23);

        txtQuantidadeMinima.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtQuantidadeMinima.setEnabled(false);
        txtQuantidadeMinima.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtQuantidadeMinima);
        txtQuantidadeMinima.setBounds(415, 130, 60, 23);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("Qtd. Mínima:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(415, 116, 70, 14);

        txtPreco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPreco.setEnabled(false);
        txtPreco.setFont(new java.awt.Font("Tahoma", 0, 10));
        jPanel1.add(txtPreco);
        txtPreco.setBounds(565, 130, 65, 23);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel8.setText("Preço:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(565, 117, 60, 13);

        add(jPanel1);
        jPanel1.setBounds(0, 270, 640, 260);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));
        jPanel6.setLayout(null);

        tblProduto.setModel(getMyTable().getModel());
        jScrollPane2.setViewportView(tblProduto);

        jPanel6.add(jScrollPane2);
        jScrollPane2.setBounds(10, 120, 620, 140);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Produto:");
        jPanel6.add(jLabel14);
        jLabel14.setBounds(5, 60, 60, 20);

        txtNomeProdutoPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10));
        txtNomeProdutoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeProdutoPesquisaKeyPressed(evt);
            }
        });
        jPanel6.add(txtNomeProdutoPesquisa);
        txtNomeProdutoPesquisa.setBounds(70, 60, 260, 23);

        btnPesquisa.setText("Pesquisar");
        jPanel6.add(btnPesquisa);
        btnPesquisa.setBounds(470, 90, 90, 23);

        cmbCategoriaPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbCategoriaPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaPesquisaActionPerformed(evt);
            }
        });
        jPanel6.add(cmbCategoriaPesquisa);
        cmbCategoriaPesquisa.setBounds(70, 90, 150, 23);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Categoria:");
        jPanel6.add(jLabel11);
        jLabel11.setBounds(5, 90, 60, 23);

        cmbMarcaPesquisa.setFont(new java.awt.Font("Tahoma", 0, 10));
        cmbMarcaPesquisa.setEnabled(false);
        jPanel6.add(cmbMarcaPesquisa);
        cmbMarcaPesquisa.setBounds(270, 90, 150, 23);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Marca:");
        jPanel6.add(jLabel12);
        jLabel12.setBounds(205, 90, 60, 23);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Código:");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(5, 30, 60, 23);

        txtIdProdutoPesquisa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIdProdutoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdProdutoPesquisaKeyPressed(evt);
            }
        });
        jPanel6.add(txtIdProdutoPesquisa);
        txtIdProdutoPesquisa.setBounds(70, 30, 90, 23);

        add(jPanel6);
        jPanel6.setBounds(0, 0, 640, 270);
    }// </editor-fold>//GEN-END:initComponents

private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
    setVisible(false);
    txtNomeProdutoPesquisa.setText("");
    txtIdProdutoPesquisa.setText("");
    cmbCategoriaPesquisa.setSelectedIndex(0);
    cmbMarcaPesquisa.setSelectedIndex(0);
}//GEN-LAST:event_btnFecharActionPerformed

private void txtNomeProdutoPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeProdutoPesquisaKeyPressed
    int key = evt.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
        setStatusPesquisar();
    }
}//GEN-LAST:event_txtNomeProdutoPesquisaKeyPressed

private void txtIdProdutoPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdProdutoPesquisaKeyPressed
    int key = evt.getKeyCode();
    if (key == KeyEvent.VK_ENTER) {
        setStatusPesquisar();
    }
}//GEN-LAST:event_txtIdProdutoPesquisaKeyPressed

private void cmbCategoriaPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaPesquisaActionPerformed
    if (cmbCategoriaPesquisa.getSelectedIndex() == 0) {
        cmbMarcaPesquisa.setEnabled(false);
        return;
    }
    cmbMarcaPesquisa.setEnabled(true);
    preencheMarcaPesquisa();
}//GEN-LAST:event_cmbCategoriaPesquisaActionPerformed

private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
    if (cmbCategoria.getSelectedIndex() == 0) {
        cmbMarca.setEnabled(false);
        return;
    }
    cmbMarca.setEnabled(cmbCategoria.isEnabled());
    preencheMarca();
}//GEN-LAST:event_cmbCategoriaActionPerformed
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
    private javax.swing.JComboBox cmbMarca;
    private javax.swing.JComboBox cmbMarcaPesquisa;
    private javax.swing.JComboBox cmbUnidadeMedida;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblProduto;
    private javax.swing.JFormattedTextField txtAntecedenciaVencimento;
    private javax.swing.JFormattedTextField txtCodigoBarra;
    private javax.swing.JFormattedTextField txtIdProduto;
    private javax.swing.JFormattedTextField txtIdProdutoPesquisa;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtNomeProdutoPesquisa;
    private javax.swing.JFormattedTextField txtPreco;
    private javax.swing.JFormattedTextField txtQuantidadeEstoque;
    private javax.swing.JFormattedTextField txtQuantidadeMinima;
    private javax.swing.JFormattedTextField txtValorMedida;
    // End of variables declaration//GEN-END:variables

//    private BigDecimal getPreco() throws ParseException {
//        if (txtPercentualLucro.getText().isEmpty() || txtPercentualLucro.getText().equals("0,00")) {
//            return null;
//        } else {
//            return new BigDecimal("" + formatoMoeda.parse(txtPercentualLucro.getText()));
//        }
//        
//    }
//    private CategoriaProduto getCategoriaProduto() {
//        if (cmbCategoria.getSelectedIndex() > 0) {
//            return (CategoriaProduto) cmbCategoria.getSelectedItem();
//        } else {
//            return null;
//        }
//    }
    protected void setStatusNovo() {
        super.setStatusNovo();
        if (idProduto == null) {
            idProduto = (Integer) produtoBusiness.getId();
        }
        txtIdProduto.setText("" + idProduto);
    }

    protected void setStatusSalvar() {
        super.setStatusSalvar();
        idProduto = null;
    }

    private MarcaProduto getMarcaProduto() {
        if (cmbMarca.getSelectedIndex() > 0) {
            return (MarcaProduto) cmbMarca.getSelectedItem();
        } else {
            return null;
        }
    }

    private UnidadeMedida getUnidadeMedida() {
        if (cmbUnidadeMedida.getSelectedIndex() > 0) {
            return (UnidadeMedida) cmbUnidadeMedida.getSelectedItem();
        } else {
            return null;
        }
    }

    private BigDecimal getBigDecimal(String string) throws ParseException {
        if (string.isEmpty() || string.equals("0,00")) {
            return null;
        } else {
            return new BigDecimal("" + formatoMoeda.parse(string));
        }

    }

    @Override
    protected Produto getEntidade() {
        try {
            produto.setIdProduto(Integer.parseInt(txtIdProduto.getText()));
            produto.setNome(txtNomeProduto.getText());
//            produto.setCategoriaProduto(getCategoriaProduto());
            produto.setCodigoBarra(Util.returnNullIfEmpty(txtCodigoBarra.getText()));
            produto.setMarcaProduto(getMarcaProduto());
            produto.setPreco(getBigDecimal(txtPreco.getText()));
            produto.setQuantidadeMinima(Integer.parseInt(txtQuantidadeMinima.getText()));
            produto.setUnidadeMedida(getUnidadeMedida());
            produto.setValorMedida(getBigDecimal(txtValorMedida.getText()));
            produto.setAntecedenciaVencimento(Integer.parseInt(txtAntecedenciaVencimento.getText()));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter valor.", "Erro", 0);
        }
        return produto;
    }

    @Override
    public void setEnabledObjctes(boolean valor) {
        cmbCategoria.setEnabled(valor);
        if (!valor || (valor && cmbCategoria.getSelectedIndex() > 0)) {
            cmbMarca.setEnabled(valor);
        }
        txtNomeProduto.setEnabled(valor);
        txtCodigoBarra.setEnabled(valor);
        txtValorMedida.setEnabled(valor);
        cmbUnidadeMedida.setEnabled(valor);
        txtQuantidadeMinima.setEnabled(valor);
        txtPreco.setEnabled(valor);
        txtAntecedenciaVencimento.setEnabled(valor);
    }

    @Override
    public void setEmptyObjects() {
        txtIdProduto.setText("");
        cmbCategoria.setSelectedIndex(0);
        cmbMarca.setSelectedIndex(0);
        txtNomeProduto.setText("");
        txtCodigoBarra.setText("");
        txtValorMedida.setText("");
        cmbUnidadeMedida.setSelectedIndex(0);
        txtQuantidadeMinima.setText("");
        txtPreco.setText("");
        txtAntecedenciaVencimento.setText("");
        txtQuantidadeEstoque.setText("");
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
        tblProduto = myTable;
    }

    @Override
    public Produto getGenericPesquisaDomain() {
        produtoPesquisa.setNome(txtNomeProdutoPesquisa.getText());
        if (txtIdProdutoPesquisa.getText() != null && !txtIdProdutoPesquisa.getText().isEmpty()) {
            produtoPesquisa.setIdProduto(Integer.parseInt(txtIdProdutoPesquisa.getText()));
        } else {
            produtoPesquisa.setIdProduto(null);
        }
//        if (cmbCategoriaPesquisa.getSelectedIndex() > 0) {
//            produtoPesquisa.setCategoriaProduto((CategoriaProduto) cmbCategoriaPesquisa.getSelectedItem());
//        } else {
//            produtoPesquisa.setCategoriaProduto(null);
//
//        }
        if (cmbMarcaPesquisa.getSelectedIndex() > 0) {
            produtoPesquisa.setMarcaProduto((MarcaProduto) cmbMarcaPesquisa.getSelectedItem());
        } else {
            produtoPesquisa.setMarcaProduto(null);
        }
        return produtoPesquisa;
    }

    @Override
    public ProdutoBusiness getGenericBusiness() {
        return instanceProdutoBusiness();
    }

    @Override
    protected void setEntidade(Produto entidade) {
        produto = (Produto) entidade;
        txtIdProduto.setText("" + produto.getIdProduto());
        cmbCategoria.setSelectedItem(produto.getCategoriaProduto());
        preencheMarca();
        cmbMarca.setSelectedItem(produto.getMarcaProduto());
        txtNomeProduto.setText(produto.getNome());
        txtCodigoBarra.setText(produto.getCodigoBarra());
        txtValorMedida.setText("" + produto.getValorMedida());
        cmbUnidadeMedida.setSelectedItem(produto.getUnidadeMedida());
        txtQuantidadeMinima.setText("" + produto.getQuantidadeMinima());
        if (produto.getPreco() != null) {
            txtPreco.setText("" + produto.getPreco());
        } else {
            txtPreco.setText("");

        }
        txtAntecedenciaVencimento.setText("" + produto.getAntecedenciaVencimento());
        txtQuantidadeEstoque.setText("" + produto.getTotalEstoque());

    }
}
