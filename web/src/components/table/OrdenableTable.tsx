"use client";
import React, { useEffect } from "react";
import styles from "./OrdenableTable.module.css";
// import boxicons from "boxicons";

type OrdenableTableProps = {
  title: string;
  data: Array<Record<string, any>>;
  columns: Array<{
    id: string;
    label: string;
    sortDirection: "asc" | "desc";
  }>;
};

const OrdenableTable = ({ title, data, columns }: OrdenableTableProps) => {
  const [sortedData, setSortedData] = React.useState(data);
  const [sortColumn, setSortColumn] = React.useState(columns);

  useEffect(() => {
    setSortedData(data);
    setSortColumn(columns);
  }, [data]);

  const handleSort = (
    columnId: string,
    currentSortDirection: "asc" | "desc"
  ) => {
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

    setSortColumn(sorted);
    setSortedData(sortedData);
  };

  return (
    <div className={styles.ordenableTable}>
      <table className={styles.table}>
        <thead>
          <tr className={styles.tableHeaderRow}>
            {sortColumn.map((column, index) => (
              <th
                onClick={() => {
                  if (column.sortDirection) {
                    handleSort(column.id, column.sortDirection);
                  }
                }}
                key={index}
                style={{ cursor: column.sortDirection ? "pointer" : "default" }}
                className={styles.tableHeader}
              >
                <div className={styles.tableHeaderContent}>
                  <span className={styles.tableHeaderLabel}>
                    {column.label}
                  </span>
                  {column.sortDirection && (
                    <span className={styles.sortDirection}>
                      {column.sortDirection === "asc" ? "▲" : "▼"}
                    </span>
                  )}
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
