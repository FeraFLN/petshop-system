/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.view.component.desktoppanel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class DesktopPaneFramePrincipal extends JDesktopPane{

    Image img;

    public DesktopPaneFramePrincipal() {
        try {
            img = javax.imageio.ImageIO.read(new File("pictures//backgroundgreen1.jpg"));
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo.", "Erro", 0);//throw new Exception("Imagem não encontrada.");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } 
    }
}
