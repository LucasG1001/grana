import React from "react";
import styles from "./Modal.module.css";
import "bootstrap-icons/font/bootstrap-icons.css";

const Modal = ({ title, isOpen, onClose, children }) => {
  if (!isOpen) return null;

  return (
    <div className={styles.modalContainer}>
      <div className={styles.modalContent}>
        <div className={styles.modalHeader}>
          <span>{title}</span>
          <button className={styles.button} onClick={onClose}>
            <i className="bx bx-x"></i>
          </button>
        </div>
        {children}
      </div>
    </div>
  );
};

export default Modal;
