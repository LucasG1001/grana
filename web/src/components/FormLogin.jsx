import React, { useEffect } from "react";
import styles from "./FormLogin.module.css";
import Input from "./Input";
import { validInput } from "./inputValidator";

const Form = () => {
  const [formMode, setFormMode] = React.useState("login");
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
    setEmail({
      value: "",
      errorMessage: "",
      type: "email",
      id: "email",
      label: "Email",
    });
    setPassword({
      value: "",
      errorMessage: "",
      type: "password",
      id: "password",
      label: "Senha",
    });
  }, [formMode]);

  const handleSubmit = (e) => {
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
      console.log("Formulário válido, enviando dados...");
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
          onClick={() => setFormMode("newUser")}
          className={formMode == "newUser" ? styles.active : ""}
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
