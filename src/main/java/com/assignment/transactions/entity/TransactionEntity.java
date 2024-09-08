package com.assignment.transactions.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION", schema = "TRANSACTIONS")
@Data
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timeStamp;

    private Float amount;

    @PrePersist
    private void prePersist() {
        this.setTimeStamp(LocalDateTime.now());
    }
}
