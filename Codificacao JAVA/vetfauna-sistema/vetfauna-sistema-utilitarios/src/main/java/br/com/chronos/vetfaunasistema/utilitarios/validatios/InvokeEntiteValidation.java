/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.validatios;

import br.com.chronos.vetfaunasistema.utilitarios.annotation.EntiteValidation;
import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import br.com.chronos.vetfaunasistema.utilitarios.validatios.exceptions.InvokeEntiteValidationException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import br.com.chronos.vetfaunasistema.utilitarios.util.Util;

/**
 *
 * @author Fernando 
 */
public class InvokeEntiteValidation {

    public static void deleteValidation(Object theObject) throws InvokeEntiteValidationException {
        validationEntite(theObject);
        Class clazz;
        try {
            clazz = Class.forName(theObject.getClass().getName());
            Field[] field = clazz.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                EntiteValidation fieldValidation = field[i].getAnnotation(EntiteValidation.class);
                if (fieldValidation != null) {
                    boolean validation = Action.validationDelete(fieldValidation.action());
                    if (validation) {
                        validation(fieldValidation, clazz, field[i].getName(), theObject);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchMethodException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (IllegalAccessException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (InvocationTargetException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchFieldException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        }
    }

    public static void insertValidation(Object theObject) throws InvokeEntiteValidationException {
        validationEntite(theObject);
        Class clazz;
        try {
            clazz = Class.forName(theObject.getClass().getName());
            Field[] field = clazz.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                EntiteValidation fieldValidation = field[i].getAnnotation(EntiteValidation.class);
                if (fieldValidation != null) {
                    boolean validation = Action.validationInsert(fieldValidation.action());
                    if (validation) {
                        validation(fieldValidation, clazz, field[i].getName(), theObject);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchMethodException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (IllegalAccessException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (InvocationTargetException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchFieldException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        }
    }

    public static void validationEntite(Object theObject) throws InvokeEntiteValidationException {
        if (theObject == null) {
            throw new InvokeEntiteValidationException("Entidade está nula.");
        }
    }

    public static void updateValidation(Object theObject) throws InvokeEntiteValidationException {
        validationEntite(theObject);
        Class clazz;
        try {
            clazz = Class.forName(theObject.getClass().getName());
            Field[] field = clazz.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                EntiteValidation fieldValidation = field[i].getAnnotation(EntiteValidation.class);
                if (fieldValidation != null) {
                    boolean validationDelete = Action.validationUpdate(fieldValidation.action());
                    if (validationDelete) {
                        validation(fieldValidation, clazz, field[i].getName(), theObject);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchMethodException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (IllegalAccessException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (InvocationTargetException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchFieldException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        }
    }

    public static void selectValidation(Object theObject) throws InvokeEntiteValidationException {
        validationEntite(theObject);
        Class clazz;
        try {
            clazz = Class.forName(theObject.getClass().getName());
            Field[] field = clazz.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                EntiteValidation fieldValidation = field[i].getAnnotation(EntiteValidation.class);
                if (fieldValidation != null) {
                    boolean validation = Action.validationSelect(fieldValidation.action());
                    if (validation) {

                        validation(fieldValidation, clazz, field[i].getName(), theObject);

                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchMethodException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (IllegalAccessException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (InvocationTargetException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        } catch (NoSuchFieldException ex) {
            throw new InvokeEntiteValidationException("Erro ao tentar validar a entidade.");
        }
    }

    private static void validation(EntiteValidation fieldValidation, Class clazz, String fieldName, Object theObject) throws InvokeEntiteValidationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {
        String nameMethod = "get" + Util.capitalize(fieldName);
        Method method = clazz.getMethod(nameMethod);
        Object o = method.invoke(theObject);
        if (o == null || o.toString().isEmpty() || o.equals(0)) {
            throw new InvokeEntiteValidationException(fieldValidation.message().replace("[nameCampo]", fieldValidation.nameField()));
        }
        if (fieldValidation != null && !fieldValidation.inField().isEmpty()) {
            nameMethod = "get" + Util.capitalize(fieldValidation.inField());
            method = o.getClass().getMethod(nameMethod);
            Object o2 = method.invoke(o);
            if (o2 == null || o2.toString().isEmpty() || o2.equals(0)) {
                throw new InvokeEntiteValidationException(fieldValidation.message().replace("[nameCampo]", fieldValidation.nameField()));
            } else {
                o = o2;
            }
//            EntiteValidation fv = o.getClass().getDeclaredField(fieldValidation.inField()).getAnnotation(EntiteValidation.class);
//            validation(fv, o.getClass(), fieldValidation.inField(), o);
        }
        if (o instanceof Integer) {
            if (fieldValidation.minValue() > Integer.parseInt(o.toString())) {
                throw new InvokeEntiteValidationException(fieldValidation.messMinValue().replace("[minValue]", "" + fieldValidation.minValue()).replace("[nameCampo]", fieldValidation.nameField()));
            }
            if (fieldValidation.maxValue() < Integer.parseInt(o.toString())) {
                throw new InvokeEntiteValidationException(fieldValidation.messMaxValue().replace("[maxValue]", "" + fieldValidation.maxValue()).replace("[nameCampo]", fieldValidation.nameField()));
            }
        }

    }
}
