import React, { useState, useEffect } from "react";
import styles from "./Compras.module.css";
import axios from "axios";
import FilterMonthComponent from "../components/FilterMonthComponent";
import BigNumbers from "./BigNumbers";
import AddExpenses from "./AddExpenses";

const Compras = () => {
  const [boxes, setBoxes] = useState([]);
  const [allExpenses, setAllExpenses] = useState([]);
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth());

  const [filteredExpenses, setFilteredExpenses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [formVisible, setFormVisible] = useState(false);


  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const [boxesRes, expensesRes] = await Promise.all([
          axios.get("http://localhost:3001/boxes"),
          axios.get("http://localhost:3001/expenses")
        ]);

        // Fixed data assignment
        setBoxes(boxesRes.data);
        setAllExpenses(expensesRes.data);
        setFilteredExpenses(expensesRes.data);
        setLoading(false);
      } catch (err) {
        console.error("Erro ao buscar dados:", err);
        setError("Falha ao carregar dados. Por favor, tente novamente.");
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    const today = new Date();
    const currentMonth = selectedMonth !== null ? selectedMonth : today.getMonth();

    const filteredExpenses = allExpenses.filter(expense => {
      // Garante que a data seja processada corretamente, independente do fuso horário
      let expenseDate;

      if (typeof expense.date === 'string') {
        const [year, month, day] = expense.date.split('-').map(Number);
        expenseDate = new Date(year, month - 1, day);
      } else {
        expenseDate = new Date(expense.date);
      }

      console.log(`Expense Date: ${expenseDate}, Current Month: ${currentMonth}`);


      const expenseMonth = expenseDate.getMonth();

      return expenseMonth === currentMonth;
    });

    setFilteredExpenses(filteredExpenses, currentMonth, allExpenses);
  }, [allExpenses, selectedMonth]);

  const handleDeleteGasto = async (id) => {
    try {
      await axios.delete(`http://localhost:3001/expenses/${id}`);

      // Atualizar ambos os estados
      const updatedExpenses = allExpenses.filter(expense => expense.id !== id);
      setAllExpenses(updatedExpenses);
      setFilteredExpenses(updatedExpenses.filter(expense => {
        const expenseDate = new Date(expense.data);
        const currentFilter = new Date();
        return expenseDate.getMonth() === currentFilter.getMonth();
      }));

    } catch (err) {
      setError("Não foi possível excluir o gasto. Verifique os dados e tente novamente.");
    }
  };
  const formatarValor = (valor) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor);
  };

  const formatarData = (dataString) => {
    const data = new Date(dataString);
    return new Intl.DateTimeFormat('pt-BR').format(data);
  };

  const getBoxName = (boxId) => {
    const box = boxes.find(b => b.id === boxId);
    return box ? box.name : "Desconhecido";
  };

  // const handleFilterMonth = (month) => {

  //   const filteredExpenses = allExpenses.filter(expense => {
  //     const expenseDate = new Date(expense.date);
  //     return expenseDate.getMonth() === month;
  //   });

  //   setFilteredExpenses(filteredExpenses, month, allExpenses);
  // };



  const formatarValorComDuasCasasDecimais = (valor) => {
    return valor.toFixed(2);
  };





  const currentMonthExpenses = allExpenses.filter(expense => {
    const expenseDate = new Date(expense.date);
    return expenseDate.getMonth() === selectedMonth;
  }).reduce((acc, expense) => acc + expense.value, 0);

  const lastMonthExpenses = allExpenses.filter(expense => {
    const expenseDate = new Date(expense.date);
    return expenseDate.getMonth() === selectedMonth - 1;
  }).reduce((acc, expense) => acc + expense.value, 0);


  let percentageOfIncrease;

  console.log(`Last Month Expenses: ${lastMonthExpenses}, Current Month Expenses: ${currentMonthExpenses}`);


  if (lastMonthExpenses === 0) {
    percentageOfIncrease = 0;
  } else {
    percentageOfIncrease = ((currentMonthExpenses - lastMonthExpenses) / lastMonthExpenses) * 100;
  }

  const dashboardData = [
    { title: "Valor Restante", value: `${formatarValorComDuasCasasDecimais(boxes.reduce((acc, box) => acc + box.limit, 0) - filteredExpenses.reduce((acc, expense) => acc + expense.value, 0), 2)}` },
    { title: "Gastos Totais", value: `${formatarValorComDuasCasasDecimais(currentMonthExpenses)}`, change: `${percentageOfIncrease.toFixed(2)}%` },
    { title: "Limite das caixas", value: `${formatarValorComDuasCasasDecimais(boxes.reduce((acc, box) => acc + box.limit, 0))}` },
  ];


  if (loading) return <div className={styles.loading}>Carregando dados...</div>;
  if (error) return <div className={styles.error}>{error}</div>;

  return (
    <div className={styles.compras}>
      <header className={styles.header}>
        <h1>Gerenciador de Compras</h1>
        <button
          onClick={() => setFormVisible(!formVisible)}
          className={styles.addButton}
        >
          {formVisible ? 'Cancelar' : '+ Novo Gasto'}
        </button>
      </header>

      {formVisible && (
        <AddExpenses boxes={boxes} formVisible={formVisible} setFormVisible={setFormVisible} />
      )}

      {/* Resumo dos caixas */}
      <section className={styles.resumoCaixas}>
        <FilterMonthComponent initialMonth={new Date().getMonth()} onMonthChange={setSelectedMonth} />

        <BigNumbers data={dashboardData} />
        <h1>Caixinhas</h1>
        <div className={styles.caixasGrid}>
          <div className={styles.add} onClick={() => setFormVisible(true)}>
            <h2>➕</h2>
          </div>
          {boxes.map(box => {
            const boxExpenses = filteredExpenses.filter(expense => expense.boxId === box.id);
            const totalGasto = boxExpenses.reduce((acc, expense) => acc + expense.value, 0);
            const percentualUsado = (totalGasto / box.limit) * 100;

            return (
              <div
                key={box.id}
                className={`${styles.caixaCard} ${percentualUsado > 90 ? styles.limiteCritico : ''}`}
              >
                <h3>{box.name}</h3>
                <p className={styles.descricao}>{box.description}</p>
                <div className={styles.progressContainer}>
                  <div
                    className={styles.progressBar}
                    style={{ width: `${Math.min(percentualUsado, 100)}%` }}
                  ></div>
                </div>
                <div className={styles.valores}>
                  <span>Usado: {formatarValor(totalGasto)}</span>
                  <span>Disponível: {formatarValor(box.limit - totalGasto)}</span>
                  <span>Limite: {formatarValor(box.limit)}</span>
                </div>
              </div>
            );
          })}
        </div>
      </section>

      <section className={styles.listaGastos}>
        <h2>Gastos Recentes</h2>
        {filteredExpenses.length === 0 ? (
          <p className={styles.semGastos}>Nenhum gasto registrado.</p>
        ) : (
          <div className={styles.tabelaContainer}>
            <table className={styles.tabela}>
              <thead>
                <tr>
                  <th>Descrição</th>
                  <th>Valor</th>
                  <th>Categoria</th>
                  <th>Local</th>
                  <th>Data</th>
                  <th>Caixa</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                {[...filteredExpenses]
                  .sort((a, b) => new Date(b.date) - new Date(a.date))
                  .map(expense => (
                    <tr key={expense.id}>
                      <td>{expense.description}</td>
                      <td className={styles.valorColuna}>{formatarValor(expense.value)}</td>
                      <td>{expense.category}</td>
                      <td>{expense.local}</td>
                      <td>{expense.date}</td>
                      <td>{getBoxName(expense.boxId)}</td>
                      <td className={styles.actions}>
                        <button onClick={() => handleDeleteGasto(expense.id)}>Excluir</button>
                      </td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        )}
      </section>
    </div>
  );
};

export default Compras;