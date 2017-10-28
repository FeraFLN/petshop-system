/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.view.component.jformatter;

import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author Fernando
 */
public class MaxLengthTextDocument extends PlainDocument {

    private static final SimpleAttributeSet nullAttribute = new SimpleAttributeSet();
    private static final long serialVersionUID = -3802846632709128803L;
    private TypeMask typeNumeberFormatter;
    private int maxLength = 10;
    private String valueInit;

    public MaxLengthTextDocument(TypeMask typeNumeberFormatter, int maxLenth, String valueInit) {
        this.maxLength = maxLenth;
        this.valueInit = valueInit;
        this.typeNumeberFormatter = typeNumeberFormatter;
    }

    private void fomatterNumber(StringBuffer mascarado, String original, String str) throws BadLocationException {
        //limpa o campo  
        remove(-1, getLength());

        mascarado.append((original + str).replaceAll("[^0-9]", ""));
        for (int i = 0; i < mascarado.length(); i++) {
            if (!Character.isDigit(mascarado.charAt(i))) {
                mascarado.deleteCharAt(i);
            }
        }
        if (typeNumeberFormatter.equals(TypeMask.DECIMAL)) {
            Long number = new Long(mascarado.toString());
            mascarado.replace(0, mascarado.length(), number.toString());
            mascaraMoney(mascarado);
        } else if (typeNumeberFormatter.equals(TypeMask.INTEGER)) {
            if (mascarado.toString().length() <= 2) {
                Long number = new Long(mascarado.toString());
                mascarado.replace(0, mascarado.length(), number.toString());
            }
        } else if (typeNumeberFormatter.equals(TypeMask.CPF)) {
            mascaraCpf(mascarado);
        } else if (typeNumeberFormatter.equals(TypeMask.PIS)) {
            mascaraPis(mascarado);
        } else if (typeNumeberFormatter.equals(TypeMask.DATA)) {
            mascaraData(mascarado);
        } else if (typeNumeberFormatter.equals(TypeMask.FONE)) {
            mascaraFone(mascarado);
        } else if (typeNumeberFormatter.equals(TypeMask.CEP)) {
            mascaraCep(mascarado);
        }
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String original = getText(0, getLength());
        if (original.length() < maxLength) {
            StringBuffer mascarado = new StringBuffer();
            if (a != nullAttribute) {
                if (!typeNumeberFormatter.equals(TypeMask.TEXT)) {
                    fomatterNumber(mascarado, original, str);
                    super.insertString(0, mascarado.toString(), a);
                } else {
                    super.insertString(offs, str, a);
                }
            } else {
                if (original.length() == 0) {
                    super.insertString(0, valueInit, a);
                }
            }
        }
        // Permite apenas digitar até 16 caracteres (9.999.999.999,99)  
    }

    private void mascaraMoney(StringBuffer mascarado) {
        int qtdCasasDecimais = 2;
        if (valueInit != null && !valueInit.isEmpty()) {
            qtdCasasDecimais = valueInit.split(",")[1].length();
        }
        if (mascarado.length() <= qtdCasasDecimais) {
            for (int i = mascarado.length(); i < qtdCasasDecimais; i++) {
                mascarado.insert(0, "0");
            }
            mascarado.insert(0, ",");
            mascarado.insert(0, "0");
        } else {
            mascarado.insert(mascarado.length() - qtdCasasDecimais, ",");
        }

        if (mascarado.length() > 6) {
            mascarado.insert(mascarado.length() - 6, '.');
            if (mascarado.length() > 10) {
                mascarado.insert(mascarado.length() - 10, '.');
                if (mascarado.length() > 14) {
                    mascarado.insert(mascarado.length() - 14, '.');
                }
            }
        }
    }

    private void mascaraCpf(StringBuffer mascarado) {
        if (mascarado.length() >= 3) {
            mascarado.insert(mascarado.length() - 2, "-");
        }
        if (mascarado.length() > 6) {
            mascarado.insert(mascarado.length() - 6, '.');
            if (mascarado.length() > 10) {
                mascarado.insert(mascarado.length() - 10, '.');
            }
        }
    }

    private void mascaraFone(StringBuffer mascarado) {
        if (mascarado.length() >= 5) {
            mascarado.insert(mascarado.length() - 4, "-");
        }
    }
    private void mascaraCep(StringBuffer mascarado) {
        if (mascarado.length() >= 6) {
            mascarado.insert(mascarado.length() - 3, "-");
        }
    }

    private void mascaraPis(StringBuffer mascarado) {
        if (mascarado.length() >= 2) {
            mascarado.insert(mascarado.length() - 1, "-");
        }
        if (mascarado.length() > 3) {
            mascarado.insert(mascarado.length() - 4, '.');
            if (mascarado.length() > 10) {
                mascarado.insert(mascarado.length() - 10, '.');
            }
        }
    }

    private void mascaraData(StringBuffer mascarado) {
        if (mascarado.length() >= 5) {
            mascarado.insert(mascarado.length() - 4, "/");
        }
        if (mascarado.length() > 7) {
            mascarado.insert(mascarado.length() - 7, '/');
        }
    }

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        if (typeNumeberFormatter.equals(TypeMask.TEXT)) {
            super.remove(offs, len);
            return;
        }

        if (len == getLength()) {
            super.remove(0, len);
            if (offs != -1) {
                insertString(0, "", nullAttribute);
            }
        } else {
            String original = getText(0, getLength()).substring(0, offs) + getText(0, getLength()).substring(offs + len);
            super.remove(0, getLength());
            insertString(0, original, null);
        }
    }
}
