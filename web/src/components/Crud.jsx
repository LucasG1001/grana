import React from "react";
import styles from "./Crud.module.css";

const Crud = ({ editItem }) => {
  return (
    <div className={styles.actions}>
      <i className={`${"bx  bx-trash"} ${styles.icon}`}></i>
      <i onClick={editItem} className={`${"bx  bx-edit"} ${styles.icon}`}></i>
      <i className={`${"bx  bx-plus"} ${styles.icon}`}></i>
    </div>
  );
};

export default Crud;
