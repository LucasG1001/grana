import React from "react";
import styles from "./Card.module.css";

const Card = ({ title, value }) => {
  return (
    <div className={styles.card}>
      <span className={styles.title}>{title}</span>
      <span className={styles.value}>{value}</span>
    </div>
  );
};

export default Card;
