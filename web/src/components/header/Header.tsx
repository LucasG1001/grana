import Link from 'next/link';
import React from 'react';
import styles from './Header.module.css';

const Header = () => {
  return (
    <ul className={styles.container}>
      <li className={styles.item}>
        <Link href="/">Home</Link>
      </li>
      <li className={styles.item}>
        <Link href="/login">Login</Link>
      </li>
      <li className={styles.item}>
        <Link href="/register">Register</Link>
      </li>
      <li className={styles.item}>
        <Link href="/transactions">Transactions</Link>
      </li>
    </ul>
  );
};

export default Header;
