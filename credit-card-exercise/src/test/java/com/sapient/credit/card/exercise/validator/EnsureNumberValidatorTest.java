package com.sapient.credit.card.exercise.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class EnsureNumberValidatorTest {
    private EnsureNumberValidator validator;

    @Before
    public void setup(){
        validator = new EnsureNumberValidator();
    }

    @Test
    public void nullValueShouldReturnFalseTest(){
        assertFalse("Should be Invalid Card Number", validator.isValid(null, null));
    }

    @Test
    public void invalidNumberTest_WithAlphabets(){
        assertFalse("Should be invalid Card number", validator.isValid("1234567a123456", null));
    }

    @Test
    public void invalidNumberTest_WithSpecialChars(){
        assertFalse("Should be invalid Card number", validator.isValid("123456_123456", null));
    }

    @Test
    public void invalidDecimalNumberTest(){
        assertFalse("Should be invalid card number", validator.isValid("12345.12", null));
    }

    @Test
    public void validNumberTest(){
        assertTrue("Should be valid Card number", validator.isValid("6543726453627123", null));
    }
}
