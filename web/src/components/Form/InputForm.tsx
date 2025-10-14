import React from 'react';
import styles from './InputForm.module.css';

type InputFormProps = {
  name: string;
  id?: string;
  placeholder?: string;
  type?: string;
  error: string | null;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur: (event: React.FocusEvent<HTMLInputElement>) => void;
};

const InputForm = (props: InputFormProps) => {
  return (
    <div className={styles.container}>
      <input
        className={`${styles.input} ${props.error ? styles.error : ''}`}
        type={props.type}
        name={props.name}
        id={props.id}
        placeholder={''}
        onChange={props.onChange}
        onBlur={props.onBlur}
      />
      <label className={styles.label} htmlFor={props.id}>
        {props.placeholder}
      </label>
      {props.error && (
        <p style={{ color: '#f31', marginTop: '0.5rem', fontSize: '0.8rem' }}>
          {props.error}
        </p>
      )}
    </div>
  );
};

export default InputForm;
