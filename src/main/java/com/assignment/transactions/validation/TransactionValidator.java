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

import static com.assignment.transactions.enums.TransactionType.DEPOSIT;

/**
 * Validator for transaction constraints.
 */
public class TransactionValidator implements ConstraintValidator<TransactionValid, Transaction> {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Validates a transaction.
     *
     * @param transaction the transaction to validate
     * @param context     the context in which the validation is performed
     * @return true if the transaction is valid, false otherwise
     */
    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext context) {
        amountChecks(transaction);

        if (transaction.getAccountId() == null) {
            throw new BadRequestException("You have to define the account ID of your transaction");
        }

        if (transaction.getType() == null) {
            throw new BadRequestException("You have to define the type of your transaction");
        }

        if (transaction.getType().equals(TransactionType.WITHDRAWAL.name())) {
            List<TransactionEntity> transactions = transactionRepository.getTransactionByAccountIdOrderByTimeStampDesc(transaction.getAccountId());

            float amount = transaction.getAmount();
            float accountBalance = sumTransactionsAmount(transactions);

            if (amount > accountBalance) {
                throw new BadRequestException("This account does not have enough funds to process a withdrawal of " + amount + " €");
            }
        }

        return true;
    }

    /**
     * Performs checks on the transaction amount.
     *
     * @param transaction the transaction to check
     */
    private void amountChecks(Transaction transaction) {
        if (transaction.getAmount() == null) {
            throw new BadRequestException("You have to define an amount for your transaction");
        }
        if (transaction.getAmount() == 0) {
            throw new BadRequestException("Your transaction cannot have a value of 0");
        }
        if (transaction.getAmount() < 0) {
            throw new BadRequestException("Your transaction cannot have a negative value");
        }
    }

    /**
     * Calculates the sum of amounts from transactions, adjusting for deposits and withdrawals.
     *
     * @param transactions the list of transactions
     * @return the sum of transaction amounts
     */
    private Float sumTransactionsAmount(List<TransactionEntity> transactions) {
        return transactions.stream()
                .map(transaction ->
                        transaction.getType().equals(DEPOSIT.name()) ? transaction.getAmount() : -transaction.getAmount()
                )
                .reduce(0f, Float::sum);
    }
}
