package com.vodafone.task.dto;

import com.mongodb.lang.NonNull;
import com.vodafone.task.document.CreditCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Pattern;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {

    @NonNull
    @Pattern(regexp = "[\\d]{14,20}")
    private String cardMask;

    @NonNull
    @Pattern(regexp = "[\\d]{4}")
    private String cardExpiry;

    private boolean principle;

    @Pattern(regexp = "[\\d]{20,25}")
    private String token;

    public static CreditCardDTO valueOf(CreditCard creditCard) {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        BeanUtils.copyProperties(creditCard, creditCardDTO);
        return creditCardDTO;
    }
}
