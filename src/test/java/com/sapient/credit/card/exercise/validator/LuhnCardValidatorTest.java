package com.sapient.credit.card.exercise.validator;

import com.sapient.credit.card.exercise.model.CreditCard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class LuhnCardValidatorTest {

    LuhnCardValidator validator = null;

    @Before
    public void init() {
        validator = new LuhnCardValidator();
    }

    @Test
    public void testValidCardNumber_16digit() {
        CreditCard card = new CreditCard();
        card.setCardNumber("1358954993914435");

        boolean isValid = validator.validCard(card);
        assertTrue("Card should be valid", isValid);
    }

    @Test
    public void testInvalidCardNumber_16digit() {
        CreditCard card = new CreditCard();
        card.setCardNumber("1358954993914434");

        boolean isValid = validator.validCard(card);
        assertFalse("Card number should NOT be valid", isValid);
    }

    @Test
    public void testValidCardNumber_19digit(){
        CreditCard card = new CreditCard();
        card.setCardNumber("6011008785309789336");

        boolean isValid = validator.validCard(card);
        assertTrue("Card number should be valid", isValid);
    }

    @Test
    public void testInValidCardNumber_19digit(){
        CreditCard card = new CreditCard();
        card.setCardNumber("6011008785309789331");

        boolean isValid = validator.validCard(card);
        assertFalse("Card number should NOT be valid", isValid);
    }
}
