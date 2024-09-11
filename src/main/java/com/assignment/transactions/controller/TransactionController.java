package com.assignment.transactions.controller;

import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.service.TransactionService;
import com.assignment.transactions.validation.TransactionValid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling transaction-related requests.
 * Provides endpoints to retrieve transaction history and create new transactions.
 */
@Validated
@RestController
@RequestMapping("${application.basepath}/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Retrieves the transaction history for a specified account.
     *
     * @param accountId the ID of the account for which to retrieve the transaction history
     * @return a list of transactions for the specified account
     */
    @GetMapping("/history")
    public ResponseEntity<List<Transaction>> getTransactionsHistory(@RequestParam(name = "account-id") Integer accountId) {
        return new ResponseEntity<>(transactionService.getTransactionHistory(accountId), HttpStatus.OK);
    }

    /**
     * Adds a new transaction.
     *
     * @param transaction the transaction details to be added
     * @return the list of transactions including the newly added one
     */
    @PostMapping("/create-transaction")
    public ResponseEntity<List<Transaction>> addTransaction(@Valid @RequestBody @TransactionValid Transaction transaction) {
        return new ResponseEntity<>(transactionService.addTransaction(transaction), HttpStatus.OK);
    }
}
