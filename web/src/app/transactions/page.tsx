"use client";
import PieChartComponent from "@/components/graphics/pie/PieChartComponent";
import styles from "./page.module.css";
import OrdenableTable from "@/components/table/OrdenableTable";
import Card from "@/components/card/Card";
import { useEffect, useState } from "react";
import {
  categoriesByMonth,
  CategoryByMonth,
  Transaction,
  transactions,
  transactionsTableColumns,
} from "@/api/types";

const TransactionPage = () => {
  const [transaction, setTransactions] = useState<Transaction[]>(transactions);
  const [categoryByMonth, setCategoryByMonth] =
    useState<CategoryByMonth[]>(categoriesByMonth);

  const [categoryId, setCategoryId] = useState<number | null>(null);

  return (
    <div className={styles.container}>
      <div className={styles.graphics}>
        <Card title="Teste">Teste</Card>
        <Card title="Transações por Categoria">
          <PieChartComponent
            data={categoryByMonth}
            handleSelected={setCategoryId}
          />
        </Card>
      </div>
      <div className={styles.transactions}>
        <Card title="Transações">
          <OrdenableTable
            data={transaction.filter((t) =>
              categoryId ? t.categoryId === categoryId : true
            )}
            columns={transactionsTableColumns}
          />
        </Card>
      </div>
    </div>
  );
};

export default TransactionPage;
