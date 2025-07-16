import React, { useEffect } from "react";
import styles from "./TransactionForm.module.css";
import { useAuth } from "../auth/AuthContext";
import Input from "./Input";
import api from "../api/axios";

const FORM_INPUTS = [
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
  },
  {
    id: "date",
    label: "Data",
    type: "datetime-local",
    value: new Date().toISOString().slice(0, 16),
    errorMessage: "",
  },
  {
    id: "category",
    label: "Categoria",
    type: "select",
    value: [],
    errorMessage: "",
  },
  {
    id: "type",
    label: "Tipo",
    type: "select",
    value: [],
    errorMessage: "",
  },
  {
    id: "color",
    label: "Cor",
    type: "color",
    value: "",
    errorMessage: "",
  },
];

const TransactionFormNew = () => {
  const [formMode, setFormMode] = React.useState("login");
  const [formInputs, setFormInputs] = React.useState(FORM_INPUTS);
  const [categories, setCategories] = React.useState([]);

  useEffect(() => {
    const fetchCategory = async () => {
      const response = await api.get("/categories");
      setCategories(response.data);
    };
    fetchCategory();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
  };

  if (categories.length === 0) return <div>Carregando...</div>;

  FORM_INPUTS[3].value = categories;

  return (
    <form onSubmit={handleSubmit} className={styles.form} action="">
      <div className={styles.formMode}>
        <div className={styles.active}>Adicionar Transação</div>
      </div>
      <div className={styles.inputs}>
        {formInputs.map((input) => {
          return (
            <div
              key={input.id}
              className={
                input.id === "description" ? styles.description : styles.input
              }
            >
              <Input key={input.id} input={input} setInput={setFormInputs} />
              <span className={styles.errorMessage}>{input.errorMessage}</span>
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
