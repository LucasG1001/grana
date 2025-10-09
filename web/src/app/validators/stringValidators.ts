import { Validator } from './types';

export const required: Validator<string> = (value) =>
  !value || value.trim() === '' ? 'Campo obrigatório' : null;

export const minLength =
  (min: number): Validator<string> =>
  (value) =>
    value.length < min ? `Mínimo de ${min} caracteres` : null;

export const maxLength =
  (max: number): Validator<string> =>
  (value) =>
    value.length > max ? `Máximo de ${max} caracteres` : null;

export const matchesRegex =
  (regex: RegExp, message: string): Validator<string> =>
  (value) =>
    regex.test(value) ? null : message;
