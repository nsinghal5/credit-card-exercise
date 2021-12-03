package com.sapient.credit.card.exercise.validator;

import com.sapient.credit.card.exercise.model.CreditCard;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnsureLuhnValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnsureLuhn {

    String message() default "Value should be Luhn 10 compliant";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean decimal() default false;
    //public boolean isValid(CreditCard card);
}
