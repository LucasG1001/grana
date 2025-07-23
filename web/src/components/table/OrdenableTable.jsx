import React, { useEffect } from "react";
import styles from "./OrdenableTable.module.css";
import boxicons from "boxicons";

const OrdenableTable = ({ title, data, columns, modal, setModal }) => {
  const [sortedData, setSortedData] = React.useState(data);
  const [sortColumn, setSortColumn] = React.useState(columns);

  useEffect(() => {
    setSortedData(data);
    setSortColumn(columns);
  }, [data]);

  const handleSort = (columnId, currentSortDirection) => {
    const newSortDirection = currentSortDirection === "asc" ? "desc" : "asc";

    const sortedData = [...data].sort((a, b) => {
      if (newSortDirection === "asc") {
        return a[columnId] > b[columnId] ? 1 : -1;
      } else {
        return a[columnId] < b[columnId] ? 1 : -1;
      }
    });

    const sorted = columns.map((column) => {
      if (column.id === columnId) {
        return { ...column, sortDirection: newSortDirection };
      }
      return { ...column };
    });

    console.log(sorted);

    setSortColumn(sorted);
    setSortedData(sortedData);
  };

  return (
    <div className={styles.ordenableTable}>
      <div className={styles.tableTitleContainer}>
        <h2 className={styles.tableTitle}>{title}</h2>
        <button onClick={() => setModal(true)} className={styles.addButton}>
          {" "}
          <i className="bx bx-plus"></i> <span>Adicionar</span>
        </button>
      </div>
      <table className={styles.table}>
        <thead>
          <tr className={styles.tableHeaderRow}>
            {sortColumn.map((column, index) => (
              <th
                onClick={() => handleSort(column.id, column.sortDirection)}
                key={index}
                className={styles.tableHeader}
              >
                <div className={styles.tableHeaderContent}>
                  <span className={styles.tableHeaderLabel}>
                    {" "}
                    {column.label}
                  </span>
                  <span className={styles.sortDirection}>
                    {column.sortDirection === "asc" ? "▲" : "▼"}
                  </span>
                </div>
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {sortedData.map((row) => (
            <tr key={row.id}>
              {sortColumn.map((column) => (
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
