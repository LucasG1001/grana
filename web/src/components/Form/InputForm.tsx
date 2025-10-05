import React from 'react';
import styles from './InputForm.module.css';

type InputFormProps = {
  name: string;
  id?: string;
  placeholder?: string;
  type?: string;
  error?: string;
};

const InputForm = (props: InputFormProps) => {
  return (
    <div className={styles.container}>
      <input
        className={styles.input}
        type={props.type}
        name={props.name}
        id={props.id}
        placeholder={''}
      />
      <label className={styles.label} htmlFor={props.id}>
        {props.placeholder}
      </label>
      {props.error && (
        <p style={{ color: '#f31', margin: '1rem 0' }}>{props.error}</p>
      )}
    </div>
  );
};

export default InputForm;
