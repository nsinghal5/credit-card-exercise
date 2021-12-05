package com.sapient.credit.card.exercise.validator;

import com.sapient.credit.card.exercise.app.CreditCardController;
import com.sapient.credit.card.exercise.model.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * Luhn Card Validator - Luhn 10 implementation
 */
public class EnsureLuhnValidator implements ConstraintValidator<EnsureLuhn, Object> {

    Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    private EnsureLuhn ensureLuhn;

    @Override
    public void initialize(EnsureLuhn constraintAnnotation) {
        this.ensureLuhn = constraintAnnotation;
    }

    @Override
    /**
     * Validate the cards numbers with Luhn 10 algorithm. Steps:
     * 1. Convert the Card number to Int Array
     * 2. Iterate Card array from rightmost digit with step i = i - 2
     * 3. Multiple each picked digit in step 2 by 2
     * 4. If number is greater than 9, do a sum of the two digits
     * 5. Put the number at same array index.
     * 6. Sum all the digits in the array now.
     * 7. Return true if sum % == 0, false otherwise
     */
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String cardNumber = String.valueOf(value);

        int[] cardIntArr = new int[cardNumber.length()];

        //1. parse each character to integer and store in array
        for(int i = 0; i < cardNumber.length(); i++){
            try{
                cardIntArr[i] = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            }catch (NumberFormatException ex){
                logger.error("NumberFormatException while parsing card number {}", ex.getMessage());
                return false;
            }
        }

        //2. iterate from rightmost element and with step i = i-2
        for(int i = cardIntArr.length -2; i >= 0; i=i-2){
            int num = cardIntArr[i];
            num = num*2;                //3. multiply by 2

            if(num > 9){
                num = num%10 + num/10;  //4. add digits if greater than 9
            }
            cardIntArr[i] = num;
        }

        // 5. sum al the digits in array
        int sum = Arrays.stream(cardIntArr).sum();

        return sum % 10 == 0;
    }
}
