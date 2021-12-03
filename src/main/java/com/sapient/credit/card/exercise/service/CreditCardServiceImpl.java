package com.sapient.credit.card.exercise.service;

import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.model.ServiceResponse;
import com.sapient.credit.card.exercise.model.Status;
import com.sapient.credit.card.exercise.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    private CreditCardRepository repository;

    @Override
    public ServiceResponse createCreditCard(CreditCard card) {
        CreditCard res = repository.save(card);
        ServiceResponse resp = new ServiceResponse(Status.SUCCESS);
        return resp;
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return repository.findAll();
    }
}
