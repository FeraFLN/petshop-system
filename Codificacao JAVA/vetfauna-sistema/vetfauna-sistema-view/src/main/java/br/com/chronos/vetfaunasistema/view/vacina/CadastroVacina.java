/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CadastroVacina.java
 *
 * Created on 01/02/2013, 17:29:05
 */
package br.com.chronos.vetfaunasistema.view.vacina;

import br.com.chronos.vetfaunasistema.view.generic.CrudView;
import br.com.chronos.vetfaunasistema.view.main.TelaPrincipal;
import javax.swing.JInternalFrame;

/**
 *
 * @author Fernando
 */
public class CadastroVacina extends JInternalFrame implements CrudView{

    private VacinaPanel vacinaPanel;

    /** Creates new form CadastroVacina */
    public CadastroVacina(TelaPrincipal telaPrincipal) {
        vacinaPanel = new VacinaPanel(this,telaPrincipal);
        initComponents();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = vacinaPanel;

        setTitle("Cadastro de Vacinas");
        getContentPane().setLayout(null);
        getContentPane().add(pnlPrincipal);
        pnlPrincipal.setBounds(0, 0, 433, 390);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-443)/2, (screenSize.height-420)/2, 443, 420);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables

    public void setVisible(boolean valor) {
        if (valor) {
            vacinaPanel.setVisible(valor);
        }
        super.setVisible(valor);
    }

   
   
}
