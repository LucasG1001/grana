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

  const skeletonClass = isLoading ? "animate-pulse bg-slate-700 text-transparent rounded" : "";

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
      {/* Saldo Atual */}
      <div className="bg-[#12121a] rounded-2xl p-6 shadow-lg border border-white/5 flex flex-col justify-between">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-slate-400 font-medium text-sm">Saldo Atual</h3>
          <div className="p-2 bg-indigo-500/10 text-indigo-400 rounded-lg border border-indigo-500/20">
            <DollarSign className="w-5 h-5" />
          </div>
        </div>
        <div className="flex items-baseline gap-2">
          <span className={`text-3xl font-bold ${isLoading ? skeletonClass : 'text-slate-50'}`}>
            {isLoading ? 'R$ 0.000,00' : formatCurrency(summary.saldo)}
          </span>
        </div>
      </div>

      {/* Receitas */}
      <div className="bg-[#12121a] rounded-2xl p-6 shadow-lg border border-white/5 flex flex-col justify-between">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-slate-400 font-medium text-sm">Receitas do Mês</h3>
          <div className="p-2 bg-emerald-500/10 text-emerald-400 rounded-lg border border-emerald-500/20">
            <ArrowUpCircle className="w-5 h-5" />
          </div>
        </div>
        <div className="flex items-baseline gap-2">
          <span className={`text-3xl font-bold ${isLoading ? skeletonClass : 'text-slate-50'}`}>
            {isLoading ? 'R$ 0.000,00' : formatCurrency(summary.totalReceitas)}
          </span>
        </div>
      </div>

      {/* Despesas */}
      <div className="bg-[#12121a] rounded-2xl p-6 shadow-lg border border-white/5 flex flex-col justify-between">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-slate-400 font-medium text-sm">Despesas do Mês</h3>
          <div className="p-2 bg-rose-500/10 text-rose-400 rounded-lg border border-rose-500/20">
            <ArrowDownCircle className="w-5 h-5" />
          </div>
        </div>
        <div className="flex items-baseline gap-2">
          <span className={`text-3xl font-bold ${isLoading ? skeletonClass : 'text-slate-50'}`}>
            {isLoading ? 'R$ 0.000,00' : formatCurrency(summary.totalDespesas)}
          </span>
        </div>
      </div>
    </div>
  );
}
