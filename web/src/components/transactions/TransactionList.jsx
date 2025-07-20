import React from "react";
import styles from "./TransactionList.module.css";

const TransactionList = ({ transactions, formatDate }) => {
  return (
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
  );
};

export default TransactionList;
