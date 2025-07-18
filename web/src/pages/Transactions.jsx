import React, { useEffect } from "react";
import api from "../api/axios";
import styles from "./Transactions.module.css";
import {} from "react-icons";
import Card from "../components/Card";
import Input from "../components/Input";
import TransactionFormNew from "../components/TransactionFormNew";
import Modal from "../components/Modal";

const MONTHS_ABREV = [
  "JAN",
  "FEV",
  "MAR",
  "ABR",
  "MAI",
  "JUN",
  "JUL",
  "AGO",
  "SET",
  "OUT",
  "NOV",
  "DEZ",
].filter((_, index) => index <= new Date().getMonth());

const Transactions = () => {
  const [transactions, setTransactions] = React.useState([]);
  const [selectedMonth, setSelectedMonth] = React.useState(
    new Date().getMonth()
  );
  const [modalForm, setModalForm] = React.useState(true);
  const [modalEdit, setModalEdit] = React.useState(false);
  const [modalDelete, setModalDelete] = React.useState(false);

  useEffect(() => {
    const year = new Date().getFullYear();
    const startDate = new Date(year, selectedMonth, 1)
      .toISOString()
      .replace("Z", "");
    const endDate = new Date(year, selectedMonth + 1, 0)
      .toISOString()
      .replace("Z", "");

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
        <Card
          title={"Receitas"}
          value={
            "R$ " +
            transactions
              .reduce((acc, transaction) => acc + transaction.value, 0)
              .toString()
              .replace(".", ",")
          }
        />
        <Card
          title={"Despesas"}
          value={
            "R$ " +
            transactions
              .reduce((acc, transaction) => acc + transaction.value, 0)
              .toString()
              .replace(".", ",")
          }
        />
      </div>
      <Modal
        title={"Adicionar Transação"}
        isOpen={true}
        onClose={() => setModalForm(false)}
      >
        <div>
          <TransactionFormNew />
        </div>
      </Modal>
      <ul className={styles.transactions}>
        <div className={styles.listHeader}>
          <span className={styles.listTitle}>Transações</span>
          <button
            className={styles.listButton}
            onClick={() => showModal(<h1>Adicionar</h1>)}
          >
            Adicionar
          </button>
        </div>
        {transactions.map((transaction) => (
          <li className={styles.transaction} key={transaction.id}>
            <span className={styles.date}>{formatDate(transaction.date)}</span>
            <span
              style={{ background: `${transaction.categoryColor}` }}
              className={styles.categoryName}
            >
              {transaction.categoryName}
            </span>
            <span>{transaction.description}</span>
            <span>R$ {transaction.value.toString().replace(".", ",")}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Transactions;
