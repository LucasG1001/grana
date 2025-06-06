import React, { useEffect } from 'react';
import styles from './ModalComponent.module.css';

const ModalComponent = ({
    isOpen,
    onClose,
    title,
    children,
    closeOnOverlayClick = true,
    showCloseButton = true,
    className = ''
}) => {
    useEffect(() => {
        if (isOpen) {
            document.body.style.overflow = 'hidden';
        } else {
            document.body.style.overflow = 'auto';
        }

        return () => {
            document.body.style.overflow = 'auto';
        };
    }, [isOpen]);

    // Close on ESC key press
    useEffect(() => {
        const handleEscKey = (e) => {
            if (e.keyCode === 27 && isOpen) {
                onClose();
            }
        };

        window.addEventListener('keydown', handleEscKey);
        return () => window.removeEventListener('keydown', handleEscKey);
    }, [isOpen, onClose]);

    // If modal is not open, don't render
    if (!isOpen) return null;

    // Handle overlay click
    const handleOverlayClick = (e) => {
        if (closeOnOverlayClick && e.target === e.currentTarget) {
            onClose();
        }
    };

    return (
        <div className={styles.modalOverlay} onClick={handleOverlayClick}>
            <div className={`${styles.modalContainer} ${className}`}>
                <div className={styles.modalHeader}>
                    <h2 className={styles.modalTitle}>{title}</h2>
                    {showCloseButton && (
                        <button className={styles.modalCloseButton} onClick={onClose}>
                            &times;
                        </button>
                    )}
                </div>
                <div className={styles.modalContent}>
                    {children}
                </div>
            </div>
        </div>
    );
};


export default ModalComponent;