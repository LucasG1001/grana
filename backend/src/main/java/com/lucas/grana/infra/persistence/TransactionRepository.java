package com.lucas.grana.infra.persistence;

import org.springframework.stereotype.Repository;

import com.lucas.grana.domain.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TransactionRepository extends  JpaRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t JOIN FETCH t.category WHERE t.user.id = :userId")
    List<Transaction> findByUserId(@Param("userId") String userId);
    @Query("SELECT t FROM Transaction t JOIN FETCH t.category WHERE t.user.id = :userId AND t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findByDateBetween(@Param("userId") String userId,@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}