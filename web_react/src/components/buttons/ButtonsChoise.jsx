import React from "react";
import styles from "./ButtonsChoise.module.css";

const ButtonsChoise = ({ handleSubmit, action, setModal }) => {
  return (
    <div className={styles.buttons}>
      <button
        onClick={() => setModal(false)}
        className={`${styles.button} ${styles.cancelButton}`}
      >
        Cancelar
      </button>
      <button
        onClick={handleSubmit}
        className={`${styles.button} ${styles.saveButton}`}
      >
        {action}
      </button>
    </div>
  );
};

export default ButtonsChoise;
