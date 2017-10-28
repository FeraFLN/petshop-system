/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.annotation;

import br.com.chronos.vetfaunasistema.utilitarios.enums.Action;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Fernando
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntiteValidation {
     Action action() default Action.DV;
     int minValue() default Integer.MIN_VALUE;
     int maxValue() default Integer.MAX_VALUE;
     String inField() default "";
     String nameField(); 
     String messMinValue() default "Valor do(a) [nameCampo] não pode ser menor que [minValue].";
     String messMaxValue() default "Valor do(a) [nameCampo] não pode ser maior que [maxValue].";
     String message() default "Campo referente a(o) [nameCampo] tem preenchimento obrigatório.";

}
