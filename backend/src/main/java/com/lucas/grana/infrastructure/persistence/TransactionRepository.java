package com.lucas.grana.infra.persistence;

import org.springframework.stereotype.Repository;

import com.lucas.grana.domain.entities.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t JOIN FETCH t.category WHERE t.user.id = :userId ORDER BY t.date DESC")
    List<Transaction> findByUserId(@Param("userId") String userId);

    @Query("SELECT t FROM Transaction t JOIN FETCH t.category WHERE t.user.id = :userId AND t.date BETWEEN :startDate AND :endDate ORDER BY t.date DESC")
    List<Transaction> findByDateBetween(@Param("userId") String userId, @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    Optional<Transaction> findByUserIdAndNameIgnoreCase(String userId, String name);
}