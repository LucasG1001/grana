
import type { Transaction } from '../types';
import { format, parseISO } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import { ArrowDownCircle, ArrowUpCircle, Trash2 } from 'lucide-react';

interface TransactionListProps {
  transactions: Transaction[];
  isLoading: boolean;
  onDelete: (id: number) => Promise<void>;
}

export function TransactionList({ transactions, isLoading, onDelete }: TransactionListProps) {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    }).format(value);
  };

  const formatDate = (dateString: string) => {
    try {
      const date = parseISO(dateString);
      return format(date, "dd 'de' MMM", { locale: ptBR });
    } catch {
      return dateString;
    }
  };

  if (isLoading && transactions.length === 0) {
    return (
      <div className="bg-white rounded-2xl shadow-sm border border-slate-100 overflow-hidden">
        <div className="p-6">
          <div className="animate-pulse flex space-x-4">
            <div className="flex-1 space-y-4 py-1">
              <div className="h-4 bg-slate-200 rounded w-3/4"></div>
              <div className="space-y-2">
                <div className="h-4 bg-slate-200 rounded"></div>
                <div className="h-4 bg-slate-200 rounded w-5/6"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="bg-white rounded-2xl shadow-sm border border-slate-100 overflow-hidden">
      <div className="px-6 py-5 border-b border-slate-100 flex justify-between items-center">
        <h3 className="font-semibold text-slate-900">Transações Recentes</h3>
      </div>
      
      {transactions.length === 0 ? (
        <div className="p-8 text-center text-slate-500">
          Nenhuma transação encontrada neste período.
        </div>
      ) : (
        <ul className="divide-y divide-slate-100">
          {transactions.map((transaction) => (
            <li key={transaction.id} className="p-6 hover:bg-slate-50 transition-colors group">
              <div className="flex items-center justify-between">
                <div className="flex items-center gap-4">
                  <div className={`p-3 rounded-full ${transaction.tipo === 'RECEITA' ? 'bg-emerald-50 text-emerald-600' : 'bg-rose-50 text-rose-600'}`}>
                    {transaction.tipo === 'RECEITA' ? <ArrowUpCircle className="w-6 h-6" /> : <ArrowDownCircle className="w-6 h-6" />}
                  </div>
                  <div>
                    <p className="text-slate-900 font-medium">{transaction.descricao}</p>
                    <div className="flex items-center gap-2 text-sm text-slate-500 mt-1">
                      <span className="bg-slate-100 px-2 py-0.5 rounded text-xs">{transaction.categoria}</span>
                      <span>•</span>
                      <span>{formatDate(transaction.data)}</span>
                    </div>
                  </div>
                </div>
                
                <div className="flex items-center gap-4">
                  <span className={`font-semibold text-lg ${transaction.tipo === 'RECEITA' ? 'text-emerald-600' : 'text-slate-900'}`}>
                    {transaction.tipo === 'RECEITA' ? '+' : '-'} {formatCurrency(transaction.valor)}
                  </span>
                  
                  <div className="flex items-center gap-1 opacity-0 group-hover:opacity-100 transition-opacity">
                    <button 
                      onClick={() => onDelete(transaction.id)}
                      className="p-2 text-slate-400 hover:text-rose-600 hover:bg-rose-50 rounded-lg transition-colors"
                      title="Excluir"
                    >
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </div>
                </div>
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
