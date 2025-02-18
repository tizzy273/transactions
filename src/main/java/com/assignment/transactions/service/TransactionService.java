package com.assignment.transactions.service;

import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.mapper.TransactionMapper;
import com.assignment.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing transactions.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    /**
     * Adds a new transaction and retrieves the updated transaction history for the account.
     *
     * @param transaction the transaction to add
     * @return the updated transaction history
     */
    public List<Transaction> addTransaction(Transaction transaction) {
        transactionRepository.save(transactionMapper.toEntity(transaction));
        return transactionMapper.mapList(transactionRepository.getTransactionByAccountIdOrderByTimeStampDesc(transaction.getAccountId()));
    }

    /**
     * Retrieves the transaction history for a specific account.
     *
     * @param accountId the account ID to search for
     * @return a list of transactions for the account
     */
    public List<Transaction> getTransactionHistory(Integer accountId){
        return transactionMapper.mapList(transactionRepository.getTransactionByAccountIdOrderByTimeStampDesc(accountId));
    }
}
