import { combineValidators } from './types';

import { required, minLength, matchesRegex } from './stringValidators';

export const PasswordValidator = combineValidators(
  required,
  minLength(8),
  matchesRegex(/[A-Z]/, 'Deve conter uma letra maiúscula'),
  matchesRegex(/[0-9]/, 'Deve conter um número'),
);

export default PasswordValidator;
