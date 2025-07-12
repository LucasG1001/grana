import React from "react";
import styles from "./Login.module.css";
import Input from "../components/Input";
import FormLogin from "../components/FormLogin";

const Login = () => {
  return (
    <div className={styles.containerLogin}>
      <FormLogin />
    </div>
  );
};

export default Login;
