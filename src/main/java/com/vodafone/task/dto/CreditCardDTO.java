package com.vodafone.task.dto;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import javax.validation.constraints.Pattern;

@Data
@ToString
@AllArgsConstructor
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
}
