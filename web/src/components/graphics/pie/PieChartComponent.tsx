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

type CategoryData = {
  id: number;
  name: string;
  value: number;
};

type PieChartComponentProps = {
  data?: CategoryData[];
  setSelectedCategoryId?: (id: number | null) => void;
};

const salesByCategory: CategoryData[] = [
  { id: 1, name: 'Eletrônicos', value: 4000 },
  { id: 2, name: 'Moda', value: 3000 },
  { id: 3, name: 'Casa', value: 2000 },
  { id: 4, name: 'Beleza', value: 1500 },
  { id: 5, name: 'Livros', value: 1000 },
];

const sortedData = [...salesByCategory].sort((a, b) => b.value - a.value);

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#A28EFF'];

const PieChartComponent: React.FC<PieChartComponentProps> = ({
  data = sortedData,
  setSelectedCategoryId,
}) => {
  const [activeIndex, setActiveIndex] = React.useState<number | null>(null);

  function handleClick(data: any, index: number) {
    if (activeIndex === index) {
      setActiveIndex(null);
      setSelectedCategoryId(null);
    } else {
      setActiveIndex(index);
      setSelectedCategoryId(data.id);
    }
  }
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
            fill="#8884d8"
            stroke="none"
            paddingAngle={0}
            labelLine={false}
            onClick={handleClick}
            style={{ outline: 'none', cursor: 'pointer' }}
          >
            {sortedData.map((entry, index) => {
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
                  stroke="none" // impede o contorno ao clicar
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
          <Legend layout="vertical" align="right" verticalAlign="middle" />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
};

export default PieChartComponent;
