import React from "react";
import styles from "./FormLogin.module.css";
import Input from "./Input";

const Form = () => {
  const [button, setButton] = React.useState(false);
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

  const handleSubmit = (e) => {
    e.preventDefault();

    if (
      email.value &&
      password.value &&
      !email.errorMessage &&
      !password.errorMessage
    ) {
      setButton(true);
    }
  };

  return (
    <form onSubmit={handleSubmit} className={styles.form} action="">
      <h2>Entrar</h2>
      <Input input={email} setInput={setEmail} />
      <Input input={password} setInput={setPassword} />
      <button
        className={`${styles.button} ${!button && styles.disabled}`}
        disabled={!button}
        type="submit"
      >
        Entrar
      </button>
    </form>
  );
};

export default Form;
