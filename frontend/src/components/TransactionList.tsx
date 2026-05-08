import type { Transaction } from '../types';
import { format, parseISO } from 'date-fns';
import { ptBR } from 'date-fns/locale';
import { ArrowDownCircle, ArrowUpCircle, CreditCard, Trash2 } from 'lucide-react';

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
      <div className="bg-[#12121a] rounded-2xl shadow-lg border border-white/5 overflow-hidden">
        <div className="p-6">
          <div className="animate-pulse flex space-x-4">
            <div className="flex-1 space-y-4 py-1">
              <div className="h-4 bg-slate-700 rounded w-3/4"></div>
              <div className="space-y-2">
                <div className="h-4 bg-slate-700 rounded"></div>
                <div className="h-4 bg-slate-700 rounded w-5/6"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="bg-[#12121a] rounded-2xl shadow-lg border border-white/5 overflow-hidden">
      <div className="px-6 py-5 border-b border-white/5 flex justify-between items-center">
        <h3 className="font-semibold text-slate-100">Transações Recentes</h3>
      </div>

      {transactions.length === 0 ? (
        <div className="p-8 text-center text-slate-500">
          Nenhuma transação encontrada neste período.
        </div>
      ) : (
        <ul className="divide-y divide-white/5">
          {transactions.map((transaction) => {
            const isInstallment = transaction.formaPagamento === 'CREDITO'
              && transaction.totalParcelas > 1
              && transaction.parcelaAtual;

            return (
              <li key={transaction.id} className="p-6 hover:bg-white/[0.02] transition-colors group">
                <div className="flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between">
                  <div className="flex items-center gap-4 min-w-0">
                    <div className={`shrink-0 p-3 rounded-full ${transaction.tipo === 'RECEITA' ? 'bg-emerald-500/10 text-emerald-400' : 'bg-rose-500/10 text-rose-400'}`}>
                      {transaction.tipo === 'RECEITA' ? <ArrowUpCircle className="w-6 h-6" /> : <ArrowDownCircle className="w-6 h-6" />}
                    </div>
                    <div className="min-w-0">
                      <p className="text-slate-100 font-medium truncate">{transaction.descricao || 'Sem descrição'}</p>
                      <div className="flex flex-wrap items-center gap-2 text-sm text-slate-500 mt-1">
                        <span className="bg-white/5 px-2 py-0.5 rounded text-xs text-slate-400">{transaction.categoria}</span>
                        <span aria-hidden="true">•</span>
                        <span>{formatDate(transaction.data)}</span>
                        {isInstallment && (
                          <>
                            <span aria-hidden="true">•</span>
                            <span className="inline-flex items-center gap-1 bg-indigo-500/10 px-2 py-0.5 rounded text-xs text-indigo-300 border border-indigo-500/20">
                              <CreditCard className="w-3 h-3" />
                              {transaction.parcelaAtual}/{transaction.totalParcelas}
                            </span>
                          </>
                        )}
                      </div>
                    </div>
                  </div>

                  <div className="flex items-center justify-between gap-4 sm:justify-end">
                    <span className={`font-semibold text-lg ${transaction.tipo === 'RECEITA' ? 'text-emerald-400' : 'text-slate-100'}`}>
                      {transaction.tipo === 'RECEITA' ? '+' : '-'} {formatCurrency(transaction.valor)}
                    </span>

                    <div className="flex items-center gap-1 opacity-100 sm:opacity-0 sm:group-hover:opacity-100 transition-opacity">
                      <button
                        onClick={() => onDelete(transaction.id)}
                        className="p-2 text-slate-500 hover:text-rose-400 hover:bg-rose-500/10 rounded-lg transition-colors"
                        title="Excluir"
                      >
                        <Trash2 className="w-4 h-4" />
                      </button>
                    </div>
                  </div>
                </div>
              </li>
            );
          })}
        </ul>
      )}
    </div>
  );
}
