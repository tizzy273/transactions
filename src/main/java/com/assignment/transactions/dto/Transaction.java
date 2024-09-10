package com.assignment.transactions.dto;

import com.assignment.transactions.enums.TransactionType;
import com.assignment.transactions.validation.ValueOfEnum;
import jakarta.persistence.Column;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
public class Transaction {

    private Integer id;

    private Integer accountId;

    @Valid
    @ValueOfEnum(enumClass = TransactionType.class)
    private String type;

    @Column(name = "TIMESTAMP")

    private LocalDateTime timeStamp;

    private Float amount;

}
