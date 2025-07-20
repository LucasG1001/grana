import React, { useEffect } from "react";
import styles from "./TransactionForm.module.css";
import { useAuth } from "../../auth/AuthContext";
import Input from "../Input";
import api from "../../api/axios";
import { useCategory } from "../../context/useCategory";
import CategoryCrud from "../categories/CategoryCrud";

export const FORM_INPUTS = [
  {
    id: "description",
    label: "Descrição",
    type: "text",
    value: "",
  },
  {
    id: "value",
    label: "Valor",
    type: "text",
    value: "R$ 0,00",
    errorMessage: "",
    mode: "numeric",
  },
  {
    id: "date",
    label: "Data",
    type: "date",
    value: new Date().toISOString().split("T")[0],
    errorMessage: "",
  },
  {
    id: "category",
    label: "Categoria",
    type: "select",
    value: [],
    errorMessage: "",
    crudComponent: (input, formMode, modal, setModal) => (
      <CategoryCrud
        input={input}
        formMode={formMode}
        modal={modal}
        setModal={setModal}
      />
    ),
  },
];

const TransactionFormNew = () => {
  const [formInputs, setFormInputs] = React.useState(FORM_INPUTS);
  const { categories, get, getById, add, edit, remove } = useCategory();

  useEffect(() => {
    get();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
  };

  if (categories.length === 0) return null;
  formInputs[3].value = categories;

  return (
    <form onSubmit={handleSubmit} className={styles.form} action="">
      <div className={styles.inputs}>
        {formInputs.map((input) => {
          return (
            <div
              key={input.id}
              className={
                input.id === "description" || input.id === "category"
                  ? styles.grid
                  : styles.input
              }
            >
              <Input key={input.id} input={input} setInput={setFormInputs} />
            </div>
          );
        })}
      </div>
      <button className={`${styles.button}`} type="submit">
        Salvar
      </button>
    </form>
  );
};

export default TransactionFormNew;
