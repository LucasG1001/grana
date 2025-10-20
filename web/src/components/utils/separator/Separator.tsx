import React from 'react';
import styles from './Separator.module.css';

const Separator = () => {
  return (
    <div className={styles.separator}>
      <div className={styles.line}></div> <span>ou</span>{' '}
      <div className={styles.line}></div>
    </div>
  );
};

export default Separator;
