package com.sapient.credit.card.exercise.service;

import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.model.ServiceResponse;
import com.sapient.credit.card.exercise.model.Status;
import com.sapient.credit.card.exercise.repository.CreditCardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CreditCardServiceImplTest {
    private CreditCardService creditCardService;

    @Mock
    private CreditCardRepository repository;

    @Before
    public void setup(){
        creditCardService = new CreditCardServiceImpl(repository);
    }

    @Test
    public void testSuccessfullCardCreate() {
        CreditCard card = new CreditCard("Test User", "6011008785309789336", 0.0);

        when(repository.save(any())).thenReturn(card);

        ServiceResponse response = creditCardService.createCreditCard(card);

        assertEquals(Status.SUCCESS, response.getStatus());
    }
    
    @Test
    public void testSuccessfulGetAll() {
        List<CreditCard> cards = Arrays.asList(new CreditCard("Neeraj", "6011008785309789336", 0.0));

        when(repository.findAll()).thenReturn(cards);

        List<CreditCard> resp = creditCardService.getAllCreditCards();

        assertEquals(1, cards.size());
    }
}
