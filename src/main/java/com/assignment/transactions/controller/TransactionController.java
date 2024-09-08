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
@Validated
@RestController
@RequestMapping("${application.basepath}/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/history")
    public ResponseEntity<List<Transaction>> getTransactionsHistorFy(@RequestParam(name = "account-id") Integer accountId){
        return new ResponseEntity<>(transactionService.getTransactionHistory(accountId), HttpStatus.OK);
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<List<Transaction>> addTransactionToAccount(@Valid @RequestBody @TransactionValid Transaction transaction){

        return new ResponseEntity<>(transactionService.addTransactionToAccount(transaction), HttpStatus.OK);
    }

}
