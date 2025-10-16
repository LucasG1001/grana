'use client';
import PieChartComponent from '@/components/graphics/pie/PieChartComponent';
import styles from './page.module.css';
import OrdenableTable from '@/components/table/OrdenableTable';
import Card from '@/components/card/Card';
import { useEffect, useState } from 'react';

type OrdenableTableProps = {
  title: string;
  data: Array<Record<string, any>>;
  columns: Array<{
    id: string;
    label: string;
    sortDirection: 'asc' | 'desc';
  }>;
};

const transactions = [
  {
    description: 'Celular',
    categoryId: 1,
    categoryName: 'Eletrônicos',
    value: 4000,
  },
  { description: 'Camiseta', categoryId: 2, categoryName: 'Moda', value: 3000 },
  { description: 'Casa', categoryId: 3, categoryName: 'Casa', value: 2000 },
  { description: 'Beleza', categoryId: 4, categoryName: 'Beleza', value: 1500 },
  { description: 'Livros', categoryId: 5, categoryName: 'Livros', value: 1000 },
  {
    description: 'Celular',
    categoryId: 1,
    categoryName: 'Eletrônicos',
    value: 4000,
  },
  { description: 'Camiseta', categoryId: 2, categoryName: 'Moda', value: 3000 },
  { description: 'Casa', categoryId: 3, categoryName: 'Casa', value: 2000 },
  { description: 'Beleza', categoryId: 4, categoryName: 'Beleza', value: 1500 },
  { description: 'Livros', categoryId: 5, categoryName: 'Livros', value: 1000 },
];

const columns = [
  { id: 'description', label: 'Descrição', sortDirection: 'asc' },
  { id: 'value', label: 'Valor', sortDirection: 'asc' },
  { id: 'categoryName', label: 'Categoria', sortDirection: 'asc' },
];

const TransactionPage = () => {
  const [categoryId, setCategoryId] = useState(null);
  const [data, setData] = useState(transactions);

  useEffect(() => {
    if (categoryId) {
      setData(
        transactions.filter(
          (transaction) => transaction.categoryId === categoryId,
        ),
      );
    } else {
      setData(transactions);
    }
  }, [categoryId]);

  const categories = [
    { id: 1, name: 'Vendas', value: 400 },
    { id: 2, name: 'Marketing', value: 300 },
    { id: 3, name: 'Operações', value: 300 },
  ];

  return (
    <div className={styles.container}>
      <div className={styles.graphics}>
        <Card title="Teste">Teste</Card>
        <Card title="Transações por Categoria">
          <PieChartComponent data={categories} handleSelected={setCategoryId} />
        </Card>
      </div>
      <div className={styles.transactions}>
        <Card title="Transações">
          <OrdenableTable data={data} columns={columns} />
        </Card>
      </div>
    </div>
  );
};

export default TransactionPage;
