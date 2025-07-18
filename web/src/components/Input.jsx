import React, { useState } from "react";
import styles from "./Input.module.css";
import { validInput } from "./inputValidator";
import Select from "react-select";
import InputSelect from "./InputSelect";

const Input = ({ input, setInput }) => {
  const handleBlur = (e) => {
    const errorMessage = validInput(input.id, e.target.value);

    setInput((prev) =>
      prev.map((item) => {
        if (item.id === input.id) {
          return {
            ...item,
            value: e.target.value,
            errorMessage: errorMessage || "",
          };
        }
        return item;
      })
    );
  };

  const handleChange = (e) => {
    let inputValue = e.target.value;
    let errorMessage = null;
    if (input.id === "value") {
      inputValue = formatNumber(inputValue);
    }

    if (input.errorMessage) {
      errorMessage = validInput(input.id, inputValue);
    }

    setInput((prev) =>
      prev.map((item) => {
        if (item.id === input.id) {
          return {
            ...item,
            value: inputValue,
            errorMessage: errorMessage || "",
          };
        }
        return item;
      })
    );
  };

  const formatNumber = (inputValue) => {
    inputValue = Number(inputValue.replace(/\D/g, "")).toString();
    let inputLength = inputValue.length;

    let decimal =
      "," + inputValue.slice(inputLength - 2, inputLength).padStart(2, "0");

    let integer = inputValue
      .slice(0, inputLength - 2)
      .replace(/\B(?=(\d{3})+(?!\d))/g, ".");

    if (integer === "") {
      integer = "0";
    }

    let newValue = "R$ " + integer + decimal;

    return newValue;
  };

  return (
    <div className={styles.inputContainer}>
      <label className={styles.label} htmlFor={input.id}>
        {input.label}
      </label>
      {input.type === "select" && <InputSelect itens={input.value} />}
      {input.type !== "select" && (
        <input
          onBlur={handleBlur}
          onChange={handleChange}
          className={`${styles.input} ${input.errorMessage && styles.error}`}
          type={input.type}
          name={input.id}
          id={input.id}
          value={input.value}
          inputMode={input.mode}
        />
      )}
      {input.errorMessage && (
        <p className={styles.errorMessage}>{input.errorMessage}</p>
      )}
    </div>
  );
};

export default Input;
