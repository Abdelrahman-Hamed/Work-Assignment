package com.vodafone.task.controller;

import com.vodafone.task.document.CreditCard;
import com.vodafone.task.service.CreditCardService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/credit")
@Setter
public class CreditCardController {

    final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping
    public List<CreditCard> getCreditCards() {
        return creditCardService.getCreditCards();
    }

}
