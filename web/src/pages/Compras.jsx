import React, { useState, useEffect } from "react";
import styles from "./Compras.module.css";
import axios from "axios";

const Compras = () => {
  const [caixas, setCaixas] = useState([]);
  const [gastos, setGastos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [formVisible, setFormVisible] = useState(false);
  
  const [novoGasto, setNovoGasto] = useState({
    descricao: "",
    valor: "",
    categoria: "",
    local: "",
    data: new Date().toISOString().split("T")[0],
    caixaId: ""
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const [caixasRes, gastosRes] = await Promise.all([
          axios.get("http://localhost:3001/caixas"),
          axios.get("http://localhost:3001/gastos")
        ]);
        
        setCaixas(caixasRes.data);
        setGastos(gastosRes.data);
        setLoading(false);
      } catch (err) {
        console.error("Erro ao buscar dados:", err);
        setError("Falha ao carregar dados. Por favor, tente novamente.");
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    
    // Converte o valor para número se for o campo "valor" ou "caixaId"
    const processedValue = (name === "valor") 
      ? parseFloat(value) 
      : name === "caixaId" ? parseInt(value, 10) : value;
    
    setNovoGasto({
      ...novoGasto,
      [name]: processedValue
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const response = await axios.post("http://localhost:3001/gastos", novoGasto);
      
      // Adiciona o novo gasto à lista de gastos
      setGastos([...gastos, response.data]);
      
      // Limpa o formulário
      setNovoGasto({
        descricao: "",
        valor: "",
        categoria: "",
        local: "",
        data: new Date().toISOString().split("T")[0],
        caixaId: ""
      });
      
      // Oculta o formulário
      setFormVisible(false);
    } catch (err) {
      console.error("Erro ao adicionar gasto:", err);
      setError("Não foi possível adicionar o gasto. Verifique os dados e tente novamente.");
    }
  };

  // Função para formatar valor em reais
  const formatarValor = (valor) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor);
  };

  // Função para formatar data
  const formatarData = (dataString) => {
    const data = new Date(dataString);
    return new Intl.DateTimeFormat('pt-BR').format(data);
  };

  // Encontra o nome da caixa pelo ID
  const getNomeCaixa = (caixaId) => {
    const caixa = caixas.find(c => c.id === caixaId);
    return caixa ? caixa.nome : "Desconhecido";
  };

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

      {/* Formulário para adicionar gastos */}
      {formVisible && (
        <form className={styles.form} onSubmit={handleSubmit}>
          <h2>Adicionar Novo Gasto</h2>
          
          <div className={styles.formGroup}>
            <label htmlFor="descricao">Descrição</label>
            <input
              type="text"
              id="descricao"
              name="descricao"
              value={novoGasto.descricao}
              onChange={handleInputChange}
              required
            />
          </div>

          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label htmlFor="valor">Valor (R$)</label>
              <input
                type="number"
                id="valor"
                name="valor"
                step="0.01"
                value={novoGasto.valor}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className={styles.formGroup}>
              <label htmlFor="caixaId">Caixa</label>
              <select
                id="caixaId"
                name="caixaId"
                value={novoGasto.caixaId}
                onChange={handleInputChange}
                required
              >
                <option value="">Selecione uma caixa</option>
                {caixas.map(caixa => (
                  <option key={caixa.id} value={caixa.id}>
                    {caixa.nome} - Limite: {formatarValor(caixa.limite)}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label htmlFor="categoria">Categoria</label>
              <input
                type="text"
                id="categoria"
                name="categoria"
                value={novoGasto.categoria}
                onChange={handleInputChange}
                required
              />
            </div>

            <div className={styles.formGroup}>
              <label htmlFor="data">Data</label>
              <input
                type="date"
                id="data"
                name="data"
                value={novoGasto.data}
                onChange={handleInputChange}
                required
              />
            </div>
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="local">Local</label>
            <input
              type="text"
              id="local"
              name="local"
              value={novoGasto.local}
              onChange={handleInputChange}
              required
            />
          </div>

          <button type="submit" className={styles.submitButton}>
            Salvar Gasto
          </button>
        </form>
      )}

      {/* Resumo dos caixas */}
      <section className={styles.resumoCaixas}>
        <h2>Caixas Disponíveis</h2>
        <div className={styles.caixasGrid}>
          {caixas.map(caixa => {
            // Calcula o total gasto para esta caixa
            const gastosNaCaixa = gastos.filter(gasto => gasto.caixaId === caixa.id);
            const totalGasto = gastosNaCaixa.reduce((acc, gasto) => acc + gasto.valor, 0);
            const percentualUsado = (totalGasto / caixa.limite) * 100;
            
            return (
              <div 
                key={caixa.id} 
                className={`${styles.caixaCard} ${percentualUsado > 90 ? styles.limiteCritico : ''}`}
              >
                <h3>{caixa.nome}</h3>
                <p className={styles.descricao}>{caixa.descricao}</p>
                <div className={styles.progressContainer}>
                  <div 
                    className={styles.progressBar} 
                    style={{ width: `${Math.min(percentualUsado, 100)}%` }}
                  ></div>
                </div>
                <div className={styles.valores}>
                  <span>Usado: {formatarValor(totalGasto)}</span>
                  <span>Limite: {formatarValor(caixa.limite)}</span>
                </div>
              </div>
            );
          })}
        </div>
      </section>

      {/* Lista de gastos */}
      <section className={styles.listaGastos}>
        <h2>Gastos Recentes</h2>
        {gastos.length === 0 ? (
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
                </tr>
              </thead>
              <tbody>
                {[...gastos]
                  .sort((a, b) => new Date(b.data) - new Date(a.data))
                  .map(gasto => (
                  <tr key={gasto.id}>
                    <td>{gasto.descricao}</td>
                    <td className={styles.valorColuna}>{formatarValor(gasto.valor)}</td>
                    <td>{gasto.categoria}</td>
                    <td>{gasto.local}</td>
                    <td>{formatarData(gasto.data)}</td>
                    <td>{getNomeCaixa(gasto.caixaId)}</td>
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