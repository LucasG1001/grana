import React from "react";
import styles from "./TransactionList.module.css";
import { useCategory } from "../../context/useCategory";
import CategoryDisplay from "../categories/CategoryDisplay";

const TransactionList = ({ transactions, formatDate, modal, setModal }) => {
  const { categories } = useCategory();
  return (
    <ul className={styles.transactions}>
      <div className={styles.listHeader}>
        <span className={styles.listTitle}>Transações</span>
        <button className={styles.listButton} onClick={() => setModal(true)}>
          Adicionar
        </button>
      </div>
      {transactions.map((transaction) => (
        <li className={styles.transaction} key={transaction.id}>
          <span className={styles.date}>{formatDate(transaction.date)}</span>

          <CategoryDisplay
            categoryName={transaction.category.name}
            categoryColor={transaction.category.color}
            categoryIcon={transaction.category.icon}
          />
          <span>{transaction.description}</span>
          <span>R$ {transaction.value.toString().replace(".", ",")}</span>
        </li>
      ))}
    </ul>
  );
};

export default TransactionList;
