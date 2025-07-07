package com.lucas.grana.domain.transaction;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.lucas.grana.domain.user.User;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    public enum Type {
        DEPOSIT,
        WITHDRAWAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Type type;

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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Transaction(Type type, String description, BigDecimal value, 
                      LocalDateTime date,User user) {
        this.type = type;
        this.description = description;
        this.value = value;
        this.date = date;
        this.user = user;
    }
}