package com.assignment.transactions.service;

import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.mapper.TransactionMapper;
import com.assignment.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;


    public List<Transaction> addTransaction(Transaction transaction) {

        transactionRepository.save(transactionMapper.toEntity(transaction));
        return transactionMapper.mapList(transactionRepository.getTransactionByAccountIdOrderByTimeStampDesc(transaction.getAccountId()));
    }


    public List<Transaction> getTransactionHistory(Integer accountId){
        return transactionMapper.mapList(transactionRepository.getTransactionByAccountIdOrderByTimeStampDesc(accountId));
    }



}
