package com.assignment.transactions.dto;

import com.assignment.transactions.enums.TransactionType;
import com.assignment.transactions.validation.ValueOfEnum;
import jakarta.persistence.Column;
import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Integer id;

    private Integer accountId;

    @Valid
    @ValueOfEnum(enumClass = TransactionType.class)
    private String type;

    @Column(name = "TIMESTAMP")

    private LocalDateTime timeStamp;

    private Float amount;

    public Transaction(int accountId, String transactionType, Float amount) {
        this.accountId = accountId;
        this.type = transactionType;
        this.amount = amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;

        return Objects.equals(accountId, that.accountId) && Objects.equals(type, that.type) && Objects.equals(amount, that.amount);
    }
}
