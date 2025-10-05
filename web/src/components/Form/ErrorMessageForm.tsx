import React from 'react';

type ErrorMessageFormProps = {
  message: string;
};

const ErrorMessageForm = ({ message }: ErrorMessageFormProps) => {
  return <p style={{ color: '#f31', fontSize: '0.8rem' }}>{message}</p>;
};

export default ErrorMessageForm;
