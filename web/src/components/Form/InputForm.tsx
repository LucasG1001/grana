"use client";
import React from "react";

type InputFormProps = {
  name: string;
  id?: string;
  placeholder?: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  type?: string;
};

const InputForm = ({
  name,
  id,
  placeholder,
  value,
  onChange,
  type = "text",
}: InputFormProps) => {
  return (
    <input
      type={type}
      name={name}
      id={id}
      placeholder={placeholder}
      value={value}
      onChange={onChange}
    />
  );
};

export default InputForm;
