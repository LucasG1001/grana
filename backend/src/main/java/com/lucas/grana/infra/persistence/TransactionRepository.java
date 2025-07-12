package com.lucas.grana.infra.persistence;

import org.springframework.stereotype.Repository;

import com.lucas.grana.domain.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TransactionRepository extends  JpaRepository<Transaction, String> {
    List<Transaction> findByUserId(String userId);
}
