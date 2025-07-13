import React, { useEffect } from "react";
import styles from "./FormLogin.module.css";
import Input from "./Input";
import { validInput } from "./inputValidator";
import { toast } from "react-toastify";
import api from "../api/axios";
import { useAuth } from "../auth/AuthContext";

const Form = () => {
  const [formMode, setFormMode] = React.useState("login");
  const { login } = useAuth();
  const [email, setEmail] = React.useState({
    value: "",
    errorMessage: "",
    type: "email",
    id: "email",
    label: "Email",
  });

  const [password, setPassword] = React.useState({
    value: "",
    errorMessage: "",
    type: "password",
    id: "password",
    label: "Senha",
  });

  useEffect(() => {
    console.log("formMode mudou para:", formMode);
    setEmail((prev) => ({
      ...prev,
      value: "",
      errorMessage: "",
    }));

    setPassword((prev) => ({
      ...prev,
      value: "",
      errorMessage: "",
    }));
  }, [formMode]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const emailError = validInput(email.id, email.value);
    const passwordError = validInput(password.id, password.value);

    setEmail((prev) => ({
      ...prev,
      errorMessage: emailError || "",
    }));

    setPassword((prev) => ({
      ...prev,
      errorMessage: passwordError || "",
    }));

    const isValid = !emailError && !passwordError;

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
      <Input input={email} setInput={setEmail} />
      <Input input={password} setInput={setPassword} />
      <button className={`${styles.button}`} type="submit">
        Entrar
      </button>
    </form>
  );
};

export default Form;
