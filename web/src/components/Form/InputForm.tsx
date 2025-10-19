import React from 'react';
import styles from './InputForm.module.css';
import { InputFormProps } from '../../types/types';

interface Props {
  inputProps: InputFormProps;
  handleChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  handleBlur: (e: React.FocusEvent<HTMLInputElement>) => void;
}

const InputForm = ({ inputProps, handleChange, handleBlur }: Props) => {
  return (
    <div className={styles.container}>
      <input
        className={`${styles.input} ${inputProps.error ? styles.error : ''}`}
        type={inputProps.type}
        name={inputProps.name}
        id={inputProps.name}
        placeholder={''}
        onChange={handleChange}
        onBlur={handleBlur}
        value={inputProps.value}
      />
      <label className={styles.label} htmlFor={inputProps.name}>
        {inputProps.placeholder}
      </label>
      {inputProps.error && (
        <p style={{ color: '#f31', marginTop: '0.5rem', fontSize: '0.8rem' }}>
          {inputProps.error}
        </p>
      )}
    </div>
  );
};

export default InputForm;
