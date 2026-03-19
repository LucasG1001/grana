import { ArrowDownCircle, ArrowUpCircle, DollarSign } from 'lucide-react';
import type { SummaryData } from '../types';

interface SummaryCardsProps {
  summary: SummaryData;
  isLoading: boolean;
}

export function SummaryCards({ summary, isLoading }: SummaryCardsProps) {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    }).format(value);
  };

  const skeletonClass = isLoading ? "animate-pulse bg-slate-200 text-transparent rounded" : "";

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
      {/* Saldo Atual */}
      <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 flex flex-col justify-between">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-slate-500 font-medium text-sm">Saldo Atual</h3>
          <div className="p-2 bg-indigo-50 text-indigo-600 rounded-lg">
            <DollarSign className="w-5 h-5" />
          </div>
        </div>
        <div className="flex items-baseline gap-2">
          <span className={`text-3xl font-bold ${isLoading ? skeletonClass : 'text-slate-900'}`}>
            {isLoading ? 'R$ 0.000,00' : formatCurrency(summary.saldo)}
          </span>
        </div>
      </div>

      {/* Receitas */}
      <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 flex flex-col justify-between">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-slate-500 font-medium text-sm">Receitas do Mês</h3>
          <div className="p-2 bg-emerald-50 text-emerald-600 rounded-lg">
            <ArrowUpCircle className="w-5 h-5" />
          </div>
        </div>
        <div className="flex items-baseline gap-2">
          <span className={`text-3xl font-bold ${isLoading ? skeletonClass : 'text-slate-900'}`}>
            {isLoading ? 'R$ 0.000,00' : formatCurrency(summary.totalReceitas)}
          </span>
        </div>
      </div>

      {/* Despesas */}
      <div className="bg-white rounded-2xl p-6 shadow-sm border border-slate-100 flex flex-col justify-between">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-slate-500 font-medium text-sm">Despesas do Mês</h3>
          <div className="p-2 bg-rose-50 text-rose-600 rounded-lg">
            <ArrowDownCircle className="w-5 h-5" />
          </div>
        </div>
        <div className="flex items-baseline gap-2">
          <span className={`text-3xl font-bold ${isLoading ? skeletonClass : 'text-slate-900'}`}>
            {isLoading ? 'R$ 0.000,00' : formatCurrency(summary.totalDespesas)}
          </span>
        </div>
      </div>
    </div>
  );
}
