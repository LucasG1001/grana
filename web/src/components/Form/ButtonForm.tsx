import React from 'react';
import styles from './ButtonForm.module.css';
import { useFormStatus } from 'react-dom';

type ButtonType = React.ButtonHTMLAttributes<HTMLButtonElement>;

const ButtonForm = ({ children, ...props }: ButtonType) => {
  const { pending } = useFormStatus();
  return (
    <button {...props} className={styles.button} disabled={pending}>
      {pending ? 'Carregando...' : children}
    </button>
  );
};

export default ButtonForm;
