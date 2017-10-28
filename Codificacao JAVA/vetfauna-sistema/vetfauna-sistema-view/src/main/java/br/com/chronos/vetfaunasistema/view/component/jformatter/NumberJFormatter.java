/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.view.component.jformatter;

import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author Fernando
 */
public class NumberJFormatter extends JFormattedTextField {

    private static final long serialVersionUID = -5712106034509737967L;
    private int maxLength = 10;
    private TypeMask typeNumeberFormatter;

    /** 
     * Creates a new instance of JMoneyField 
     */
    public NumberJFormatter(TypeMask typeNumeberFormatter, int maxLenth, String valueInit) {
        this.maxLength = maxLenth;
        this.typeNumeberFormatter = typeNumeberFormatter;
        this.setHorizontalAlignment(JTextField.CENTER);
        this.setDocument(new MaxLengthTextDocument(typeNumeberFormatter, maxLenth, valueInit));
        this.addFocusListener(new MoneyFieldFocusListener());
        this.setText(valueInit);
        this.addCaretListener(
                new CaretListener() {

                    public void caretUpdate(CaretEvent e) {
                        if (e.getDot() != getText().length()) {
                            getCaret().setDot(getText().length());
                        }
                    }
                });




    }

    public TypeMask getTypeNumeberFormatter() {
        return typeNumeberFormatter;
    }

    public int getMaxLength() {
        return maxLength;
    }

    private final class MoneyFieldFocusListener extends FocusAdapter {
        private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        public void focusGained(FocusEvent e) {
            selectAll();
        }

        public void focusLost(FocusEvent e) {
            if ((!getTypeNumeberFormatter().equals(TypeMask.DECIMAL)
                    && !getTypeNumeberFormatter().equals(TypeMask.INTEGER)
                    && !getTypeNumeberFormatter().equals(TypeMask.NUMERIC_STRING))
                    && (getText().length() != getMaxLength())) {
                setText("");
            }
            if (getTypeNumeberFormatter().equals(TypeMask.DATA)&&!getText().isEmpty()) {
                formato.setLenient(false);
                try {
                    formato.parse(getText());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data inválida.", "Erro", 0);
                    setText("");
                    requestFocus();
                }
            }
        }
    }
}
