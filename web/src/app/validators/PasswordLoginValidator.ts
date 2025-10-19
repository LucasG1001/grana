import { minLength, required } from './stringValidators';
import { combineValidators } from './types';

export const PasswordLoginValidator = combineValidators(required, minLength(8));

export default PasswordLoginValidator;
