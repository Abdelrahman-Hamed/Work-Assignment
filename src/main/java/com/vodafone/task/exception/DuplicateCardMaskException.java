package com.vodafone.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateCardMaskException extends RuntimeException {
    public DuplicateCardMaskException(String message) {
        super(message);
    }
}
