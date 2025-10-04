'use client';
import React, { useActionState, useEffect } from 'react';
import styles from './LoginForm.module.css';
import login from '@/api/auth/login';
import { initialState } from '@/api/types';
import InputForm from '../InputForm';
import ErrorMessageForm from '../ErrorMessageForm';
import ButtonForm from '../ButtonForm';
import Link from 'next/link';

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
        <Link href="login/esqueci" className={styles.link}>
          <span className={styles.span}>Esqueceu a senha?</span> Enviar email de
          recuperação
        </Link>
        <ButtonForm type="submit">Entrar</ButtonForm>
        <Link href="login/esqueci" className={styles.link}>
          <span className={styles.span}>Ainda não possui conta?</span>{' '}
          Cadastre-se
        </Link>
      </form>
    </div>
  );
};

export default LoginForm;
