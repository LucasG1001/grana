type InputFormProps = {
  name: string;
  value: string;
  error: string | null;
  validator: (value: string) => string | null;
  placeholder: string;
  type: string;
};

export type { InputFormProps };
