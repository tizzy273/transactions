package com.assignment.transactions.repository;

import com.assignment.transactions.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for accessing transaction data.
 */
@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

    /**
     * Retrieves a list of transactions by account ID, ordered by timestamp in descending order.
     *
     * @param accountId the account ID to search for
     * @return a list of transactions
     */
    @Query
    public List<TransactionEntity> getTransactionByAccountIdOrderByTimeStampDesc(Integer accountId);

}
