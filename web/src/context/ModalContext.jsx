import React, { createContext, useContext } from "react";
import styles from "./ModalContext.module.css";
import "bootstrap-icons/font/bootstrap-icons.css";
const ModalContext = createContext();

export const ModalProvider = ({ children }) => {
  const [modalContent, setModalContent] = React.useState(null);
  const [modalOpen, setModalOpen] = React.useState(false);

  const showModal = (content) => {
    setModalContent(content);
    setModalOpen(true);
  };
  const hideModal = () => {
    setModalContent(null);
    setModalOpen(false);
  };

  return (
    <ModalContext.Provider value={{ showModal, hideModal }}>
      {children}
      {modalOpen && (
        <div className={styles.modalContainer}>
          <div className={styles.modalContent}>
            <div className={styles.modalHeader}>
              <button className={styles.button} onClick={hideModal}>
                <i className="bx  bx-x"></i>
              </button>
            </div>
            {modalContent}
          </div>
        </div>
      )}
    </ModalContext.Provider>
  );
};

export const useModal = () => useContext(ModalContext);
