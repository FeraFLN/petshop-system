/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.validatios;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.TableView;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Align;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
import br.com.chronos.vetfaunasistema.utilitarios.util.TableComparator;
import br.com.chronos.vetfaunasistema.utilitarios.util.Util;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.exceptions.InvokeEntiteValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class TableReflection {

    private static NumberFormat formatoMoeda;
    private static Align[] align;

    public static String[] titleTable(Object theObject) throws InvokeEntiteValidationException {
        List<Table> lista = new ArrayList<>();
        int j = 0;
        try {
            if (theObject == null) {
                throw new InvokeEntiteValidationException("Entidade está nula.");
            }
            Class clazz;
            clazz = Class.forName(theObject.getClass().getName());
            Field[] field = clazz.getDeclaredFields();
            align = new Align[field.length];
            for (int i = 0; i < field.length; i++) {
                TableView tableValue = field[i].getAnnotation(TableView.class);
                if (tableValue != null) {
                    String title = tableValue.title();
                    if (title != null && !title.isEmpty()) {
                        lista.add(new Table(tableValue.order(), tableValue.title()));
                    }
                    align[j] = tableValue.align();
                    j++;
                }
            }
            Collections.sort(lista, new TableComparator());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableReflection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getVector(lista);
    }

    private static String[] getVector(List<Table> list) {
        String[] title = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            title[i] = list.get(i).getTitle();
        }
        return title;
    }

    public static Object[] valueTable(Object theObject, int vectoLength) throws InvokeEntiteValidationException {
        Object[] o = new Object[vectoLength];
        int j = 0;
        try {
            if (theObject == null) {
                throw new InvokeEntiteValidationException("Entidade está nula.");
            }
            Class clazz;
            clazz = Class.forName(theObject.getClass().getName());
            Field[] field = clazz.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                TableView tableValue = field[i].getAnnotation(TableView.class);
                if (tableValue != null) {
                    String nameMethod = "get" + capitalize(field[i].getName());//Util.capitalize(field[i].getName());
                    Method method = clazz.getMethod(nameMethod);
                    if (!tableValue.name().isEmpty()) {
                        Object o2 = method.invoke(theObject);
                        if (o2 != null) {
                            nameMethod = "get" + capitalize(tableValue.name());
                            method = o2.getClass().getMethod(nameMethod);
                            o[j] = method.invoke(o2);
                        }
                    } else {
                        o[j] = method.invoke(theObject);
                    }

                    o[j] = mask(o[j], tableValue.typeMask());
                    j++;
                }
            }

        } catch (IllegalAccessException ex) {
            Logger.getLogger(TableReflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TableReflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TableReflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TableReflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TableReflection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableReflection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    public static Align[] getAlign() {
        return align;
    }

    private static Object mask(Object o, TypeMask typeMask) {
        if (o == null) {
            return o;
        }
        if (TypeMask.NOTHING.equals(typeMask)) {
            return o;
        }
        if (TypeMask.MONETARIO.equals(typeMask)) {
            formatoMoeda = DecimalFormat.getCurrencyInstance();
            return formatoMoeda.format(o);
        }
        if (TypeMask.DECIMAL.equals(typeMask)) {
            formatoMoeda = DecimalFormat.getInstance();
            return formatoMoeda.format(o);
        }
        if (TypeMask.DATA.equals(typeMask)) {
            return Util.formatterDate((Date) o);
        }
        return o;
    }

    private static String capitalize(String string) {
        String firstLetter = string.substring(0, 1);
        String lastPart = string.substring(1);
        return firstLetter.toUpperCase() + lastPart;
    }
}
