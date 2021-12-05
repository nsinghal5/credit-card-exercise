package com.sapient.credit.card.exercise.service;

import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.model.ServiceResponse;

import java.util.List;

public interface CreditCardService {
    public ServiceResponse createCreditCard(CreditCard card);
    public List<CreditCard> getAllCreditCards();
}
