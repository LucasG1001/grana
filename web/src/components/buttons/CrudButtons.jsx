import React from "react";
import styles from "./CrudButtons.module.css";

export const CrudButtons = ({ onAdd, onEdit, onDelete }) => {
  return (
    <div className={styles.CrudButtons}>
      <button className={styles.button} onClick={onAdd}>
        Adicionar
      </button>
      <button className={styles.button} onClick={onEdit}>
        Editar
      </button>
      <button className={styles.button} onClick={onDelete}>
        Excluir
      </button>
    </div>
  );
};
