import React from "react";
import styles from "./Actions.module.css";

const Actions = ({ onEdit, onDelete, OnAdd }) => {
  return (
    <div className={styles.actions}>
      {OnAdd && (
        <i
          onClick={() => OnAdd("new")}
          className={`${"bx  bx-plus"} ${styles.icon}`}
        ></i>
      )}
      {onEdit && (
        <i
          onClick={() => onEdit("edit")}
          className={`${"bx  bx-edit"} ${styles.icon}`}
        ></i>
      )}
      {onDelete && (
        <i
          onClick={() => onDelete("delete")}
          className={`${"bx  bx-trash"} ${styles.icon}`}
        ></i>
      )}
    </div>
  );
};

export default Actions;
