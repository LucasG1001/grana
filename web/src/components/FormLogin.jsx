import React, { useEffect } from "react";
import styles from "./FormLogin.module.css";
import Input from "./Input";
import { validInput } from "./inputValidator";
import { toast } from "react-toastify";
import api from "../api/axios";
import { useAuth } from "../auth/AuthContext";

const FORM_INPUTS = [
  {
    id: "email",
    label: "Email",
    type: "email",
    value: "",
    errorMessage: "",
  },
  {
    id: "password",
    label: "Senha",
    type: "password",
    value: "",
    errorMessage: "",
  },
];

const Form = () => {
  const [formMode, setFormMode] = React.useState("login");
  const { login } = useAuth();
  const [formInputs, setFormInputs] = React.useState(FORM_INPUTS);

  useEffect(() => {
    setFormInputs(FORM_INPUTS);
  }, [formMode]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const email = formInputs.find((input) => input.id === "email");
    const password = formInputs.find((input) => input.id === "password");
    let isValid = true;

    formInputs.forEach((input) => {
      const errorMessage = validInput(input.id, input.value);
      if (errorMessage) {
        input = {
          ...input,
          errorMessage: errorMessage || "",
        };
        isValid = false;
      }
    });

    if (isValid) {
      const body = {
        email: email.value,
        password: password.value,
      };

      try {
        const response = await api.post(`/auth/${formMode}`, body);
        login(response.data.accessToken);
      } catch (error) {
        toast.error(error.response.data);
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} className={styles.form} action="">
      <div className={styles.formMode}>
        <div
          onClick={() => setFormMode("login")}
          className={formMode == "login" ? styles.active : ""}
        >
          ENTRAR
        </div>
        <div
          onClick={() => setFormMode("register")}
          className={formMode == "register" ? styles.active : ""}
        >
          CADASTRAR
        </div>
      </div>
      {formInputs.map((input) => {
        return (
          <Input key={input.id} input={input} setInput={setFormInputs}></Input>
        );
      })}
      <button className={`${styles.button}`} type="submit">
        Entrar
      </button>
    </form>
  );
};

export default Form;
