import React from 'react';

type ErrorMessageFormProps = {
  message: string;
};

const ErrorMessageForm = ({ message }: ErrorMessageFormProps) => {
  return <p style={{ color: '#f31', margin: '1rem 0' }}>{message}</p>;
};

export default ErrorMessageForm;
