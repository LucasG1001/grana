// Define all global types related to the application.
export interface Transaction {
  id: number;
  valor: number;
  categoria: string;
  descricao: string;
  data: string; // YYYY-MM-DD format from API
  tipo: 'RECEITA' | 'DESPESA';
  formaPagamento: 'DINHEIRO' | 'DEBITO' | 'CREDITO' | 'PIX' | 'TRANSFERENCIA' | 'OUTRO';
  parcelaAtual: number | null;
  totalParcelas: number;
  grupoParcelamento: string | null;
}

export interface SummaryData {
  totalReceitas: number;
  totalDespesas: number;
  saldo: number;
  porCategoria: Record<string, number>;
}
