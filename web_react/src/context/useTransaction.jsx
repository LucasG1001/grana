import React from "react";
import api from "../api/axios";

const CreateContext = React.createContext({});

export const TransactionProvider = ({ children }) => {
  const [transactions, setTransactions] = React.useState([]);
  const [transaction, setTransaction] = React.useState({});

  const get = async () => {
    const response = await api.get("/transactions");
    setTransactions(response.data);
  };

  const getById = async (id) => {
    const response = await api.get(`/transactions/${id}`);
    setTransactions(response.data);
  };

  const getByMonth = async (month) => {
    const year = new Date().getFullYear();
    const startDate = new Date(year, month, 1).toISOString().split("T")[0];
    const endDate = new Date(year, month + 1, 0).toISOString().split("T")[0];

    const response = await api.get("/transactions/date-between", {
      params: { startDate, endDate },
    });
    setTransactions(response.data);
  };

  const add = async (transaction) => {
    const response = await api.post("/transactions", transaction);
    setTransactions([...transactions, response.data]);
  };

  const edit = async (transaction) => {
    const { id, ...rest } = transaction;
    const response = await api.put(`/transactions/${id}`, rest);
    setTransactions(
      transactions.map((c) => (c.id === transaction.id ? response.data : c))
    );
  };

  const remove = async (id) => {
    await api.delete(`/transactions/${id}`);
    setTransactions(transactions.filter((c) => c.id !== id));
  };

  return (
    <CreateContext.Provider
      value={{
        transactions,
        transaction,
        get,
        getById,
        getByMonth,
        add,
        edit,
        remove,
      }}
    >
      {children}
    </CreateContext.Provider>
  );
};

export const useTransaction = () => React.useContext(CreateContext);
