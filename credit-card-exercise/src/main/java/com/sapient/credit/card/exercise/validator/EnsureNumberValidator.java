package com.sapient.credit.card.exercise.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnsureNumberValidator implements ConstraintValidator<EnsureNumber, Object> {
    private EnsureNumber ensureNumber;

    @Override
    public void initialize(EnsureNumber constraintAnnotation) {
        this.ensureNumber = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        String regex = "-?[0-9]+";
        String data = String.valueOf(value);
        return data.matches(regex);
    }
}
