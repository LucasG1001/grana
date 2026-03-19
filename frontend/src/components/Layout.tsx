import type { ReactNode } from 'react';
import { LayoutDashboard, Receipt, Wallet } from 'lucide-react';

export function Layout({ children }: { children: ReactNode }) {
  return (
    <div className="min-h-screen bg-slate-50 text-slate-900">
      <header className="bg-white border-b border-slate-200 sticky top-0 z-10">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 justify-between items-center">
            <div className="flex items-center gap-2">
              <div className="p-2 bg-indigo-600 rounded-lg">
                <Wallet className="h-6 w-6 text-white" />
              </div>
              <span className="text-xl font-bold bg-gradient-to-r from-indigo-600 to-violet-600 bg-clip-text text-transparent">
                Grana.AI
              </span>
            </div>
            
            <nav className="hidden md:flex space-x-8">
              <span className="text-indigo-600 font-medium flex items-center gap-2 px-3 py-2 rounded-md bg-indigo-50">
                <LayoutDashboard className="h-4 w-4" />
                Dashboard
              </span>
              <span className="text-slate-500 hover:text-slate-900 font-medium flex items-center gap-2 px-3 py-2 rounded-md hover:bg-slate-50 transition-colors cursor-pointer">
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
