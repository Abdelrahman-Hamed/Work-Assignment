package com.vodafone.task.service;

import com.vodafone.task.document.CreditCard;
import com.vodafone.task.repository.CreditCardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepo creditCardRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public CreditCard addCreditCard(CreditCard creditCard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CreditCard> getCreditCards() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.sort(Sort.Direction.DESC, "principle").and(Sort.Direction.DESC, "createdAt"));
        AggregationResults<CreditCard> results = mongoTemplate.aggregate(aggregation, "credit", CreditCard.class);
        List<CreditCard> cardList = results.getMappedResults();
        return cardList;
    }


}
