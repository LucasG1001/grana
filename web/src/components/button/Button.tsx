import React from 'react';
import styles from './Button.module.css';

type ButtonProps = {
  text: string;
  onClick?: () => void;
  type?: 'button' | 'submit' | 'reset';
  disabled?: boolean;
};

const Button = (buttonProps: ButtonProps) => {
  return (
    <button {...buttonProps} className={styles.button}>
      {' '}
      {buttonProps.text}
    </button>
  );
};

export default Button;
