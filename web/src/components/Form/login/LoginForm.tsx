'use client';
import React, { useActionState, useEffect } from 'react';
import styles from './LoginForm.module.css';
import login from '@/api/auth/login';
import { initialState } from '@/api/types';
import InputForm from '../InputForm';
import ErrorMessageForm from '../ErrorMessageForm';
import Link from 'next/link';
import Button from '@/components/button/Button';
import { useFormStatus } from 'react-dom';
import EmailValidator from '@/app/validators/EmailValidator';
import PasswordValidator from '@/app/validators/PasswordValidator';
import { error } from 'console';

function FormButton() {
  const { pending } = useFormStatus();

  return <Button text="Entrar" type="submit" disabled={pending} />;
}

const LoginForm = () => {
  const [password, setPassword] = React.useState({ value: '', error: null });
  const [email, setEmail] = React.useState({ value: '', error: null });

  const [state, action] = useActionState(login, initialState);
  useEffect(() => {
    if (state.ok) window.location.href = '/home';
  }, [state.ok]);

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target.name === 'email') {
      setEmail({ value: e.target.value, error: error });
    }
  }

  function handleBlur(e: React.FocusEvent<HTMLInputElement>) {
    if (e.target.name === 'email') {
      EmailValidator(e.target.value);
    }

    if (e.target.name === 'password') {
      PasswordValidator(e.target.value);
    }
  }

  return (
    <div className={styles.container}>
      <form action={action} className={styles.formLogin}>
        <InputForm
          type="text"
          name="email"
          id="email"
          placeholder="E-mail"
          onChange={(e) => handleChange(e)}
          onBlur={(e) => handleBlur(e)}
        />
        <InputForm
          type="password"
          name="password"
          id="password"
          placeholder="Senha"
          onChange={(e) => handleChange(e)}
          onBlur={(e) => handleBlur(e)}
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
