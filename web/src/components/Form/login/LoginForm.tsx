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

function FormButton() {
  const { pending } = useFormStatus();

  return <Button text="Entrar" type="submit" disabled={pending} />;
}

const LoginForm = () => {
  const [state, action] = useActionState(login, initialState);
  const [form, setForm] = React.useState({
    email: { value: '', error: null, validator: EmailValidator },
    password: { value: '', error: null, validator: PasswordValidator },
  });

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    validateAll();

    const hasError = Object.values(form).some((field) => field.error != null);
    if (hasError) return;
    const formData = new FormData();
    formData.append('email', form.email.value);
    formData.append('password', form.password.value);

    console.log(formData, hasError, form);

    // action(formData);
  }

  useEffect(() => {
    if (state.ok) window.location.href = '/home';
  }, [state.ok]);

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target;

    setForm((prev) => {
      const field = prev[name as keyof typeof prev];
      const error = field.error ? field.validator(value) : null;
      return {
        ...prev,
        [name]: { ...field, value, error },
      };
    });
  }

  function handleBlur(e: React.FocusEvent<HTMLInputElement>) {
    const { name, value } = e.target;

    validate({ name, value });
  }

  function validate({ name, value }: { name: string; value: string }) {
    setForm((prev) => {
      const field = prev[name as keyof typeof prev];
      const error = field.validator(value);
      return {
        ...prev,
        [name]: { ...field, error },
      };
    });
  }

  function validateAll() {
    Object.keys(form).forEach((name) => {
      validate({ name, value: form[name as keyof typeof form].value });
    });
  }

  return (
    <div className={styles.container}>
      <form onSubmit={handleSubmit} className={styles.formLogin}>
        <InputForm
          type="text"
          name="email"
          id="email"
          placeholder="E-mail"
          onChange={(e) => handleChange(e)}
          onBlur={(e) => handleBlur(e)}
          error={form.email.error}
        />
        <InputForm
          type="password"
          name="password"
          id="password"
          placeholder="Senha"
          onChange={(e) => handleChange(e)}
          onBlur={(e) => handleBlur(e)}
          error={form.password.error}
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
