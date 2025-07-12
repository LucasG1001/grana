import React from "react";
import styles from "./Input.module.css";
import { validInput } from "./inputValidator";

const Input = ({ input, setInput }) => {
  const handleBlur = (e) => {
    const errorMessage = validInput(input.id, e.target.value);

    setInput((prev) => ({
      ...prev,
      errorMessage: errorMessage || "",
    }));
  };

  const handleChange = (e) => {
    if (input.errorMessage) {
      const errorMessage = validInput(input.id, e.target.value);
      setInput((prev) => ({
        ...prev,
        value: e.target.value,
        errorMessage: errorMessage || "",
      }));
    } else {
      setInput((prev) => ({
        ...prev,
        value: e.target.value,
      }));
    }
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
