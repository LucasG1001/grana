import React, { useState } from "react";
import styles from "./Input.module.css";
import { validInput } from "./inputValidator";
import Select from "react-select";
import DropdownSelector from "./dropDown/Dropdown";

const Input = ({ input, setInput }) => {
  const updateInputs = (inputs, id, changes) => {
    return inputs.map((item) => {
      if (item.id === id) {
        return {
          ...item,
          ...changes,
        };
      }
      return item;
    });
  };
  const handleBlur = (e) => {
    const errorMessage = validInput(input.type, e.target.value);

    setInput((prev) =>
      updateInputs(prev, input.id, {
        value: e.target.value,
        errorMessage: errorMessage || "",
      })
    );
  };

  const handleChange = (e) => {
    let inputValue = e.target.value;
    if (input.id === "value") {
      inputValue = formatNumber(inputValue);
    }

    const errorMessage = input.errorMessage
      ? validInput(input.type, inputValue)
      : "";

    setInput((prev) =>
      updateInputs(prev, input.id, {
        value: inputValue,
        errorMessage: errorMessage,
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
      {input.type === "select" && (
        <DropdownSelector
          options={input.value}
          input={input}
          setInput={setInput}
        />
      )}
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
