package com.assignment.transactions.validation;

import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.entity.TransactionEntity;
import com.assignment.transactions.enums.TransactionType;
import com.assignment.transactions.exception.BadRequestException;
import com.assignment.transactions.repository.TransactionRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionValidator implements ConstraintValidator<TransactionValid, Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintValidatorContext) {
        if(transaction.getType().equals(TransactionType.WITHDRAWAL.name())){
            List<TransactionEntity> transactions = transactionRepository.getTransactionByAccountId(transaction.getAccountId());

            float amount = transaction.getAmount();
            float accountBalance = accountBalance(transactions);

            if(amount > accountBalance) {
                throw new BadRequestException("This account does not have enough funds to process a withdrawal of " + amount);
            }
        }

        return true;
    }

    private static Float accountBalance(List<TransactionEntity> transactions) {
        return transactions.stream()
                .map(TransactionEntity::getAmount)
                .reduce(0f, Float::sum);
    }
}
