import React from "react";
import styles from "./InputSelect.module.css";

const ITENS = [
  { name: "Item 1", color: "red" },
  { name: "Item 2", color: "green" },
  { name: "Item 3", color: "blue" },
];

const InputSelect = () => {
  const [active, setActive] = React.useState(false);
  const [selected, setSelected] = React.useState(() => ITENS[0]);

  return (
    <div className={`${styles.container} ${active ? styles.active : ""}`}>
      {/* Esta é a lista de itens */}
      <div
        style={{ backgroundColor: selected.color }}
        className={styles.selected}
        onClick={() => setActive(!active)}
      >
        {selected.name}
        <span>
          {active ? (
            <i className="bx bx-chevron-up"></i>
          ) : (
            <i className="bx bx-chevron-down"></i>
          )}
        </span>
      </div>
      <div className={styles.list}>
        {ITENS.filter((item) => item.name !== selected.name).map(
          (item, index) => (
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
          )
        )}
      </div>
    </div>
  );
};

export default InputSelect;
