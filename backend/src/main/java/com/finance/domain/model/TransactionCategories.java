package com.finance.domain.model;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class TransactionCategories {

    public static final List<String> ALL = List.of(
        "Alimentação",
        "Mercado",
        "Transporte",
        "Moradia",
        "Saúde",
        "Educação",
        "Lazer",
        "Compras",
        "Serviços",
        "Contas",
        "Impostos",
        "Salário",
        "Rendimentos",
        "Investimentos",
        "Outros"
    );

    private static final Map<String, String> ALIASES = Map.ofEntries(
        Map.entry("alimentacao", "Alimentação"),
        Map.entry("comida", "Alimentação"),
        Map.entry("restaurante", "Alimentação"),
        Map.entry("lanche", "Alimentação"),
        Map.entry("delivery", "Alimentação"),
        Map.entry("mercado", "Mercado"),
        Map.entry("supermercado", "Mercado"),
        Map.entry("feira", "Mercado"),
        Map.entry("transporte", "Transporte"),
        Map.entry("uber", "Transporte"),
        Map.entry("taxi", "Transporte"),
        Map.entry("combustivel", "Transporte"),
        Map.entry("moradia", "Moradia"),
        Map.entry("aluguel", "Moradia"),
        Map.entry("condominio", "Moradia"),
        Map.entry("saude", "Saúde"),
        Map.entry("farmacia", "Saúde"),
        Map.entry("medico", "Saúde"),
        Map.entry("educacao", "Educação"),
        Map.entry("curso", "Educação"),
        Map.entry("lazer", "Lazer"),
        Map.entry("cinema", "Lazer"),
        Map.entry("compras", "Compras"),
        Map.entry("shopping", "Compras"),
        Map.entry("servicos", "Serviços"),
        Map.entry("assinatura", "Serviços"),
        Map.entry("contas", "Contas"),
        Map.entry("luz", "Contas"),
        Map.entry("agua", "Contas"),
        Map.entry("internet", "Contas"),
        Map.entry("imposto", "Impostos"),
        Map.entry("salario", "Salário"),
        Map.entry("rendimento", "Rendimentos"),
        Map.entry("investimento", "Investimentos")
    );

    private TransactionCategories() {
    }

    public static String normalize(String candidate) {
        if (candidate == null || candidate.isBlank()) {
            return "Outros";
        }

        String key = simplify(candidate);
        if (ALIASES.containsKey(key)) {
            return ALIASES.get(key);
        }

        return ALL.stream()
            .filter(category -> simplify(category).equals(key))
            .findFirst()
            .orElse("Outros");
    }

    public static String promptOptions() {
        return String.join(", ", ALL);
    }

    private static String simplify(String value) {
        String withoutAccents = Normalizer.normalize(value, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");

        return withoutAccents
            .toLowerCase(Locale.ROOT)
            .replaceAll("[^a-z0-9]+", " ")
            .trim()
            .replace(" ", "");
    }
}
