# Grana.AI - Controle Financeiro Pessoal

Aplicação web completa de controle financeiro pessoal com suporte a inserção de transações via linguagem natural usando a API do Gemini. 

O projeto foi construído usando **Clean Architecture** no Backend (Spring Boot 3 + Java 21) e uma interface moderna no Frontend (React + Vite + Tailwind CSS v4).

## Estrutura do Projeto

- `backend`: API RESTful baseada em Spring Boot com PostgreSQL.
- `frontend`: Aplicação Web SPA (Single Page Application) em React e TypeScript.

## Pré-requisitos

1. **Java 21**
2. **Node.js 20+**
3. **Maven**
4. **PostgreSQL** (opcional para rodar localmente caso utilize Docker ou outro servidor, senão configure localmente)

## Configuração do Backend

1. Entre na pasta `backend`:
   ```bash
   cd backend
   ```
2. Crie um arquivo `.env` na raiz da pasta `backend` baseando-se no `.env.example`:
   ```env
   DB_USER=postgres
   DB_PASSWORD=sua_senha
   GEMINI_API_KEY=sua_chave_do_gemini
   PORT=8080
   ```
3. Rode a aplicação com o Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```

## Configuração do Frontend

1. Entre na pasta `frontend`:
   ```bash
   cd frontend
   ```
2. Crie um arquivo `.env` baseando-se no `.env.example`:
   ```env
   VITE_API_URL=http://localhost:8080
   ```
3. Instale as dependências:
   ```bash
   npm install
   ```
4. Inicialize o servidor de desenvolvimento:
   ```bash
   npm run dev
   ```

## Funcionalidades Principais

- **Linguagem Natural:** Através de um único campo, você pode digitar "Comprei um lanche por 25 reais hoje no cartão" e a IA classificará, extrairá o valor e a data automaticamente.
- **Inserção Manual:** Os endpoints tradicionais também existem para CRUD manual.
- **Dashboard e Resumo:** Integração com Gráficos Recharts e listagem das transações do mês com saldo.

## Testes

Os testes integração foram implementados com **H2 in-memory database** no backend para garantir que as rotas e regras de negócio da infraestrutura estejam funcionando isoladamente.

```bash
cd backend
./mvnw clean test
```

## Tecnologias Utilizadas

- **Backend:** Java 21, Spring Boot 3, Spring Data JPA, PostgreSQL, Mockito (Testes).
- **Frontend:** React 19, Vite, TypeScript, Tailwind CSS v4, Axios, date-fns, Recharts, Lucide React.
- **Integração IA:** API REST Oficial do Google Gemini.
