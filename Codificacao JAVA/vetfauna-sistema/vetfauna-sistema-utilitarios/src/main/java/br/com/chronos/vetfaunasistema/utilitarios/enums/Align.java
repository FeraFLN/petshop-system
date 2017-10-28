/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.enums;

/**
 *
 * @author Fernando
 */
public enum Align {
   RIGHT,
   LEFT,
   CENTER;
   
   public static int value(Align align){
       if(align.equals(Align.CENTER))
           return 0;
       if(align.equals(Align.LEFT))
           return 2;
       if(align.equals(Align.RIGHT))
           return 4;
       else return -1;
   }
}
