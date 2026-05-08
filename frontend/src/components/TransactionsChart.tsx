import { PieChart, Pie, Cell, ResponsiveContainer, Tooltip, Legend } from 'recharts';
import { CATEGORY_COLORS, FIXED_CATEGORIES, isFixedCategory } from '../config/categories';

interface TransactionsChartProps {
  data: Record<string, number>;
  isLoading: boolean;
}

const FALLBACK_COLORS = ['#818cf8', '#34d399', '#f87171', '#fbbf24', '#c084fc', '#60a5fa', '#f472b6', '#a3e635'];

export function TransactionsChart({ data, isLoading }: TransactionsChartProps) {
  const fixedData = FIXED_CATEGORIES.map((name) => ({
    name,
    value: data[name] ?? 0,
  })).filter((item) => item.value > 0);

  const extraData = Object.entries(data)
    .filter(([name, value]) => !isFixedCategory(name) && value > 0)
    .map(([name, value]) => ({ name, value }));

  const chartData = [...fixedData, ...extraData];

  if (isLoading) {
    return (
      <div className="bg-[#12121a] rounded-2xl p-6 shadow-lg border border-white/5 h-80 flex items-center justify-center">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-indigo-400"></div>
      </div>
    );
  }

  if (chartData.length === 0) {
    return (
      <div className="bg-[#12121a] rounded-2xl p-6 shadow-lg border border-white/5 h-80 flex items-center justify-center flex-col text-slate-500">
        <p>Nenhum dado para exibir neste mês.</p>
      </div>
    );
  }

  const formatTooltip = (value: number) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    }).format(value);
  };

  return (
    <div className="bg-[#12121a] rounded-2xl p-6 shadow-lg border border-white/5 h-80 flex flex-col">
      <h3 className="text-slate-100 font-semibold mb-6">Despesas por Categoria</h3>
      <div className="flex-1 w-full relative">
        <ResponsiveContainer width="100%" height="100%">
          <PieChart>
            <Pie
              data={chartData}
              cx="50%"
              cy="50%"
              innerRadius={60}
              outerRadius={80}
              paddingAngle={5}
              dataKey="value"
            >
              {chartData.map((entry, index) => (
                <Cell
                  key={`cell-${index}`}
                  fill={isFixedCategory(entry.name) ? CATEGORY_COLORS[entry.name] : FALLBACK_COLORS[index % FALLBACK_COLORS.length]}
                />
              ))}
            </Pie>
            <Tooltip
              formatter={(value: any) => formatTooltip(value as number)}
              contentStyle={{
                borderRadius: '8px',
                border: '1px solid rgba(255,255,255,0.1)',
                boxShadow: '0 4px 6px -1px rgb(0 0 0 / 0.3)',
                backgroundColor: '#1a1a2e',
                color: '#e2e8f0',
              }}
            />
            <Legend verticalAlign="bottom" height={36} iconType="circle" wrapperStyle={{ fontSize: '12px', color: '#94a3b8' }} />
          </PieChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}
