import React from "react";
import { LegendPayload } from "recharts";
import { Data } from "./types";

const CustomLegend = (data: Data[]) => {
  if (!data) return null;

  console.log(data);

  const sortedPayload = [...data].sort((a, b) => b.value - a.value);

  return (
    <ul style={{ listStyle: "none", padding: 0, margin: 0 }}>
      {sortedPayload.map((entry, index) => (
        <li
          key={`item-${index}`}
          style={{
            marginBottom: 4,
            display: "grid",
            alignItems: "center",
            gridTemplateColumns: "80px 20px 1fr",
            gap: 8,
          }}
        >
          <span style={{ textAlign: "left", color: "#666", fontSize: 14 }}>
            R$ {entry.value}
          </span>
          <span
            style={{
              display: "inline-block",
              width: 17,
              height: 17,
              backgroundColor: entry.color,
              borderRadius: 3,
            }}
          />
          <span style={{ color: "#666", fontSize: 14 }}>{entry.value}</span>
        </li>
      ))}
    </ul>
  );
};

export default CustomLegend;
