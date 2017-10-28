/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.util;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Fernando
 */
public class Util {

    private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    public static String capitalize(String string) {
        String palavra = string;
        String firstLetter = palavra.substring(0, 1);
        String lastPart = palavra.substring(1);
        return firstLetter.toUpperCase() + lastPart;
    }

    public static String returnNullIfEmpty(String string) {
        return string.equals("") ? null : string;
    }

    public static String formatterDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return formato.format(date);
        }
    }
    public static Date formatterDate(String date) throws ParseException {
       if (date.equals("")) {
            return null;
        } else {
            return formato.parse(date);
        }
    }

    public static Date parseDate(String date) throws ParseException {
        if (date == null || date.isEmpty()) {
            return null;
        } else {
            return formato.parse(date);
        }
    }

    public static Integer parseInt(String valor) throws ParseException {
        if (valor == null || valor.isEmpty()) {
            return null;
        } else {
            return Integer.parseInt(valor);
        }
    }

    public static void repaintComponents(List list, Class<?> clazz,
            Component... components) {
        for (Component component : components) {
            if (component != null && JComboBox.class.isInstance(component)) {
                JComboBox box = (JComboBox) component;
                if (box.getItemCount() > 0) {
                    Object o = box.getItemAt(0);
                    if (o != null && clazz.equals(o.getClass())) {
                        Object firstValue = o;
                        box.removeAllItems();
                        box.addItem(firstValue);
                        for (Object obj : list) {
                            box.addItem(obj);
                        }
                    }
                }
            }
        }

    }
}
