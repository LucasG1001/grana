// Define all global types related to the application.
export interface Transaction {
  id: number;
  valor: number;
  categoria: string;
  descricao: string;
  data: string; // YYYY-MM-DD format from API
  tipo: 'RECEITA' | 'DESPESA';
}

export interface SummaryData {
  totalReceitas: number;
  totalDespesas: number;
  saldo: number;
  porCategoria: Record<string, number>;
}
