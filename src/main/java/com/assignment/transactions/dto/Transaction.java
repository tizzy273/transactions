package com.assignment.transactions.dto;

import com.assignment.transactions.enums.TransactionType;
import com.assignment.transactions.validation.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Data Transfer Object representing a financial transaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    /**
     * Unique identifier for the transaction.
     */
    private Integer id;

    /**
     * Identifier for the associated account.
     */
    private Integer accountId;

    /**
     * Type of the transaction (e.g., credit, debit).
     * Must be a valid value from the {@link TransactionType} enum.
     */
    @Valid
    @ValueOfEnum(enumClass = TransactionType.class)
    private String type;

    /**
     * Timestamp of when the transaction occurred.
     */
    private LocalDateTime timeStamp;

    /**
     * Amount of the transaction.
     */
    private Float amount;

    /**
     * Constructor for creating a transaction with specified account ID, type, and amount.
     *
     * @param accountId the ID of the associated account
     * @param transactionType the type of the transaction
     * @param amount the amount of the transaction
     */
    public Transaction(int accountId, String transactionType, Float amount) {
        this.accountId = accountId;
        this.type = transactionType;
        this.amount = amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;

        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(amount, that.amount);
    }
}
