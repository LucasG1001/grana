import React from "react";
import styles from "./OrdenableTable.module.css";

const OrdenableTable = ({ title, data, columns }) => {
  return (
    <div className={styles.ordenableTable}>
      <h2 className={styles.tableTitle}>{title}</h2>
      <table className={styles.table}>
        <thead>
          <tr className={styles.tableHeaderRow}>
            {columns.map((column, index) => (
              <th key={index} className={styles.tableHeader}>
                {column.label}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((row) => (
            <tr key={row.id}>
              {columns.map((column) => (
                <td key={column.id} className={styles.tableCell}>
                  {row[column.id]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OrdenableTable;
