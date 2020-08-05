package com.vodafone.task.document;

import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Document(collection = "credit")
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreditCard implements Persistable<ObjectId> {

    @Id
    private ObjectId id;

    @NotBlank
    @NonNull
    @Pattern(regexp = "[\\d]{14,20}")
    @Indexed(unique = true)
    private String cardMask;

    @NonNull
    @Pattern(regexp = "[\\d]{4}")
    private String cardExpiry;

    private boolean principle;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date savedAt;

    @Pattern(regexp = "[\\d]{20,25}")
    private String token;

    private boolean persisted;

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return !persisted;
    }
}
