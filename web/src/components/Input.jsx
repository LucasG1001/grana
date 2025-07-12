import React from "react";
import styles from "./Input.module.css";

const Input = ({ input, setInput }) => {
  const validations = [
    {
      id: "email",
      tests: [
        {
          id: "required",
          errorMessage: "Campo obrigatório",
          regex: /^.{1,}$/,
        },
        {
          id: "email",
          errorMessage: "Email inválido",
          regex: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
        },
      ],
    },
    {
      id: "password",
      tests: [
        {
          id: "required",
          errorMessage: "Campo obrigatório",
          regex: /^.{1,}$/,
        },
        {
          id: "length",
          errorMessage: "Mínimo de 6 caracteres",
          regex: /^.{8,}$/,
        },
        {
          id: "uppercase",
          errorMessage: "Pelo menos uma letra maiúscula",
          regex: /[A-Z]/,
        },
        {
          id: "lowercase",
          errorMessage: "Pelo menos uma letra minúscula",
          regex: /[a-z]/,
        },
        {
          id: "number",
          errorMessage: "Pelo menos um número",
          regex: /[0-9]/,
        },
      ],
    },
  ];
  const handleBlur = (e) => {
    validInput(e.target.value);
  };

  const handleChange = (e) => {
    if (input.errorMessage) {
      validInput(e.target.value);
    } else {
      setInput((prev) => ({
        ...prev,
        value: e.target.value,
      }));
    }
  };

  const validInput = (value) => {
    const inputValidations = validations.find(
      (validation) => validation.id === input.id
    );

    if (!inputValidations) {
      setInput((prev) => ({
        ...prev,
        value,
        errorMessage: "",
      }));
    }

    let errorFound = false;

    for (const test of inputValidations.tests) {
      if (!test.regex.test(value)) {
        errorFound = test.errorMessage;
        break;
      }
    }

    setInput((prev) => ({
      ...prev,
      value,
      errorMessage: errorFound || "",
    }));
  };

  return (
    <div className={styles.inputContainer}>
      <label className={styles.label} htmlFor={input.id}>
        {input.label}
      </label>
      <input
        onBlur={handleBlur}
        onChange={handleChange}
        className={`${styles.input} ${input.errorMessage && styles.error}`}
        type={input.type}
        name={input.id}
        id={input.id}
      />
      {input.errorMessage && (
        <p className={styles.errorMessage}>{input.errorMessage}</p>
      )}
    </div>
  );
};

export default Input;
