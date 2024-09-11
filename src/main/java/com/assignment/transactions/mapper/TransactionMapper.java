package com.assignment.transactions.mapper;

import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Transaction DTO and TransactionEntity.
 */
@Component
public class TransactionMapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a TransactionEntity to a Transaction DTO.
     *
     * @param transactionEntity the entity to convert
     * @return the corresponding DTO
     */
    public Transaction toDto(TransactionEntity transactionEntity) {
        return modelMapper.map(transactionEntity, Transaction.class);
    }

    /**
     * Converts a Transaction DTO to a TransactionEntity.
     *
     * @param transaction the DTO to convert
     * @return the corresponding entity
     */
    public TransactionEntity toEntity(Transaction transaction) {
        return modelMapper.map(transaction, TransactionEntity.class);
    }

    /**
     * Converts a list of TransactionEntity to a list of Transaction DTOs.
     *
     * @param transactions the list of entities to convert
     * @return the list of corresponding DTOs
     */
    public List<Transaction> mapList(List<TransactionEntity> transactions) {
        return transactions.stream().map(this::toDto).collect(Collectors.toList());
    }
}
