import { useState } from 'react';
import { Bot, Send, Sparkles } from 'lucide-react';
import clsx from 'clsx';

interface NLPInputBarProps {
  onSubmit: (text: string) => Promise<void>;
  isLoading: boolean;
}

export function NLPInputBar({ onSubmit, isLoading }: NLPInputBarProps) {
  const [input, setInput] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!input.trim() || isLoading) return;
    
    await onSubmit(input);
    setInput('');
  };

  return (
    <div className="bg-white rounded-2xl p-1 shadow-sm border border-slate-200 mb-8 max-w-3xl mx-auto focus-within:ring-2 focus-within:ring-indigo-500 focus-within:border-transparent transition-all">
      <form onSubmit={handleSubmit} className="relative flex items-center w-full">
        <div className="pl-4 pr-2 py-3 flex items-center justify-center text-indigo-500">
          <Bot className="w-6 h-6" />
        </div>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ex: Paguei 40 reais de uber hoje..."
          className="flex-1 appearance-none bg-transparent py-3 pr-4 text-slate-900 placeholder-slate-400 focus:outline-none sm:text-lg"
          disabled={isLoading}
        />
        <button
          type="submit"
          disabled={!input.trim() || isLoading}
          className={clsx(
            "mr-1 p-3 rounded-xl flex items-center justify-center transition-all",
            input.trim() && !isLoading
              ? "bg-indigo-600 text-white hover:bg-indigo-700 shadow-md"
              : "bg-slate-100 text-slate-400 cursor-not-allowed"
          )}
        >
          {isLoading ? (
            <Sparkles className="w-5 h-5 animate-pulse" />
          ) : (
            <Send className="w-5 h-5" />
          )}
        </button>
      </form>
      <div className="px-4 pb-2 text-xs text-slate-400 text-center">
        A inteligência artificial irá extrair o valor, categoria e data automaticamente.
      </div>
    </div>
  );
}
