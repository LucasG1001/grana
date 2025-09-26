import React, { useEffect } from "react";
import styles from "./Transactions.module.css";
import Card from "../components/Card";
import TransactionForm from "../components/transactions/TransactionForm";
import Modal from "../components/Modal";
import { MONTHS_ABREV, TRANSACTION_COLUMNS } from "../components/constants";
import { useCategory } from "../context/useCategory";
import OrdenableTable from "../components/table/OrdenableTable";
import CategoryDisplay from "../components/categories/CategoryDisplay";
import { useTransaction } from "../context/useTransaction";
import WaveChart from "../components/graphics/WaveChart";
import PieChartComponent from "../components/graphics/PieChartComponent";
import Actions from "../components/actions/Actions";

const Transactions = () => {
  const [selectedMonth, setSelectedMonth] = React.useState(
    new Date().getMonth()
  );
  const { get } = useCategory();
  const { getByMonth, transactions } = useTransaction();
  const [crudModal, setCrudModal] = React.useState(false);
  const [selectedTransaction, setSelectedTransaction] = React.useState({});

  useEffect(() => {
    get();
  }, []);

  useEffect(() => {
    getByMonth(selectedMonth);
  }, [selectedMonth]);
  const [formMode, setFormMode] = React.useState("null");

  const [modal, setModal] = React.useState(false);
  const { add } = useTransaction();

  useEffect(() => {
    if (formMode === "new") {
      setModal(true);
    } else if (formMode === "edit") {
      setModal(true);
    }
  }, [formMode]);

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
        title={`${formMode === "new" ? "Nova" : "Editar"} Transação`}
        isOpen={modal}
        onClose={() => setModal(false)}
      >
        <TransactionForm
          transaction={selectedTransaction}
          formMode={formMode}
          modal={modal}
          setModal={setModal}
        />
      </Modal>
      <div className={styles.transactionsGraphics}>
        <WaveChart />
        <PieChartComponent />
      </div>
      <OrdenableTable
        title="Transações"
        data={transactions.map((transaction) => ({
          date: formatDate(transaction.date),
          category: (
            <CategoryDisplay
              key={transaction.category.id}
              categoryName={transaction.category.name}
              categoryColor={transaction.category.color}
              categoryIcon={transaction.category.icon}
            />
          ),
          description: transaction.description,
          value: transaction.value,
          actions: (
            <Actions
              onEdit={() => {
                setSelectedTransaction(transaction);
                setFormMode("edit");
                setModal(true);
              }}
              onDelete={() => setFormMode("delete")}
            />
          ),
        }))}
        columns={TRANSACTION_COLUMNS}
        modal={modal}
        setModal={setModal}
      />
      ;
    </div>
  );
};

export default Transactions;
