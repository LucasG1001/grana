package com.finance.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private Long id;
    private BigDecimal valor;
    private String categoria;
    private String descricao;
    private LocalDate data;
    private TransactionType tipo;
    private PaymentMethod formaPagamento;
    private Integer parcelaAtual;
    private Integer totalParcelas;
    private String grupoParcelamento;

    public Transaction(Long id, BigDecimal valor, String categoria, String descricao, LocalDate data, TransactionType tipo) {
        this(id, valor, categoria, descricao, data, tipo, PaymentMethod.OUTRO, null, 1, null);
    }

    public Transaction(Long id, BigDecimal valor, String categoria, String descricao, LocalDate data, TransactionType tipo,
                       PaymentMethod formaPagamento, Integer parcelaAtual, Integer totalParcelas, String grupoParcelamento) {
        this.id = id;
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.data = data;
        this.tipo = tipo;
        this.formaPagamento = formaPagamento != null ? formaPagamento : PaymentMethod.OUTRO;
        this.parcelaAtual = parcelaAtual;
        this.totalParcelas = totalParcelas != null && totalParcelas > 0 ? totalParcelas : 1;
        this.grupoParcelamento = grupoParcelamento;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public TransactionType getTipo() { return tipo; }
    public void setTipo(TransactionType tipo) { this.tipo = tipo; }

    public PaymentMethod getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(PaymentMethod formaPagamento) {
        this.formaPagamento = formaPagamento != null ? formaPagamento : PaymentMethod.OUTRO;
    }

    public Integer getParcelaAtual() { return parcelaAtual; }
    public void setParcelaAtual(Integer parcelaAtual) { this.parcelaAtual = parcelaAtual; }

    public Integer getTotalParcelas() { return totalParcelas; }
    public void setTotalParcelas(Integer totalParcelas) {
        this.totalParcelas = totalParcelas != null && totalParcelas > 0 ? totalParcelas : 1;
    }

    public String getGrupoParcelamento() { return grupoParcelamento; }
    public void setGrupoParcelamento(String grupoParcelamento) { this.grupoParcelamento = grupoParcelamento; }
}
