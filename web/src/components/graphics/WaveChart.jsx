import React from "react";
import styles from "./WaveChart.module.css";
import {
  Area,
  AreaChart,
  CartesianGrid,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

const salesByDay = [
  { date: "07/01", value: 2400 },
  { date: "07/02", value: 2400 },
  { date: "07/03", value: 2400 },
  { date: "07/04", value: 2400 },
  { date: "07/05", value: 2400 },
  { date: "07/06", value: 2400 },
  { date: "07/07", value: 2400 },
  { date: "07/08", value: 2400 },
  { date: "07/09", value: 2400 },
  { date: "07/10", value: 2400 },
  { date: "07/11", value: 2400 },
  { date: "07/12", value: 2400 },
  { date: "07/13", value: 2400 },
  { date: "07/14", value: 2400 },
  { date: "07/15", value: 2400 },
  { date: "07/16", value: 1398 },
  { date: "07/17", value: 9800 },
  { date: "07/18", value: 3908 },
  { date: "07/19", value: 4800 },
  { date: "07/20", value: 3800 },
  { date: "07/21", value: 4300 },
  { date: "07/22", value: 4300 },
  { date: "07/23", value: 0 },
  { date: "07/24", value: 0 },
  { date: "07/25", value: 0 },
  { date: "07/26", value: 0 },
  { date: "07/27", value: 0 },
  { date: "07/28", value: 0 },
  { date: "07/29", value: 0 },
  { date: "07/30", value: 0 },
  { date: "07/31", value: 0 },
];

const WaveChart = () => {
  return (
    <div className={styles.waveChart}>
      <h2 className={styles.title}>Gastos por Dia</h2>
      <ResponsiveContainer
        width="100%"
        height={300}
        className={styles.chartContainer}
      >
        <AreaChart
          data={salesByDay}
          margin={{ top: 10, right: 30, left: 0, bottom: 20 }}
          className={styles.chart}
        >
          <XAxis dataKey="date" tick={{ fill: "white", fontSize: 12 }} />
          <YAxis tick={{ fill: "white", fontSize: 12 }} />
          <Tooltip />
          <Area
            type="monotone"
            dataKey="value"
            stroke="#4992FF"
            strokeWidth={2}
            fillOpacity={0.1}
            fill="blue"
          />
        </AreaChart>
      </ResponsiveContainer>
    </div>
  );
};

export default WaveChart;
