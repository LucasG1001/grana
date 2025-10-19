import { combineValidators } from './types';
import {
  required,
  minLength,
  maxLength,
  matchesRegex,
} from './stringValidators';

export const EmailValidator = combineValidators(
  required,
  maxLength(100),
  matchesRegex(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i, 'Email inválido'),
);

export default EmailValidator;
