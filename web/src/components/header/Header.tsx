import Link from 'next/link';
import React from 'react';
import styles from './Header.module.css';

const Header = () => {
  return (
    <ul className={styles.container}>
      <li>
        <Link href="/">Home</Link>
      </li>
      <li>
        <Link href="/login">Login</Link>
      </li>
      <li>
        <Link href="/register">Register</Link>
      </li>
      <li>
        <Link href="/transactions">Transactions</Link>
      </li>
    </ul>
  );
};

export default Header;
