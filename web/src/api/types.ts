export interface LoginState {
  ok: boolean;
  error: string;
  data: any | null;
}

export const initialState: LoginState = {
  ok: false,
  error: "",
  data: null,
};

export type Transaction = {
  id: number;
  amount: number;
  date: string;
  description: string;
  categoryId: number;
  categoryName: string;
};

export type TransactionTableColumn = {
  id: keyof Transaction;
  label: string;
  sortDirection: "asc" | "desc";
};

export type CategoryByMonth = {
  id: number;
  name: string;
  totalAmount: number;
  month: number;
  color: string;
};

export const transactionsTableColumns: TransactionTableColumn[] = [
  { id: "id", label: "ID", sortDirection: "asc" },
  { id: "amount", label: "Amount", sortDirection: "asc" },
  { id: "date", label: "Date", sortDirection: "asc" },
  { id: "description", label: "Description", sortDirection: "asc" },
  { id: "categoryId", label: "Category ID", sortDirection: "asc" },
  { id: "categoryName", label: "categoryName", sortDirection: "asc" },
];

export const transactions: Transaction[] = [
  {
    id: 1,
    amount: -120.5,
    date: "2025-10-01",
    description: "Supermercado Extra",
    categoryId: 1,
    categoryName: "Alimentação",
  },
  {
    id: 2,
    amount: -45.9,
    date: "2025-10-03",
    description: "Uber para o trabalho",
    categoryId: 2,
    categoryName: "Transporte",
  },
  {
    id: 3,
    amount: -250,
    date: "2025-10-05",
    description: "Conta de energia elétrica",
    categoryId: 3,
    categoryName: "Contas",
  },
  {
    id: 4,
    amount: 3500,
    date: "2025-10-05",
    description: "Salário Mensal",
    categoryId: 4,
    categoryName: "Renda",
  },
  {
    id: 5,
    amount: -75.3,
    date: "2025-09-28",
    description: "Cinema com amigos",
    categoryId: 5,
    categoryName: "Lazer",
  },
  {
    id: 6,
    amount: -90.0,
    date: "2025-10-10",
    description: "Restaurante japonês",
    categoryId: 1,
    categoryName: "Alimentação",
  },
  {
    id: 7,
    amount: -30.0,
    date: "2025-10-15",
    description: "Ônibus intermunicipal",
    categoryId: 2,
    categoryName: "Transporte",
  },
  {
    id: 8,
    amount: -400.0,
    date: "2025-10-18",
    description: "Mensalidade da academia",
    categoryId: 6,
    categoryName: "Saúde",
  },
];

export const categoriesByMonth: CategoryByMonth[] = [
  {
    id: 1,
    name: "Alimentação",
    totalAmount: -210.5,
    month: 10,
    color: "#FF6384",
  },
  {
    id: 2,
    name: "Transporte",
    totalAmount: -75.9,
    month: 10,
    color: "#36A2EB",
  },
  {
    id: 3,
    name: "Contas",
    totalAmount: -250,
    month: 10,
    color: "#FFCE56",
  },
  {
    id: 4,
    name: "Renda",
    totalAmount: 3500,
    month: 10,
    color: "#4BC0C0",
  },
  {
    id: 5,
    name: "Lazer",
    totalAmount: -75.3,
    month: 9,
    color: "#9966FF",
  },
  {
    id: 6,
    name: "Saúde",
    totalAmount: -400,
    month: 10,
    color: "#FF9F40",
  },
];
