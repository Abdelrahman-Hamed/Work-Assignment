package com.vodafone.task.controller;

import com.vodafone.task.document.CreditCard;
import com.vodafone.task.dto.CreditCardDTO;
import com.vodafone.task.service.CreditCardService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/credit")
@Setter
public class CreditCardController {

    final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping
    public List<CreditCardDTO> getCreditCards() {
        return creditCardService.getCreditCards()
                .stream()
                .map(CreditCardDTO::valueOf)
                .collect(Collectors.toList())
                ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardDTO addCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) throws Exception {
        logger.info("--> Adding new Card ", creditCardDTO);
        CreditCard creditCard = new CreditCard();
        BeanUtils.copyProperties(creditCardDTO, creditCard);
        creditCardService.addCreditCard(creditCard);
        logger.info("--> Card Added Successfully ", creditCardDTO);
        return creditCardDTO;
    }
}
