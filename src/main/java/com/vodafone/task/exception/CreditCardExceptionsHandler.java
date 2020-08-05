package com.vodafone.task.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CreditCardExceptionsHandler {
    final Logger logger = LoggerFactory.getLogger(CreditCardExceptionsHandler.class);

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Card")
    @ExceptionHandler(DuplicateKeyException.class)
    public void duplicateCardMaskHandler() {
        logger.error("Duplicated Card Mask");
    }
}
