package com.sapient.credit.card.exercise.service;

import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.model.ServiceResponse;
import com.sapient.credit.card.exercise.model.Status;
import com.sapient.credit.card.exercise.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService{
    private CreditCardRepository repository;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository repository){
        this.repository = repository;
    }

    private static final Logger LOG = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    @Override
    public ServiceResponse createCreditCard(CreditCard card) {
        LOG.info("inside create credit card");
        CreditCard res = repository.save(card);
        ServiceResponse resp = new ServiceResponse(Status.SUCCESS);
        LOG.info("Credit card successfully created {}", card.getCardNumber());
        return resp;
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        LOG.info("inside get all credit cards");
        return repository.findAll();
    }
}
