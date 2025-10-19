import { InputFormProps } from '@/types/types';
import React from 'react';

const useForm = (initialState: InputFormProps[]) => {
  const [form, setForm] = React.useState<InputFormProps[]>(initialState);
  const [isValid, setIsValid] = React.useState(false);

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value } = e.target;
    const updatedForm = form.map((field) => {
      if (field.name === name) {
        if (field.error) field.error = field.validator(value);
        return { ...field, value };
      }
      return field;
    });
    setIsValid(updatedForm.every((field) => !field.error));
    setForm(updatedForm);
  }

  function handleBlur(e: React.FocusEvent<HTMLInputElement>) {
    const { name } = e.target;
    const updatedForm = form.map((field) => {
      if (field.name === name) {
        return { ...field, error: field.validator(field.value) };
      }
      return field;
    });
    setIsValid(updatedForm.every((field) => !field.error));
    setForm(updatedForm);
  }

  function validateAll() {
    const updatedForm = form.map((field) => {
      return { ...field, error: field.validator(field.value) };
    });

    setIsValid(updatedForm.every((field) => !field.error));

    setForm(updatedForm);
  }

  return { form, validateAll, isValid, handleChange, handleBlur };
};

export default useForm;
