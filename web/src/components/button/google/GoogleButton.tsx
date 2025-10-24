import React from 'react';
import styles from './GoogleButton.module.css';
import Image from 'next/image';

const GoogleButton = (title: string) => {
  return (
    <button className={styles.googleBtn}>
      <Image
        src="https://www.svgrepo.com/show/355037/google.svg"
        alt="Google logo"
        width={25}
        height={25}
      />
      <span>{title}</span>
    </button>
  );
};

export default GoogleButton;
