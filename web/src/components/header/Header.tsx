import Link from "next/link";
import React from "react";

const Header = () => {
  return (
    <ul className="header" style={{ display: "flex", gap: "20px" }}>
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
