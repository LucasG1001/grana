import type { Metadata } from 'next';
import './globals.css';

export const metadata: Metadata = {
  title: 'Grana',
  description: 'Criado por Lucas dev',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="pt-BR">
      <body>{children}</body>
    </html>
  );
}
