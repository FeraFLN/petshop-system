/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.enums;

/**
 *
 * @author Fernando
 */
public enum Sexo {

    M("Macho"),
    F("Fêmea");
    private String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    public String getValue() {
      return this.sexo;
    }

    public String toString() {
        return sexo;
    }
    
}
