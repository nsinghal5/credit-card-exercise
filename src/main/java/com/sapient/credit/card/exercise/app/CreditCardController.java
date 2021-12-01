package com.sapient.credit.card.exercise.app;

import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.model.ServiceResponse;
import com.sapient.credit.card.exercise.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/credit-card")
public class CreditCardController {

    Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardService service;

    @PostMapping
    public ResponseEntity<ServiceResponse> createCreditCard(@Valid @RequestBody CreditCard card){
        logger.info("Credit Card creation request {}", card);
        ServiceResponse response = service.createCreditCard(card);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> getAll(){
        List<CreditCard> cards = service.getAllCreditCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
