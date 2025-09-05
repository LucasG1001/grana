package com.lucas.grana.infra.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TransactionType type;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Boolean installment = false;

    private Integer currentInstallment;

    private Integer totalInstallments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;
}