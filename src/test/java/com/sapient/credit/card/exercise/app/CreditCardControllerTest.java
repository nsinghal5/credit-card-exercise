package com.sapient.credit.card.exercise.app;

import com.sapient.credit.card.exercise.model.CreditCard;
import com.sapient.credit.card.exercise.service.CreditCardServiceImpl;
import com.sapient.credit.card.exercise.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardServiceImpl mockCreditCardService;

    private static final String CREATE_URI = "/api/v1/credit-card";

    @Test
    public void invalidCardNumber_minLength_Test() throws Exception {
        CreditCard invalidCard = new CreditCard("Neeraj Singhal", "12344", 0.0);
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
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
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
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
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
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
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
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
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
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
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_URI)
                .content(CommonUtils.asJsonString(validCard))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}
