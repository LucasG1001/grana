import React, { useState } from 'react'
import styles from './FilterMonthComponent.module.css'

const FilterMonthComponent = ({ onMonthChange, initialMonth, includeAllMonths = false }) => {
  const [selectedMonth, setSelectedMonth] = useState(initialMonth ?? new Date().getMonth());
  
  const months = [
    ...(includeAllMonths ? [{ value: -1, label: 'Todos os Meses' }] : []),
    { value: 0, label: 'Janeiro' },
    { value: 1, label: 'Fevereiro' },
    { value: 2, label: 'Março' },
    { value: 3, label: 'Abril' },
    { value: 4, label: 'Maio' },
    { value: 5, label: 'Junho' },
    { value: 6, label: 'Julho' },
    { value: 7, label: 'Agosto' },
    { value: 8, label: 'Setembro' },
    { value: 9, label: 'Outubro' },
    { value: 10, label: 'Novembro' },
    { value: 11, label: 'Dezembro' }
  ];

  const onlyMonthsThisYear = months.filter(month => {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const monthDate = new Date(currentYear, month.value);
    return monthDate.getFullYear() === currentYear && month.value <= currentDate.getMonth();
  });

  // Removido o useEffect que causava o loop

  const handleMonthChange = (event) => {
    const newMonth = Number(event.target.value);
    setSelectedMonth(newMonth);
    
    // Chama onMonthChange apenas quando o usuário muda a seleção
    if (onMonthChange) {
      onMonthChange(newMonth);
    }
  };

  return (
    <div className={styles.filterContainer}>
      <label htmlFor="month-select" className={styles.filterLabel}>Mês:</label>
      <select
        id="month-select"
        className={styles.filterSelect}
        value={selectedMonth}
        onChange={handleMonthChange}
      >
        {onlyMonthsThisYear.map((month) => (
          <option key={month.value} value={month.value}>
            {month.label}
          </option>
        ))}
      </select>
    </div>
  )
}

export default FilterMonthComponent