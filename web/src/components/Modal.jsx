import React from "react";
import styles from "./Modal.module.css";

const Modal = ({ title }) => {
  return (
    <div className={styles.modal}>
      <div>{title}</div>
    </div>
  );
};

export default Modal;
