package com.sapient.credit.card.exercise.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class EnsureLuhnValidatorTest {

    EnsureLuhnValidator validator = null;

    @Before
    public void init() {
        validator = new EnsureLuhnValidator();
    }

    @Test
    public void testValidCardNumber_16digit() {

        boolean isValid = validator.isValid("1358954993914435", null);
        assertTrue("Card should be valid", isValid);
    }

    @Test
    public void testInvalidCardNumber_16digit() {

        boolean isValid = validator.isValid("1358954993914434", null);
        assertFalse("Card number should NOT be valid", isValid);
    }

    @Test
    public void testValidCardNumber_19digit(){

        boolean isValid = validator.isValid("6011008785309789336", null);
        assertTrue("Card number should be valid", isValid);
    }

    @Test
    public void testInValidCardNumber_19digit(){

        boolean isValid = validator.isValid("6011008785309789331", null);;
        assertFalse("Card number should NOT be valid", isValid);
    }

    @Test
    public void tesInvalidNumberFormat(){
        boolean isValid = validator.isValid("601100878530e789331", null);;
        assertFalse("Card number should NOT be valid", isValid);
    }

}
