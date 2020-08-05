package com.vodafone.task.service;

import com.vodafone.task.document.CreditCard;

import java.util.List;

public interface CreditCardService {

    CreditCard addCreditCard(CreditCard creditCard);

    List<CreditCard> getCreditCards();
}
