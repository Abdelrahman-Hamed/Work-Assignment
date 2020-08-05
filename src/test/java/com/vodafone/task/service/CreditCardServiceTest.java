package com.vodafone.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.task.document.CreditCard;
import com.vodafone.task.repository.CreditCardRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardServiceTest {

    @Autowired
    private CreditCardServiceImpl creditCardService;

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        creditCardRepo.deleteAll();
        saveCards();
    }

    @Test
    public void testHappyScenario() {
        List<CreditCard> cards = creditCardService.getCreditCards();
        Assert.assertEquals(2, cards.size());
    }

    @Test
    public void testSortByPrinciple() {
        CreditCard card=creditCardService.getCreditCards().get(0);
        Assert.assertTrue(card.isPrinciple());
        Assert.assertEquals("1234",card.getCardExpiry());
    }

    @Test
    public void testAddCard() {
        CreditCard card = new CreditCard();
        card.setPrinciple(false);
        card.setCardExpiry("5698");
        card.setCardMask("1234123412341234");
        card.setToken("12341234123412341234");
        card.setPersisted(false);
        creditCardService.addCreditCard(card);
        Assert.assertEquals(3, creditCardRepo.findAll().size());
        Optional<CreditCard> returnedCard = creditCardRepo.findAll().stream().filter(c -> c.getCardExpiry().equals("5698")).findFirst();
        Assert.assertTrue(returnedCard.isPresent());
        Assert.assertEquals(card, returnedCard.get());
    }

    @Test(expected = DuplicateKeyException.class)
    public void testDuplicatedCardMask() {
        CreditCard card = creditCardRepo.findAll().get(0);
        creditCardService.addCreditCard(card);
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
