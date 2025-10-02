import React from 'react';

type ErrorFormProps = {
  message: string;
};

const ErrorForm = ({ message }: ErrorFormProps) => {
  return <p>{message}</p>;
};

export default ErrorForm;
