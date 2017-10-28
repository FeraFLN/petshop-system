/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chronos.vetfaunasistema.utilitarios.annotation;

import br.com.chronos.vetfaunasistema.utilitarios.enums.Align;
import br.com.chronos.vetfaunasistema.utilitarios.enums.TypeMask;
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
public @interface TableView {

    String title() default "";

    int order() default 1;
    
    String name() default "";
    
    TypeMask typeMask() default TypeMask.NOTHING;
    
    Align align() default Align.LEFT;
}
