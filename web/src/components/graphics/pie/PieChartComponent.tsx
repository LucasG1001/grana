'use client';
import React from 'react';
import styles from './PieChart.module.css';
import {
  Cell,
  Legend,
  Pie,
  PieChart,
  ResponsiveContainer,
  Tooltip,
} from 'recharts';

// Tipo genérico (espera um array de objetos)
export type PieChartComponentProps<
  T extends { id: number; name: string; value: number },
> = {
  data: T[];
  handleSelected: (id: number | null) => void;
};

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#A28EFF'];

function PieChartComponent<
  T extends { id: number; name: string; value: number },
>({ data, handleSelected }: PieChartComponentProps<T>) {
  const [activeIndex, setActiveIndex] = React.useState<number | null>(null);

  const sortedData = React.useMemo(
    () => [...data].sort((a, b) => b.value - a.value),
    [data],
  );

  function handleClick(_, index: number) {
    if (activeIndex === index) {
      setActiveIndex(null);
      handleSelected(null);
    } else {
      setActiveIndex(index);
      handleSelected(sortedData[index].id);
    }
  }

  const CustomLegend = (props: any) => {
    const { payload } = props;
    const sortedPayload = [...payload].sort(
      (a, b) => b.payload.value - a.payload.value,
    );

    return (
      <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
        {sortedPayload.map((entry: any, index: number) => (
          <li
            key={`item-${index}`}
            style={{
              marginBottom: 4,
              display: 'grid',
              alignItems: 'center',
              gridTemplateColumns: '80px 20px 1fr',
              gap: 8,
            }}
          >
            <span style={{ textAlign: 'left', color: '#666', fontSize: 14 }}>
              R$ {entry.payload.value}
            </span>
            <span
              style={{
                display: 'inline-block',
                width: 17,
                height: 17,
                backgroundColor: entry.color,
                borderRadius: 3,
              }}
            />
            <span style={{ color: '#666', fontSize: 14 }}>{entry.value}</span>
          </li>
        ))}
      </ul>
    );
  };

  return (
    <div className={styles.PieChart}>
      <ResponsiveContainer height={300}>
        <PieChart width={400} height={300}>
          <Pie
            data={sortedData}
            dataKey="value"
            nameKey="name"
            cx="40%"
            cy="50%"
            innerRadius={60}
            outerRadius={120}
            stroke="none"
            paddingAngle={0}
            labelLine={false}
            onClick={handleClick}
            style={{ outline: 'none', cursor: 'pointer' }}
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
                    cursor: 'pointer',
                    transition: 'all 0.3s',
                    transform:
                      activeIndex === index ? 'scale(1.03)' : 'scale(1)',
                    transformOrigin: 'center',
                    outline: 'none',
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
            content={<CustomLegend />}
          />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
}

export default PieChartComponent;
