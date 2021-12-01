package com.sapient.credit.card.exercise.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.service.CreditCardService;
import com.sapient.credit.card.exercise.service.CreditCardServiceImpl;
import com.sapient.credit.card.exercise.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.regex.Matcher;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardServiceImpl creditCardService;

    private static final String CREATE_URI = "/api/v1/credit-card";

    @Test
    public void invalidCardNumber_minLength_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "12345", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
            .content(CommonUtils.asJsonString(invalidCard))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details", containsString("Card number length should be between 12 to 19")));
    }

    @Test
    public void invalidCardNumber_maxLength_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "12345678910111213141", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
                .content(CommonUtils.asJsonString(invalidCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details", containsString("Card number length should be between 12 to 19")));
    }

    @Test
    public void invalidCardNumber_onlyDigit_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "1234567891011121e", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
                .content(CommonUtils.asJsonString(invalidCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details", containsString("Card number must be numeric only")));
    }

    @Test
    public void invalidCardNumber_emptyName_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("", "1234567891011121", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
                .content(CommonUtils.asJsonString(invalidCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Validation Failed")))
                .andExpect(jsonPath("$.details", containsString("Name must NOT be empty")));
    }
}
