package com.github.lf2a.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>ClienteUpdate.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 18/03/2021
 */
@Constraint(validatedBy = ClienteUpdateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteUpdate {

    String message() default "Erro de validação";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
