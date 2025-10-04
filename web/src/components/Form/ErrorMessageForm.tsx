import React from 'react';

type ErrorMessageFormProps = {
  message: string;
};

const ErrorMessageForm = ({ message }: ErrorMessageFormProps) => {
  return (
    <p style={{ color: '#f31', margin: '1rem 0', fontSize: '0.8rem' }}>
      {message}
    </p>
  );
};

export default ErrorMessageForm;
