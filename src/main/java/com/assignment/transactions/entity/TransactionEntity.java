package com.assignment.transactions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing a financial transaction in the database.
 */
@Entity
@Table(name = "TRANSACTION", schema = "TRANSACTIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    /**
     * Unique identifier for the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Type of the transaction (e.g., credit, debit).
     */
    private String type;

    /**
     * Identifier for the associated account.
     */
    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    /**
     * Timestamp of when the transaction occurred.
     */
    @Column(name = "TIMESTAMP")
    private LocalDateTime timeStamp;

    /**
     * Amount of the transaction.
     */
    private Float amount;

    /**
     * Constructor for creating a transaction with specified type, account ID, and amount.
     *
     * @param type the type of the transaction
     * @param accountId the ID of the associated account
     * @param amount the amount of the transaction
     */
    public TransactionEntity(String type, Integer accountId, Float amount) {
        this.type = type;
        this.accountId = accountId;
        this.amount = amount;
    }

    /**
     * Automatically sets the timestamp to the current date and time before persisting.
     */
    @PrePersist
    private void prePersist() {
        this.setTimeStamp(LocalDateTime.now());
    }
}
