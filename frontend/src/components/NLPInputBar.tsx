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
    <div className="bg-[#12121a] rounded-2xl p-1 shadow-lg border border-white/5 mb-8 max-w-3xl mx-auto focus-within:ring-2 focus-within:ring-indigo-500/50 focus-within:border-indigo-500/30 transition-all">
      <form onSubmit={handleSubmit} className="relative flex items-center w-full">
        <div className="pl-4 pr-2 py-3 flex items-center justify-center text-indigo-400">
          <Bot className="w-6 h-6" />
        </div>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ex: Comprei celular de 2400 no crédito em 12x..."
          className="flex-1 appearance-none bg-transparent py-3 pr-4 text-slate-100 placeholder-slate-500 focus:outline-none sm:text-lg"
          disabled={isLoading}
        />
        <button
          type="submit"
          disabled={!input.trim() || isLoading}
          className={clsx(
            'mr-1 p-3 rounded-xl flex items-center justify-center transition-all',
            input.trim() && !isLoading
              ? 'bg-indigo-600 text-white hover:bg-indigo-500 shadow-md shadow-indigo-600/20'
              : 'bg-white/5 text-slate-500 cursor-not-allowed',
          )}
        >
          {isLoading ? (
            <Sparkles className="w-5 h-5 animate-pulse" />
          ) : (
            <Send className="w-5 h-5" />
          )}
        </button>
      </form>
      <div className="px-4 pb-2 text-xs text-slate-500 text-center">
        A inteligência artificial irá extrair valor, categoria, data e parcelas automaticamente.
      </div>
    </div>
  );
}
