import Link from 'next/link';
import React from 'react';

const Header = () => {
  return (
    <ul className="header">
      <div className="auth">
        <li>
          <Link href="/">Home</Link>
        </li>
        <li>
          <Link href="/login">Login</Link>
        </li>
        <li>
          <Link href="/register">Register</Link>
        </li>
      </div>
    </ul>
  );
};

export default Header;
