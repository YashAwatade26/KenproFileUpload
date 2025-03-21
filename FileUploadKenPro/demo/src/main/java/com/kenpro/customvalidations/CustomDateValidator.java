package com.kenpro.customvalidations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomDateValidatorClass.class)
public @interface CustomDateValidator {
	String message() default "Date must be today's date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
