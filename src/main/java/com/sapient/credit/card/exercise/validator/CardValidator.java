package com.sapient.credit.card.exercise.validator;

import com.sapient.credit.card.exercise.model.CreditCard;

public interface CardValidator {
    /**
     * To be implemented by any Validator
     *
     * @param card
     * @return
     */
    public boolean validCard(CreditCard card);
}
