package com.assignment.transactions.repository;

import com.assignment.transactions.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

    @Query
    public List<TransactionEntity> getTransactionByAccountIdOrderByTimeStampDesc(Integer accountId);

}
