import axios from 'axios';
import React from 'react'
import ModalComponent from '../components/modal/ModalComponent';
import styles from './AddExpenses.module.css';

const AddExpenses = ({ boxes, formVisible, setFormVisible }) => {

    const [expense, setExpense] = React.useState({
        description: '',
        value: 0,
        category: '',
        local: '',
        date: new Date().toISOString().split('T')[0], // Formato YYYY-MM-DD
        boxId: 0
    });

    const inputs = [
        { name: 'description', type: 'text', placeholder: 'Descrição' },
        { name: 'value', type: 'number', placeholder: 'Valor' },
        { name: 'category', type: 'text', placeholder: 'Categoria' },
        { name: 'local', type: 'text', placeholder: 'Local' },
        { name: 'date', type: 'date', placeholder: 'Data' },
        { name: 'boxId', type: 'select', placeholder: 'ID do Caixa', options: boxes.map(box => ({ value: box.id, label: box.name })) }
    ];


    const validAllInputs = inputs.every(input => {
        if (input.type === 'select') {
            return expense[input.name] !== '' && expense[input.name] !== 0;
        } else {
            return expense[input.name] !== '';
        }
    }
    );

    const handleSubmit = (event) => {
        event.preventDefault();

        axios.post('http://localhost:3001/expenses', expense)
            .then(response => {
                setExpense({
                    description: '',
                    value: 0,
                    category: '',
                    local: '',
                    date: new Date().toISOString().split('T')[0],
                    boxId: 0
                });
            })
            .catch(error => {
                console.error("Erro ao adicionar gasto:", error);
            });
    }

    return (
        <div>
            <ModalComponent isOpen={formVisible} onClose={() => setFormVisible(false)} title="Adicionar Gasto">
                <form onSubmit={handleSubmit}>

                    {inputs.map(input => (

                        <div key={input.name}>
                            <label htmlFor={input.name}>{input.placeholder}:</label>
                            {input.type === 'select' ? (
                                <select
                                    id={input.name}
                                    name={input.name}
                                    value={expense[input.name]}
                                    onChange={(e) => setExpense({ ...expense, [input.name]: e.target.value })}
                                    required
                                >
                                    <option value="">Selecione uma opção</option>
                                    {input.options.map(option => (
                                        <option key={option.value} value={option.value}>
                                            {option.label}
                                        </option>
                                    ))}
                                </select>
                            ) : (
                                <input
                                    type={input.type}
                                    id={input.name}
                                    name={input.name}
                                    placeholder={input.placeholder}
                                    value={expense[input.name]}
                                    onChange={(e) => setExpense({
                                        ...expense,
                                        [input.name]: input.type === 'number' ? Number(e.target.value) : e.target.value
                                    })}
                                    required
                                />
                            )}
                        </div>
                    ))}
                    <button type="submit">Adicionar Gasto</button>
                </form>
            </ModalComponent>
        </div>
    )
}

export default AddExpenses