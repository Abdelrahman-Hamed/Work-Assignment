package com.vodafone.task.service;

import com.vodafone.task.document.CreditCard;
import com.vodafone.task.repository.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Override
    public CreditCard addCreditCard(CreditCard creditCard) {
        return creditCardRepo.save(creditCard);
    }

    @Override
    public List<CreditCard> getCreditCards() {
        Sort sort=Sort.by(Sort.Direction.DESC,"principle","createdAt");
        return creditCardRepo.findAll(sort);
    }


}
