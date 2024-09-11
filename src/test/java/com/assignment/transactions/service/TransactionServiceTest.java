package com.assignment.transactions.service;

import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.entity.TransactionEntity;
import com.assignment.transactions.enums.TransactionType;
import com.assignment.transactions.mapper.TransactionMapper;
import com.assignment.transactions.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {


    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;


    @Test
    public void addTransactionTest(){
        TransactionEntity transactionEntity = new TransactionEntity(1, TransactionType.DEPOSIT.name(), 1, LocalDateTime.of(2024, Month.JANUARY, 1, 0,0), 5F);

        Transaction transaction = new Transaction(1, 1 , TransactionType.DEPOSIT.name(),  LocalDateTime.of(2024, Month.JANUARY, 1, 0,0), 5f);

        List<Transaction> transactions = List.of(transaction);

        when(transactionRepository.save(any())).thenReturn(transactionEntity);
        when(transactionMapper.toEntity(transaction)).thenReturn(transactionEntity);
        when(transactionMapper.mapList(any())).thenReturn(transactions);

        assertEquals(transactions, transactionService.addTransaction(transaction));

    }


    @Test
    public void getTransactionsHistoryTest()
    {
        TransactionEntity transactionEntity = new TransactionEntity(1, TransactionType.DEPOSIT.name(), 1, LocalDateTime.of(2024, Month.JANUARY, 1, 0,0), 5F);

        Transaction transaction = new Transaction(1, 1 , TransactionType.DEPOSIT.name(),  LocalDateTime.of(2024, Month.JANUARY, 1, 0,0), 5f);

        List<Transaction> transactionsDto = List.of(transaction);

        List<TransactionEntity> transactionsEntity = List.of(transactionEntity);

        when(transactionRepository.getTransactionByAccountIdOrderByTimeStampDesc(transaction.getAccountId())).thenReturn(transactionsEntity);
        when(transactionMapper.mapList(any())).thenReturn(transactionsDto);

        assertEquals(transactionsDto, transactionService.getTransactionHistory(1));



    }
}
