'use client';
import React, { useActionState, useEffect } from 'react';
import styles from './LoginForm.module.css';
import login from '@/api/auth/login';
import { initialState } from '@/api/types';
import InputForm from '../InputForm';
import ErrorMessageForm from '../ErrorMessageForm';
import Link from 'next/link';
import Button from '@/components/button/Button';
import { useFormState } from 'react-dom';

function FormButton() {
  const { pending } = useFormState();

  return <Button text="Entrar" type="submit" disabled={pending} />;
}

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
        {state?.error && <ErrorMessageForm message={state?.error} />}
        <Link href="login/esqueci" className={styles.link}>
          <span className={styles.span}>Esqueceu a senha?</span> Enviar email de
          recuperação
        </Link>
        <FormButton />
        <Link
          href="login/esqueci"
          style={{ textAlign: 'center' }}
          className={styles.link}
        >
          <span className={styles.span}>Ainda não possui conta?</span>{' '}
          Cadastre-se
        </Link>
      </form>
    </div>
  );
};

export default LoginForm;
