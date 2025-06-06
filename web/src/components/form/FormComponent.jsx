import React from 'react'
import styles from './FormComponent.module.css'

const FormComponent = ({ onSubmit, className, children }) => {
    const handleSubmit = (event) => {
        event.preventDefault()

        if (onSubmit) {
            onSubmit(event)
        }
    }

    return (
        <form onSubmit={handleSubmit} className={`${styles.formComponent} ${className || ''}`}>
            {children}
        </form>
    )
}

export default FormComponent