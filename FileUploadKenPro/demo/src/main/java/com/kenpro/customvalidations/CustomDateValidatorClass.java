package com.kenpro.customvalidations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomDateValidatorClass implements ConstraintValidator<CustomDateValidator, LocalDateTime> {

	public boolean isValid(LocalDateTime dateTime, ConstraintValidatorContext context) {
        if (dateTime == null) {
            return false;
        }
        return dateTime.toLocalDate().isEqual(LocalDate.now()); // Check if the date matches today
    }
}