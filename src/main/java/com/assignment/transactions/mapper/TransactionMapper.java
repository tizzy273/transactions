package com.assignment.transactions.mapper;

import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Transaction toDto(TransactionEntity transactionEntity){
        return modelMapper.map(transactionEntity, Transaction.class);
    }

    public TransactionEntity toEntity(Transaction transaction){
        return modelMapper.map(transaction, TransactionEntity.class);
    }

    public List<Transaction> mapList(List<TransactionEntity> transactions){
        return transactions.stream().map(this::toDto).collect(Collectors.toList());
    }
}
