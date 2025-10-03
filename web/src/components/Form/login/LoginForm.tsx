'use client';
import React, { useActionState, useEffect } from 'react';
import styles from './LoginForm.module.css';
import login from '@/api/auth/login';
import { useFormState, useFormStatus } from 'react-dom';
import { initialState } from '@/api/types';
import InputForm from '../InputForm';
import ErrorMessageForm from '../ErrorMessageForm';
import ButtonForm from '../ButtonForm';

const LoginForm = () => {
  const [state, action] = useActionState(login, initialState);
  useEffect(() => {
    if (state.ok) window.location.href = '/home';
  }, [state.ok]);
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
        <ErrorMessageForm message={state?.error} />
        <ButtonForm type="submit">Entrar</ButtonForm>
      </form>
    </div>
  );
};

export default LoginForm;
