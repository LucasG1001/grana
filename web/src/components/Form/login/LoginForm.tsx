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
import PasswordLoginValidator from '@/app/validators/PasswordLoginValidator';
import { InputFormProps } from '../../../types/types';
import useForm from '@/hooks/useForm';

function FormButton() {
  const { pending } = useFormStatus();

  return <Button text="Entrar" type="submit" disabled={pending} />;
}

const initialForm: InputFormProps[] = [
  {
    placeholder: 'Email',
    name: 'email',
    value: '',
    validator: EmailValidator,
    error: null,
    type: 'email',
  },
  {
    placeholder: 'Senha',
    name: 'password',
    value: '',
    validator: PasswordLoginValidator,
    error: null,
    type: 'password',
  },
];

const LoginForm = () => {
  const [state, action] = useActionState(login, initialState);
  const { form, isValid, validateAll, handleChange, handleBlur } =
    useForm(initialForm);

  async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    validateAll();

    if (!isValid) return;
    const formData = new FormData();

    console.log(formData);

    initialForm.forEach((field) => {
      formData.append(field.name, field.value);
    });

    // action(formData);
  }

  useEffect(() => {
    if (state.ok) window.location.href = '/home';
  }, [state.ok]);

  return (
    <div className={styles.container}>
      <form onSubmit={handleSubmit} className={styles.formLogin}>
        {form.map((input) => (
          <InputForm
            inputProps={input}
            handleChange={handleChange}
            handleBlur={handleBlur}
            key={input.name}
          />
        ))}

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
