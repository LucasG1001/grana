package com.finance.web.dto;

import java.math.BigDecimal;
import java.util.Map;

public record SummaryResponse(BigDecimal totalReceitas, BigDecimal totalDespesas, BigDecimal saldo, Map<String, BigDecimal> porCategoria) {}
