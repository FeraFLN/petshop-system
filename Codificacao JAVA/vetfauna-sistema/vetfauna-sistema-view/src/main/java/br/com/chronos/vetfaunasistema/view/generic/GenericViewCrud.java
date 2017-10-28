/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.view.generic;

import br.com.chronos.vetfaunasistema.business.generic.GenericBusiness;
import br.com.chronos.vetfaunasistema.business.generic.exception.GenericBusinessException;
import br.com.chronos.vetfaunasistema.domain.GenericDomain;
import br.com.chronos.vetfaunasistema.utilitarios.enums.StatusTela;
import br.com.chronos.vetfaunasistema.utilitarios.util.Util;
import br.com.chronos.vetfaunasistema.view.component.Repaint;
import br.com.chronos.vetfaunasistema.view.component.table.MyTable;
import br.com.chronos.vetfaunasistema.view.main.TelaPrincipal;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Fernando
 */
public abstract class GenericViewCrud<E extends GenericDomain, T extends GenericBusiness> extends JPanel implements Repaint {

    private MyTable myTable;
    private List<E> l;
    private E genericDomain;
    protected StatusTela statusAtual;
    private CrudView crudView;
    private TelaPrincipal telaPrincipal;

    public GenericViewCrud() {
        instanceObjects();
        //this.telaPrincipal = telaPrincipal;
        this.myTable = new MyTable(genericDomain);
        setTable();
    }

    private void instanceObjects() {
        try {
            this.genericDomain = getEntityClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Favor entrar em contato com o desenvolvedor.", "Erro", 0);
        }
    }

    protected void setCrudView(CrudView crudView) {
        this.crudView = crudView;
    }

    private void setTable() {
        setTable(myTable);
    }

