import type { ReactNode } from 'react';
import { LayoutDashboard, Receipt, Wallet } from 'lucide-react';

export function Layout({ children }: { children: ReactNode }) {
  return (
    <div className="min-h-screen bg-[#0a0a0f] text-slate-100">
      <header className="bg-[#0d0d14]/80 backdrop-blur-xl border-b border-white/5 sticky top-0 z-10">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 justify-between items-center">
            <div className="flex items-center gap-2">
              <div className="p-2 bg-indigo-600 rounded-lg shadow-lg shadow-indigo-600/20">
                <Wallet className="h-6 w-6 text-white" />
              </div>
              <span className="text-xl font-bold bg-gradient-to-r from-indigo-400 to-violet-400 bg-clip-text text-transparent">
                Grana.AI
              </span>
            </div>
            
            <nav className="hidden md:flex space-x-8">
              <span className="text-indigo-400 font-medium flex items-center gap-2 px-3 py-2 rounded-md bg-indigo-500/10 border border-indigo-500/20">
                <LayoutDashboard className="h-4 w-4" />
                Dashboard
              </span>
              <span className="text-slate-400 hover:text-slate-100 font-medium flex items-center gap-2 px-3 py-2 rounded-md hover:bg-white/5 transition-colors cursor-pointer">
                <Receipt className="h-4 w-4" />
                Transações
              </span>
            </nav>
          </div>
        </div>
      </header>

      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {children}
      </main>
    </div>
  );
}
