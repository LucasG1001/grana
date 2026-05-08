export const FIXED_CATEGORIES = [
  'Alimentação',
  'Mercado',
  'Transporte',
  'Moradia',
  'Saúde',
  'Educação',
  'Lazer',
  'Compras',
  'Serviços',
  'Contas',
  'Impostos',
  'Salário',
  'Rendimentos',
  'Investimentos',
  'Outros',
] as const;

export type FixedCategory = (typeof FIXED_CATEGORIES)[number];

export const CATEGORY_COLORS: Record<FixedCategory, string> = {
  Alimentação: '#fb7185',
  Mercado: '#f59e0b',
  Transporte: '#38bdf8',
  Moradia: '#a78bfa',
  Saúde: '#34d399',
  Educação: '#60a5fa',
  Lazer: '#f472b6',
  Compras: '#c084fc',
  Serviços: '#2dd4bf',
  Contas: '#fbbf24',
  Impostos: '#f87171',
  Salário: '#4ade80',
  Rendimentos: '#22c55e',
  Investimentos: '#818cf8',
  Outros: '#94a3b8',
};

export function isFixedCategory(category: string): category is FixedCategory {
  return FIXED_CATEGORIES.includes(category as FixedCategory);
}
