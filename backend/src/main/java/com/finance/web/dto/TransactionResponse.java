package com.finance.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(Long id, BigDecimal valor, String categoria, String descricao, LocalDate data, String tipo) {}
