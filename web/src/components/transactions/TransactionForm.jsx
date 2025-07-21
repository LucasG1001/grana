import React, { useEffect } from "react";
import styles from "./TransactionForm.module.css";
import { useAuth } from "../../auth/AuthContext";
import Input from "../Input";
import api from "../../api/axios";
import { useCategory } from "../../context/useCategory";
import CategoryCrud from "../categories/CategoryCrud";
import { validInput } from "../inputValidator";
import { updateInputs } from "../inputs/updateInput";
import { useTransaction } from "../../context/useTransaction";

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
    selected: {},
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

const TransactionFormNew = ({ modal, setModal }) => {
  const [formInputs, setFormInputs] = React.useState(FORM_INPUTS);
  const { categories } = useCategory();
  const { add } = useTransaction();

  const handleSubmit = async (e) => {
    e.preventDefault();
    let isValid = true;

    formInputs.forEach((input) => {
      const errorMessage = validInput(input.type, input.value);
      if (errorMessage) {
        setFormInputs((prev) => updateInputs(prev, input.id, { errorMessage }));
      }
    });

    if (!isValid) return;

    const description = formInputs.find((input) => input.id === "description");
    const value = formInputs.find((input) => input.id === "value");
    const date = formInputs.find((input) => input.id === "date");
    const category = formInputs.find((input) => input.id === "category")
      .selected.name;

    const body = {
      type: "EXPENSE",
      description: description.value,
      value: parseFloat(
        value.value.replace("R$ ", "").replace(/\./g, "").replace(",", ".")
      ),
      date: date.value,
      installment: false,
      currentInstallment: 0,
      totalInstallments: 0,
      categoryName: category,
    };

    add(body);
    setModal(false);
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
