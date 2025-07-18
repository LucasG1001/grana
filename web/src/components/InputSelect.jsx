import React from "react";
import styles from "./InputSelect.module.css";
import Modal from "./Modal";
import Input from "./Input";
import CategoriesEdit from "./categories/CategoriesEdit";
import Crud from "./crud";

const InputSelect = ({ itens }) => {
  const [active, setActive] = React.useState(false);
  const [selected, setSelected] = React.useState(() => itens[0]);
  const [modalEdit, setModalEdit] = React.useState(true);

  const editItem = () => {
    setModalEdit(true);
  };

  return (
    <div className={`${styles.container} ${active ? styles.active : ""}`}>
      <Modal
        title="Editar item"
        isOpen={modalEdit}
        onClose={() => setModalEdit(false)}
      >
        <CategoriesEdit />
      </Modal>
      <div className={styles.selected}>
        <div className={styles.selectedItem} onClick={() => setActive(!active)}>
          <span className={styles.name}>
            <span>
              <i
                style={{ backgroundColor: selected.color }}
                className={`${selected.icon} ${styles.icon}`}
              ></i>
            </span>
            <span>{selected.name}</span>
          </span>
          <div className={styles.arrow}>
            {active ? (
              <i
                className={`${"bx bx-chevron-up"} ${styles.icon} ${
                  styles.arrow
                }`}
              ></i>
            ) : (
              <i
                className={`${"bx bx-chevron-down"} ${styles.icon} ${
                  styles.arrow
                }`}
              ></i>
            )}
          </div>
          <div className={styles.list}>
            {itens
              .filter((item) => item.name !== selected.name)
              .map((item, index) => (
                <div
                  onClick={() => {
                    setSelected(item);
                    setActive(false);
                  }}
                  className={styles.item}
                  key={index}
                >
                  {item.name}
                </div>
              ))}
          </div>
        </div>
        <Crud editItem={editItem} />
      </div>
    </div>
  );
};

export default InputSelect;
