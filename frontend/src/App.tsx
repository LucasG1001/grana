import { useState, useEffect, useCallback } from 'react';
import { api } from './config/api';
import type { SummaryData, Transaction } from './types';
import { Layout } from './components/Layout';
import { SummaryCards } from './components/SummaryCards';
import { TransactionsChart } from './components/TransactionsChart';
import { NLPInputBar } from './components/NLPInputBar';
import { TransactionList } from './components/TransactionList';

function App() {
  const [summary, setSummary] = useState<SummaryData>({
    totalReceitas: 0,
    totalDespesas: 0,
    saldo: 0,
    porCategoria: {},
  });
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const fetchData = useCallback(async () => {
    try {
      setIsLoading(true);
      const [summaryRes, transRes] = await Promise.all([
        api.get<SummaryData>('/api/transactions/summary'),
        api.get<Transaction[]>('/api/transactions'),
      ]);
      setSummary(summaryRes.data);
      setTransactions(transRes.data);
    } catch (error) {
      console.error('Failed to fetch data', error);
      alert('Erro ao carregar dados. Verifique se o servidor está rodando.');
    } finally {
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  const handleNLPSubmit = async (text: string) => {
    try {
      setIsSubmitting(true);
      await api.post('/api/transactions/natural', { textoLivre: text });
      await fetchData(); // Refresh data after successful insertion
    } catch (error) {
      console.error('Failed to process natural language', error);
      alert('Não foi possível processar a transação. Verifique se a chave de API do Gemini é válida.');
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleDelete = async (id: number) => {
    if (!window.confirm('Tem certeza que deseja excluir esta transação?')) return;
    
    try {
      setIsLoading(true);
      await api.delete(`/api/transactions/${id}`);
      await fetchData();
    } catch (error) {
      console.error('Failed to delete transaction', error);
      alert('Erro ao excluir transação.');
      setIsLoading(false); // In case fetchData isn't reached
    }
  };

  return (
    <Layout>
      <NLPInputBar onSubmit={handleNLPSubmit} isLoading={isSubmitting} />
      
      <SummaryCards summary={summary} isLoading={isLoading} />
      
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <div className="lg:col-span-2">
          <TransactionList 
            transactions={transactions} 
            isLoading={isLoading} 
            onDelete={handleDelete} 
          />
        </div>
        
        <div className="lg:col-span-1">
          <TransactionsChart data={summary.porCategoria} isLoading={isLoading} />
        </div>
      </div>
    </Layout>
  );
}

export default App;
