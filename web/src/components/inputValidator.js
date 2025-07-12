const validations = [
  {
    id: "email",
    tests: [
      {
        id: "required",
        errorMessage: "Campo obrigatório",
        regex: /^.{1,}$/,
      },
      {
        id: "email",
        errorMessage: "Email inválido",
        regex: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
      },
    ],
  },
  {
    id: "password",
    tests: [
      {
        id: "required",
        errorMessage: "Campo obrigatório",
        regex: /^.{1,}$/,
      },
      {
        id: "length",
        errorMessage: "Mínimo de 6 caracteres",
        regex: /^.{8,}$/,
      },
      {
        id: "uppercase",
        errorMessage: "Pelo menos uma letra maiúscula",
        regex: /[A-Z]/,
      },
      {
        id: "lowercase",
        errorMessage: "Pelo menos uma letra minúscula",
        regex: /[a-z]/,
      },
      {
        id: "number",
        errorMessage: "Pelo menos um número",
        regex: /[0-9]/,
      },
    ],
  },
];

const validInput = (id, value) => {
  const inputValidations = validations.find((v) => v.id === id);

  if (!inputValidations) return null;

  if (!inputValidations) {
    setInput((prev) => ({
      ...prev,
      value,
      errorMessage: "",
    }));
  }

  let errorFound = false;

  for (const test of inputValidations.tests) {
    if (!test.regex.test(value)) {
      errorFound = test.errorMessage;
      break;
    }
  }

  return errorFound;
};

export { validInput };
