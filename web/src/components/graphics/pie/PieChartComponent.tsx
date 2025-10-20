"use client";
import React from "react";
import styles from "./PieChart.module.css";
import {
  Cell,
  Legend,
  Pie,
  PieChart,
  ResponsiveContainer,
  Tooltip,
} from "recharts";
import CustomLegend from "./CustomLegend";
import { Data, PieChartComponentProps } from "./types";

const COLORS = [
  "#0088FE",
  "#00C49F",
  "#FFBB28",
  "#FF8042",
  "#A28EFF",
  "#FF6699",
  "#33CCFF",
  "#FF9933",
];

function PieChartComponent({ data, handleSelected }: PieChartComponentProps) {
  const [activeIndex, setActiveIndex] = React.useState<number | null>(null);

  const sortedData = React.useMemo(
    () => [...data].sort((a, b) => b.totalAmount - a.totalAmount),
    [data]
  );

  function handleClick(data: Data, index: number) {
    if (activeIndex === index) {
      setActiveIndex(null);
      handleSelected(null);
    } else {
      setActiveIndex(index);
      handleSelected(data.id);
    }
  }

  return (
    <div className={styles.PieChart}>
      <ResponsiveContainer height={300}>
        <PieChart width={400} height={300}>
          <Pie
            data={sortedData}
            dataKey="totalAmount"
            nameKey="name"
            cx="40%"
            cy="50%"
            innerRadius={60}
            outerRadius={120}
            stroke="none"
            paddingAngle={0}
            labelLine={false}
            onClick={handleClick}
            style={{ outline: "none", cursor: "pointer" }}
          >
            {sortedData.map((_, index) => {
              let fill = COLORS[index % COLORS.length];
              if (activeIndex !== null) {
                const isActive = index === activeIndex;
                const fillColor = COLORS[index % COLORS.length];
                fill = isActive ? fillColor : `${fillColor}80`;
              }

              return (
                <Cell
                  key={`cell-${index}`}
                  fill={fill}
                  style={{
                    cursor: "pointer",
                    transition: "all 0.3s",
                    transform:
                      activeIndex === index ? "scale(1.03)" : "scale(1)",
                    transformOrigin: "center",
                    outline: "none",
                  }}
                />
              );
            })}
          </Pie>
          <Tooltip />
          <Legend
            layout="vertical"
            align="right"
            verticalAlign="middle"
            content={<CustomLegend payload={data} />}
          />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
}

export default PieChartComponent;
