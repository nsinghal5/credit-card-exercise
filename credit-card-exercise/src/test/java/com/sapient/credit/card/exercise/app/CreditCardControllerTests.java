package com.sapient.credit.card.exercise.app;

import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.service.CreditCardServiceImpl;
import com.sapient.credit.card.exercise.utils.CommonUtils;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class CreditCardControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private CreditCardController controller;

    @MockBean
    private CreditCardServiceImpl mockCreditCardService;

    @Before
    public void setup(){
        controller = new CreditCardController(mockCreditCardService);
    }

    private static final String CREDIT_CARD_BASE_URL = "/api/v1/credit-card";

    @Test
    public void invalidCardNumber_minLength_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "12344", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREDIT_CARD_BASE_URL)
            .content(CommonUtils.asJsonString(invalidCard))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details[0]", containsString("Card number length should be between 12 to 19")));
    }

    @Test
    public void invalidCardNumber_maxLength_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "12345678910111213142", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREDIT_CARD_BASE_URL)
                .content(CommonUtils.asJsonString(invalidCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[0]", containsString("Card number length should be between 12 to 19")));
    }

    @Test
    public void invalidCardNumber_onlyDigit_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "123456e891011121e", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREDIT_CARD_BASE_URL)
                .content(CommonUtils.asJsonString(invalidCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details", hasSize(2)));
    }

    @Test
    public void invalidCardNumber_emptyName_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("", "1358954993914435", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREDIT_CARD_BASE_URL)
                .content(CommonUtils.asJsonString(invalidCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details[0]", containsString("Name must NOT be empty")));
    }

    @Test
    public void invalidCardNumber_luhn10_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "1358954993914434", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREDIT_CARD_BASE_URL)
                .content(CommonUtils.asJsonString(invalidCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details", hasSize(1)))
                .andExpect(jsonPath("$.details[0]", containsString("Card number must be Luhn 10 compliant")));
    }

    @Test
    public void validCard_Success_Test() throws Exception {
        CreditCard validCard = new CreditCard("Neeraj Singhal", "1358954993914435", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREDIT_CARD_BASE_URL)
                .content(CommonUtils.asJsonString(validCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void dbExceptionThrownTest_InternalServerError() throws Exception {
        CreditCard validCard = new CreditCard("Neeraj Singhal", "1358954993914435", 0.0);

        when(mockCreditCardService.createCreditCard(any()))
                .thenThrow(new JDBCConnectionException("Connection not established", new SQLException()));

        //when service throws db run time exception, then 500-Internal server error should be returned
        mockMvc.perform(MockMvcRequestBuilders.post(CREDIT_CARD_BASE_URL)
                .content(CommonUtils.asJsonString(validCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void getAll_Success_Test() throws Exception {
        List<CreditCard> cards = Arrays.asList(new CreditCard("Neeraj Singhal", "1358954993914435", 0.0));

        when(mockCreditCardService.getAllCreditCards()).thenReturn(cards);

        mockMvc.perform(MockMvcRequestBuilders.get(CREDIT_CARD_BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cardNumber", containsString("1358954993914435")));
    }

    @Test
    public void getAll_InternalServerError_Test() throws Exception {
        //Test total catch
        when(mockCreditCardService.getAllCreditCards()).thenThrow(new RuntimeException("Run time exception to test total catch"));

        mockMvc.perform(MockMvcRequestBuilders.get(CREDIT_CARD_BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
