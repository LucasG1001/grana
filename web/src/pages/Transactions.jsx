import React, { useEffect } from "react";
import api from "../api/axios";
import styles from "./Transactions.module.css";
import {} from "react-icons";
import Card from "../components/Card";
import Input from "../components/Input";
import TransactionForm from "../components/transactions/TransactionForm";
import Modal from "../components/Modal";
import { MONTHS_ABREV } from "../components/constants";
import TransactionList from "../components/transactions/TransactionList";

const Transactions = () => {
  const [transactions, setTransactions] = React.useState([]);
  const [selectedMonth, setSelectedMonth] = React.useState(
    new Date().getMonth()
  );

  const income = transactions
    .filter((transaction) => transaction.type === "INCOME")
    .reduce((acc, transaction) => acc + transaction.value, 0);

  const expense = transactions
    .filter((transaction) => transaction.type === "EXPENSE")
    .reduce((acc, transaction) => acc + transaction.value, 0);

  const BIG_NUMBERS = [
    {
      title: "Receitas",
      value: income,
    },
    {
      title: "Despesas",
      value: expense,
    },
    {
      title: "Saldo",
      value: income - expense,
    },
  ];

  useEffect(() => {
    const year = new Date().getFullYear();
    const startDate = new Date(year, selectedMonth, 1)
      .toISOString()
      .split("T")[0];
    const endDate = new Date(year, selectedMonth + 1, 0)
      .toISOString()
      .split("T")[0];

    const fetchTransactions = async () => {
      const response = await api.get("/transactions/date-between", {
        params: {
          startDate,
          endDate,
        },
      });
      setTransactions(response.data);
    };
    fetchTransactions();
  }, [selectedMonth]);

  const formatDate = (transactionDate) => {
    const date = new Date(transactionDate);

    const day = date.getDate().toString().padStart(2, "0");
    const month = MONTHS_ABREV[date.getMonth()].toUpperCase();
    return `${day} ${month}`;
  };

  return (
    <div className={styles.transactionsContainer}>
      <div className={styles.months}>
        {MONTHS_ABREV.map((month) => (
          <span
            onClick={() => setSelectedMonth(MONTHS_ABREV.indexOf(month))}
            className={`${
              selectedMonth === MONTHS_ABREV.indexOf(month)
                ? styles.selectedMonth
                : ""
            } ${styles.month}`}
            key={month}
          >
            {month}
          </span>
        ))}
      </div>
      <div className={styles.transactionsHeader}>
        {BIG_NUMBERS.map((bigNumber) => (
          <Card
            key={bigNumber.title}
            title={bigNumber.title}
            value={"R$ " + bigNumber.value.toFixed(2).replace(".", ",")}
          />
        ))}
      </div>

      <Modal
        title={"Adicionar Transação"}
        isOpen={true}
        onClose={() => setModalForm(false)}
      >
        <TransactionForm />
      </Modal>

      <TransactionList transactions={transactions} formatDate={formatDate} />
    </div>
  );
};

export default Transactions;
