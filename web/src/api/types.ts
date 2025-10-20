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
  id: string;
  amount: number;
  date: string;
  description: string;
  categoryId: string;
  categoryName: string;
};

export type CategoryByMonth = {
  id: string;
  name: string;
  totalAmount: number;
  month: number;
};

export const transactions: Transaction[] = [
  {
    id: "t1",
    amount: -120.5,
    date: "2025-10-01",
    description: "Supermercado Extra",
    categoryId: "c1",
    categoryName: "Alimentação",
  },
  {
    id: "t2",
    amount: -45.9,
    date: "2025-10-03",
    description: "Uber para o trabalho",
    categoryId: "c2",
    categoryName: "Transporte",
  },
  {
    id: "t3",
    amount: -250,
    date: "2025-10-05",
    description: "Conta de energia elétrica",
    categoryId: "c3",
    categoryName: "Contas",
  },
  {
    id: "t4",
    amount: 3500,
    date: "2025-10-05",
    description: "Salário Mensal",
    categoryId: "c4",
    categoryName: "Renda",
  },
  {
    id: "t5",
    amount: -75.3,
    date: "2025-09-28",
    description: "Cinema com amigos",
    categoryId: "c5",
    categoryName: "Lazer",
  },
  {
    id: "t6",
    amount: -90.0,
    date: "2025-10-10",
    description: "Restaurante japonês",
    categoryId: "c1",
    categoryName: "Alimentação",
  },
  {
    id: "t7",
    amount: -30.0,
    date: "2025-10-15",
    description: "Ônibus intermunicipal",
    categoryId: "c2",
    categoryName: "Transporte",
  },
  {
    id: "t8",
    amount: -400.0,
    date: "2025-10-18",
    description: "Mensalidade da academia",
    categoryId: "c6",
    categoryName: "Saúde",
  },
];

export const categoriesByMonth: CategoryByMonth[] = [
  {
    id: "c1",
    name: "Alimentação",
    totalAmount: -210.5,
    month: 10,
  },
  {
    id: "c2",
    name: "Transporte",
    totalAmount: -75.9,
    month: 10,
  },
  {
    id: "c3",
    name: "Contas",
    totalAmount: -250,
    month: 10,
  },
  {
    id: "c4",
    name: "Renda",
    totalAmount: 3500,
    month: 10,
  },
  {
    id: "c5",
    name: "Lazer",
    totalAmount: -75.3,
    month: 9,
  },
  {
    id: "c6",
    name: "Saúde",
    totalAmount: -400,
    month: 10,
  },
];
