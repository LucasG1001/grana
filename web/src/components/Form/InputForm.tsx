import React from 'react';

type InputFormProps = {
  name: string;
  id?: string;
  placeholder?: string;
  type?: string;
  error?: string;
};

const InputForm = (props: InputFormProps) => {
  return (
    <>
      <input
        type={props.type}
        name={props.name}
        id={props.id}
        placeholder={props.placeholder}
      />
      {props.error && (
        <p style={{ color: '#f31', margin: '1rem 0' }}>{props.error}</p>
      )}
    </>
  );
};

export default InputForm;
