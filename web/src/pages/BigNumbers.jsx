import React from 'react'
import styles from './BigNumbers.module.css'



const BigNumbers = ({ data = [] }) => {
    return (
        <div className={styles.container}>
            {data.map((item, index) => (
                <div key={index} className={styles.card}>
                    <div className={styles.title}>{item.title}</div>
                    <div className={styles.content}>

                        <div className={`${styles.value} ${item.value < 0 ? styles.negative : ''}`}>{`R$ ${item.value}`}</div>
                        {item.change !== undefined && (
                            <div className={`${styles.change} ${item.change > 0 ? styles.positive : item.change < 0 ? styles.negative : ''}`}>
                                {item.change > 0 ? '+' : 'MoM '}{item.change}%
                            </div>
                        )}
                    </div>
                </div>
            ))}
        </div>
    )
}

export default BigNumbers