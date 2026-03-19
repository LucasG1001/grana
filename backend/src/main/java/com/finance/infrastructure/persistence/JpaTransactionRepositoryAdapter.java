package com.finance.infrastructure.persistence;

import com.finance.domain.model.Transaction;
import com.finance.domain.repository.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaTransactionRepositoryAdapter implements TransactionRepository {

    private final SpringDataTransactionRepository springDataRepository;

    public JpaTransactionRepositoryAdapter(SpringDataTransactionRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = toEntity(transaction);
        TransactionEntity saved = springDataRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return springDataRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Transaction> findAll() {
        return springDataRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByMonthAndYear(int month, int year) {
        return springDataRepository.findByMonthAndYear(month, year).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }

    private TransactionEntity toEntity(Transaction domain) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(domain.getId());
        entity.setValor(domain.getValor());
        entity.setCategoria(domain.getCategoria());
        entity.setDescricao(domain.getDescricao());
        entity.setData(domain.getData());
        entity.setTipo(domain.getTipo());
        return entity;
    }

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
            entity.getId(),
            entity.getValor(),
            entity.getCategoria(),
            entity.getDescricao(),
            entity.getData(),
            entity.getTipo()
        );
    }
}
