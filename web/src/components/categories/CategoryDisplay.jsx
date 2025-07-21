import React from "react";
import styles from "./CategoryDisplay.module.css";
import boxicons from "boxicons";

const CategoryDisplay = ({ categoryName, categoryColor, categoryIcon }) => {
  return (
    <div className={styles.categoryDisplay}>
      <span
        className={` ${styles.categoryColor}`}
        style={{ background: categoryColor }}
      >
        <i className={`${categoryIcon} ${styles.icon}`}></i>
      </span>
      <span className={styles.categoryName}>{categoryName}</span>
    </div>
  );
};

export default CategoryDisplay;
