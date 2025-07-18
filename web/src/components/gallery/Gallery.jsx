import React from "react";
import styles from "./Gallery.module.css";

export const Gallery = ({ setIcon, icons }) => {
  return (
    <div className={styles.container}>
      {icons.map((icon) => (
        <i
          onClick={() => setIcon(icon)}
          style={{ backgroundColor: icon }}
          className={`${icon} ${styles.icon}`}
        ></i>
      ))}
    </div>
  );
};
