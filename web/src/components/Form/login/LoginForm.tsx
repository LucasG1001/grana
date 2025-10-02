'use client';
import React, { useActionState } from 'react';
import InputForm from '../InputForm';
import styles from './LoginForm.module.css';
import login from '@/api/auth/login';
import ButtonForm from '../ButtonForm';
import { useFormState, useFormStatus } from 'react-dom';
import ErrorForm from '../ErrorForm';
import { initialState } from '@/api/types';

const LoginForm = () => {
  const [state, action] = useFormState(login, initialState);
  return (
    <div className={styles.container}>
      <form action={action} className={styles.formLogin}>
        <InputForm type="text" name="email" id="email" placeholder="E-mail" />
        <InputForm
          type="password"
          name="password"
          id="password"
          placeholder="Senha"
        />
        <ErrorForm message={state?.error} />
        <ButtonForm type="submit">Entrar</ButtonForm>
      </form>
    </div>
  );
};

export default LoginForm;
