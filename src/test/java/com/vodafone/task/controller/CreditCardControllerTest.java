package com.vodafone.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.task.document.CreditCard;
import com.vodafone.task.dto.CreditCardDTO;
import com.vodafone.task.repository.CreditCardRepo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CreditCardControllerTest extends AbstractControllerTest {

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        creditCardRepo.deleteAll();
        saveCards();
    }

    @Test
    public void getCardsList() throws Exception {
        String uri = "/api/v1/credit";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CreditCardDTO[] productlist = super.mapFromJson(content, CreditCardDTO[].class);
        assertTrue(productlist.length == 2);
    }

    @Test
    public void createCard() throws Exception {
        String uri = "/api/v1/credit";
        CreditCardDTO cardDTO = new CreditCardDTO("1234123412341234", "9856", true, "12341234123412341234");
        String inputJson = super.mapToJson(cardDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        CreditCardDTO returnedCard = super.mapFromJson(content, CreditCardDTO.class);
        assertEquals(cardDTO, returnedCard);
        assertEquals(3, creditCardRepo.findAll().size());
    }

    @Test
    public void testBadRequestInvalidMask() throws Exception {
        String uri = "/api/v1/credit";
        CreditCardDTO cardDTO = new CreditCardDTO("75678912456c6811111", "9856", true, "12341234123412341234");
        String inputJson = super.mapToJson(cardDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

    private void saveCards() {
        String creditCards = "[" +
                "{\"cardMask\": \"7567891245636811111\",\n" +
                "        \"cardExpiry\": \"4321\",\n" +
                "        \"principle\": false,\n" +
                "        \"token\": \"756789124563698111111\"}" +
                ",{\"cardMask\": \"756789124563682222\",\n" +
                "        \"cardExpiry\": \"1234\",\n" +
                "        \"principle\": true,\n" +
                "        \"token\": \"756789124563698111111\"}" +
                "]";
        try {
            CreditCard cards[] = new ObjectMapper().readValue(creditCards, CreditCard[].class);
            for (CreditCard card : cards) {
                creditCardRepo.save(card);
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
