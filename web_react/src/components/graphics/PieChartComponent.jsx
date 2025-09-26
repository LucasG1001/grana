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
import { renderCustomLabel } from "./renderCustomLabel";

const salesByCategory = [
  { name: "Eletrônicos", value: 4000 },
  { name: "Moda", value: 3000 },
  { name: "Casa", value: 2000 },
  { name: "Beleza", value: 1500 },
  { name: "Livros", value: 1000 },
];

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "#A28EFF"];

const PieChartComponent = () => {
  return (
    <div className={styles.PieChart}>
      <h2 className={styles.title}>Despesas por Categoria</h2>
      <ResponsiveContainer height={300}>
        <PieChart>
          <Pie
            data={salesByCategory}
            dataKey="value"
            nameKey="name"
            cx="40%"
            cy="50%"
            innerRadius={60}
            outerRadius={120}
            fill="#8884d8"
            stroke="none"
            paddingAngle={0}
            label={renderCustomLabel}
            labelLine={false}
          >
            {salesByCategory.map((entry, index) => (
              <Cell
                key={`cell-${index}`}
                fill={COLORS[index % COLORS.length]}
              />
            ))}
          </Pie>
          <Tooltip />
          <Legend layout="vertical" align="right" verticalAlign="middle" />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
};

export default PieChartComponent;
