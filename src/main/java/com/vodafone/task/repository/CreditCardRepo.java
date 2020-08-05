package com.vodafone.task.repository;

import com.vodafone.task.document.CreditCard;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepo extends MongoRepository<CreditCard, ObjectId> {

}
