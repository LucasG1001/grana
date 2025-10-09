export type ValidationResult = string | null;
export type Validator<T> = (value: T) => ValidationResult;

export function combineValidators<T>(
  ...validators: Validator<T>[]
): Validator<T> {
  return (value: T) => {
    for (const validate of validators) {
      const result = validate(value);
      if (result != null) return result;
    }
    return null;
  };
}
