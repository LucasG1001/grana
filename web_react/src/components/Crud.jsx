import React from "react";
import styles from "./Crud.module.css";

const Crud = ({ setFormMode, setModal, actions }) => {
  const handleClick = (mode) => {
    setFormMode(mode);
    setModal(true);
  };

  return (
    <div className={styles.actions}>
      {actions.includes("new") && (
        <i
          onClick={() => handleClick("new")}
          className={`${"bx  bx-plus"} ${styles.icon}`}
        ></i>
      )}
      {actions.includes("edit") && (
        <i
          onClick={() => handleClick("edit")}
          className={`${"bx  bx-edit"} ${styles.icon}`}
        ></i>
      )}
      {actions.includes("delete") && (
        <i
          onClick={() => handleClick("delete")}
          className={`${"bx  bx-trash"} ${styles.icon}`}
        ></i>
      )}
    </div>
  );
};

export default Crud;
