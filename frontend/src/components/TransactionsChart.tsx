import { PieChart, Pie, Cell, ResponsiveContainer, Tooltip, Legend } from 'recharts';

interface TransactionsChartProps {
  data: Record<string, number>;
  isLoading: boolean;
}

const COLORS = ['#818cf8', '#34d399', '#f87171', '#fbbf24', '#c084fc', '#60a5fa', '#f472b6', '#a3e635'];

export function TransactionsChart({ data, isLoading }: TransactionsChartProps) {
  const chartData = Object.entries(data).map(([name, value]) => ({
    name,
    value,
  })).sort((a, b) => b.value - a.value);

  if (isLoading) {
    return (
      <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 h-80 flex items-center justify-center">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-indigo-600"></div>
      </div>
    );
  }

  if (chartData.length === 0) {
    return (
      <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 h-80 flex items-center justify-center flex-col text-slate-400">
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
    <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 h-80 flex flex-col">
      <h3 className="text-slate-900 font-semibold mb-6">Despesas por Categoria</h3>
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
              {chartData.map((_entry, index) => (
                <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip 
              formatter={(value: any) => formatTooltip(value as number)}
              contentStyle={{ borderRadius: '8px', border: 'none', boxShadow: '0 4px 6px -1px rgb(0 0 0 / 0.1)' }}
            />
            <Legend verticalAlign="bottom" height={36} iconType="circle" wrapperStyle={{ fontSize: '12px' }} />
          </PieChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}
