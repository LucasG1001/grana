"use client";
import React, { useEffect } from "react";
import styles from "./OrdenableTable.module.css";

type OrdenableTableProps<T extends Record<string, unknown>> = {
  data: Array<T>;
  columns: Array<{
    id: keyof T;
    label: string;
    sortDirection: "asc" | "desc";
  }>;
};

function OrdenableTable<T extends Record<string, unknown>>({
  data,
  columns,
}: OrdenableTableProps<T>) {
  const [sortedData, setSortedData] = React.useState<T[]>(data);

  useEffect(() => {
    setSortedData(data);
  }, [data]);

  const handleSort = (
    columnId: keyof T,
    currentSortDirection: "asc" | "desc"
  ) => {
    const newSortDirection = currentSortDirection === "asc" ? "desc" : "asc";

    const sorted = [...data].sort((a, b) => {
      const aValue = a[columnId];
      const bValue = b[columnId];

      if (typeof aValue === "number" && typeof bValue === "number") {
        return newSortDirection === "asc" ? aValue - bValue : bValue - aValue;
      }

      return newSortDirection === "asc"
        ? String(aValue).localeCompare(String(bValue))
        : String(bValue).localeCompare(String(aValue));
    });

    setSortedData(sorted);
    columns.map((column) =>
      column.id === columnId
        ? { ...column, sortDirection: newSortDirection }
        : column
    );
  };

  return (
    <div className={styles.ordenableTable}>
      <table className={styles.table}>
        <thead>
          <tr className={styles.tableHeaderRow}>
            {columns.map((column, index) => (
              <th
                onClick={() => handleSort(column.id, column.sortDirection)}
                key={index}
                style={{ cursor: "pointer" }}
                className={styles.tableHeader}
              >
                <div className={styles.tableHeaderContent}>
                  <span className={styles.tableHeaderLabel}>
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
          {sortedData.map((row, rowIndex) => (
            <tr key={String(rowIndex)}>
              {columns.map((column) => (
                <td key={String(column.id)} className={styles.tableCell}>
                  {String(row[column.id])}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default OrdenableTable;
