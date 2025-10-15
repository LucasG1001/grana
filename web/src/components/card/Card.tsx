import React from "react";
import styles from "./Card.module.css";

const Card = ({
  title,
  children,
}: {
  title: string;
  children: React.ReactNode;
}) => {
  return (
    <div className={styles.card}>
      {title && <h2 className={styles.title}>{title}</h2>}
      {children}
    </div>
  );
};

export default Card;
