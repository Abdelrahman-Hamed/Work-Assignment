package com.vodafone.task.config;

import com.vodafone.task.document.CreditCard;
import com.vodafone.task.repository.CreditCardRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@EnableMongoRepositories(basePackages = "com.vodafone.task.repository")
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(CreditCardRepo creditCardRepo) {
        return strings -> {
            creditCardRepo.deleteAll();
            CreditCard card = new CreditCard();
            card.setCardMask("25678912456369898765");
            //card.setId(UUID.randomUUID());
            card.setPrinciple(true);
            creditCardRepo.save(card);
            card = new CreditCard();
            card.setCardMask("556789144563698");
            //card.setId(UUID.randomUUID());
            card.setPrinciple(false);
            creditCardRepo.save(card);
            card = new CreditCard();
            card.setCardMask("156789124563698");
            //card.setId(UUID.randomUUID());
            card.setPrinciple(true);
            creditCardRepo.save(card);
        };
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
