package com.finance.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE EXTRACT(MONTH FROM t.data) = :month AND EXTRACT(YEAR FROM t.data) = :year ORDER BY t.data DESC")
    List<TransactionEntity> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}