    /**
     * Responsável retornar o tipo da classe.
     * @return O tipo da classe.
     */
    protected Class<E> getEntityClass() {
        ParameterizedType tipo = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) tipo.getActualTypeArguments()[0];
    }

    protected Class<E> getBusinessClass() {
        ParameterizedType tipo = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) tipo.getActualTypeArguments()[1];
    }

    protected MyTable getMyTable() {
        return myTable;
    }

    protected void setTelaPrincipal(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    public abstract JButton getBtnSalvar();

    public abstract JButton getBtnAlterar();

    public abstract JButton getBtnExcluir();

    public abstract JButton getBtnPesquisar();

    public abstract JButton getBtnCancelar();

    public abstract JButton getBtnNovo();

    public abstract void setEnabledObjctes(boolean valor);

    public abstract void setEmptyObjects();

    public abstract E getGenericPesquisaDomain();

    protected abstract E getEntidade();

    protected abstract void setEntidade(E entidade);

    public abstract T getGenericBusiness();

    public abstract void setTable(MyTable myTable);

    @Override
    public void repaint(List<? extends GenericDomain> list, Class<?> clazz) {
        if (getRepaintComponets() == null) {
            return;
        }
        Util.repaintComponents(list, clazz, getRepaintComponets());
    }

    protected Component[] getRepaintComponets() {
        return null;
    }

    protected void actions() {
        actionClickTable();
        actionPesquisa();
        actionSalvar();
        actionNovo();
        actionAlterar();
        actionCancelar();
        actionExcluir();
    }

    public void setVisible(boolean valor) {
        if (valor) {
            setStatusInicial();
        } else {
            crudView.setVisible(valor);
        }
    }

    private void actionClickTable() {
        myTable.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setStatusPreencher();

            }
        });
        myTable.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyPressed(java.awt.event.KeyEvent evt) {
                int key = evt.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    setStatusPreencher();
                }
            }
        });
    }

    private void actionExcluir() {
        getBtnExcluir().addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (myTable.getSelectedRows().length > 0 && myTable.getSelectedRows().length < 5) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir esse(s) registro(s)?", "Aviso", 0);
                    try {
                        if (resposta == 0) {
                            for (int i = 0; i < myTable.getSelectedRows().length; i++) {
                                getGenericBusiness().delete(l.get(myTable.getSelectedRows()[i]));
                            }
                            JOptionPane.showMessageDialog(null, "Registro(s) excluído(s) com sucesso.", "Aviso", 1);
                            setStatusDeletar();
                            repaintObject();
                        }
                    } catch (GenericBusinessException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha no minimo 1 e no maximo 5 registros para exclusão.", "Erro", 0);

                }

            }
        });

    }

    private void actionPesquisa() {
        getBtnPesquisar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setStatusPesquisar();
            }
        });
    }

    private void actionSalvar() {
        getBtnSalvar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    salvar();
                    setStatusSalvar();
                    repaintObject();
                } catch (GenericBusinessException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
                }
            }
        });
    }

    private void actionNovo() {
        getBtnNovo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusAtual = StatusTela.NOVO;
                setStatusNovo();
            }
        });
    }

    private void actionAlterar() {
        getBtnAlterar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusAtual = StatusTela.ATUALIZAR;
                setStatusAlterar();
            }
        });
    }

    private void actionCancelar() {
        getBtnCancelar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusAtual = StatusTela.INICIAL;
                setStatusCancelar();
            }
        });
    }

    private void preencher() {
        if (myTable.getSelectedRow() < 0) {
            return;
        }
        genericDomain = l.get(myTable.getSelectedRow());
        setEntidade(genericDomain);
    }

    private void pesquisa() {
        try {
            l = getGenericBusiness().select(getGenericPesquisaDomain());
            myTable.fillTable(l);
            myTable.getModel();
        } catch (GenericBusinessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
        }
    }

    protected void salvar() throws GenericBusinessException {
        if (statusAtual.equals(StatusTela.NOVO)) {
            getGenericBusiness().insert(getEntidade());
            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso.", "Aviso", 1);
        } else if (statusAtual.equals(StatusTela.ATUALIZAR)) {
            getGenericBusiness().update(getEntidade());
            JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso.", "Aviso", 1);
        }

    }

    private void repaintObject() {
        if (telaPrincipal != null) {
            try {
                E domain = getEntityClass().newInstance();
                List<E> le = getGenericBusiness().select(domain);
                telaPrincipal.repaintAll(le, domain.getClass());
            } catch (GenericBusinessException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", 0);
            } catch (InstantiationException | IllegalAccessException ex) {
                JOptionPane.showMessageDialog(null, "Favor entrar em contato com o desenvolvedor.", "Erro", 0);
            }
        }
    }

    protected void setStatusNovo() {
        getBtnCancelar().setEnabled(true);
        getBtnSalvar().setEnabled(true);
        getBtnNovo().setEnabled(false);
        getBtnExcluir().setEnabled(false);
        getBtnAlterar().setEnabled(false);
        setEnabledObjctes(true);
        setEmptyObjects();
    }

    protected void setStatusPreencher() {
        getBtnNovo().setEnabled(true);
        getBtnAlterar().setEnabled(true);
        getBtnExcluir().setEnabled(true);
        getBtnCancelar().setEnabled(false);
        getBtnSalvar().setEnabled(false);
        setEnabledObjctes(false);
        preencher();
    }

    protected void setStatusAlterar() {
        getBtnCancelar().setEnabled(true);
        getBtnSalvar().setEnabled(true);
        getBtnNovo().setEnabled(false);
        getBtnExcluir().setEnabled(false);
        getBtnAlterar().setEnabled(false);
        setEnabledObjctes(true);

    }

    protected void setStatusSalvar() {
        getBtnNovo().setEnabled(true);
        getBtnCancelar().setEnabled(false);
        getBtnSalvar().setEnabled(false);
        getBtnExcluir().setEnabled(false);
        getBtnAlterar().setEnabled(false);
        setEmptyObjects();
        setEnabledObjctes(false);
        if (myTable.getRowCount() != 0) {
            pesquisa();
        }
    }

    protected void setStatusInicial() {
        getBtnNovo().setEnabled(true);
        getBtnCancelar().setEnabled(false);
        getBtnSalvar().setEnabled(false);
        getBtnExcluir().setEnabled(false);
        getBtnAlterar().setEnabled(false);
        setEmptyObjects();
        setEnabledObjctes(false);
        myTable.cleanTable();
    }

    protected void setStatusCancelar() {
        getBtnNovo().setEnabled(true);
        getBtnCancelar().setEnabled(false);
        getBtnSalvar().setEnabled(false);
        getBtnExcluir().setEnabled(false);
        getBtnAlterar().setEnabled(false);
        setEmptyObjects();
        setEnabledObjctes(false);
        myTable.getSelectionModel().removeSelectionInterval(0, myTable.getRowCount());
    }

    protected void setStatusDeletar() {
        getBtnNovo().setEnabled(true);
        getBtnCancelar().setEnabled(false);
        getBtnSalvar().setEnabled(false);
        getBtnExcluir().setEnabled(false);
        getBtnAlterar().setEnabled(false);
        setEnabledObjctes(false);
        setEmptyObjects();
        pesquisa();
    }

    protected void setStatusPesquisar() {
        getBtnNovo().setEnabled(true);
        getBtnCancelar().setEnabled(false);
        getBtnSalvar().setEnabled(false);
        getBtnExcluir().setEnabled(false);
        getBtnAlterar().setEnabled(false);
        setEnabledObjctes(false);
        setEmptyObjects();
        pesquisa();
    }

    protected void reload() {
        int[] valor = getMyTable().getSelectedRows();
        setStatusPesquisar();
        for (int i = 0; i < valor.length; i++) {
            getMyTable().getSelectionModel().setSelectionInterval(valor[i], valor[i]);
        }
        setStatusPreencher();
    }
}
