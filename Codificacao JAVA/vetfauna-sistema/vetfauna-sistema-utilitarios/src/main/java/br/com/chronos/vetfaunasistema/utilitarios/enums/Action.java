/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.enums;

/**
 *
 * @author Fernando
 */
public enum Action {
    DV,//DON'T VALIDATION
    I,//INSERT
    D,
    U,
    S,
    I_D,
    I_U,
    I_S,
    D_U,
    D_S,
    U_S,
    I_D_U,
    I_D_S,
    I_U_S,
    D_U_S;
    public static boolean validationSelect(Action actionValidation) {
        if (S.equals(actionValidation)) {
            return true;
        } else if (I_S.equals(actionValidation)) {
            return true;
        } else if (D_S.equals(actionValidation)) {
            return true;
        } else if (U_S.equals(actionValidation)) {
            return true;
        } else if (I_D_S.equals(actionValidation)) {
            return true;
        } else if (D_U_S.equals(actionValidation)) {
            return true;
        } else if (I_U_S.equals(actionValidation)) {
            return true;
        }
        return false;
    }
    public static boolean validationUpdate(Action actionValidation) {
        if (U.equals(actionValidation)) {
            return true;
        } else if (I_U.equals(actionValidation)) {
            return true;
        } else if (D_U.equals(actionValidation)) {
            return true;
        } else if (U_S.equals(actionValidation)) {
            return true;
        } else if (I_D_U.equals(actionValidation)) {
            return true;
        } else if (D_U_S.equals(actionValidation)) {
            return true;
        } else if (I_U_S.equals(actionValidation)) {
            return true;
        }
        return false;
    }

    public static boolean validationInsert(Action actionValidation) {
        if (I.equals(actionValidation)) {
            return true;
        } else if (I_D.equals(actionValidation)) {
            return true;
        } else if (I_D_S.equals(actionValidation)) {
            return true;
        } else if (I_D_U.equals(actionValidation)) {
            return true;
        } else if (I_S.equals(actionValidation)) {
            return true;
        } else if (I_U.equals(actionValidation)) {
            return true;
        } else if (I_U_S.equals(actionValidation)) {
            return true;
        }
        return false;
    }
    public static boolean validationDelete(Action actionValidation) {
        if (D.equals(actionValidation)) {
            return true;
        } else if (I_D.equals(actionValidation)) {
            return true;
        } else if (I_D_S.equals(actionValidation)) {
            return true;
        } else if (I_D_U.equals(actionValidation)) {
            return true;
        } else if (D_S.equals(actionValidation)) {
            return true;
        } else if (D_U.equals(actionValidation)) {
            return true;
        } else if (D_U_S.equals(actionValidation)) {
            return true;
        }
        return false;
    }
}
