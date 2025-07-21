import React, { useEffect } from "react";
import styles from "./DropdownSelector.module.css";
import Crud from "../Crud";

const DropdownSelector = ({ options, input, setInput }) => {
  const [isOpen, setIsOpen] = React.useState(false);
  const [selectedOption, setSelectedOption] = React.useState({});
  const [modal, setModal] = React.useState(true);
  const [formMode, setFormMode] = React.useState("");

  const handleSelect = (option) => {
    setSelectedOption(option);
    setInput((prev) =>
      prev.map((item) =>
        item.id === input.id
          ? { ...item, value: option.name, selected: option }
          : item
      )
    );
    setIsOpen(false);
  };

  useEffect(() => {
    setSelectedOption(options[options.length - 1]);
  }, [options]);

  useEffect(() => {
    const defaultOption = options.find((option) => option.name === "Outros");
    setSelectedOption(defaultOption || {});
    handleSelect(defaultOption || options[0]);
  }, []);

  return (
    <div className={`${styles.dropdownWrapper} ${isOpen ? styles.open : ""}`}>
      {input.crudComponent &&
        formMode &&
        input.crudComponent(selectedOption, formMode, modal, setModal)}

      <div className={styles.dropdownHeader}>
        <div className={styles.selector} onClick={(e) => setIsOpen(!isOpen)}>
          <span className={styles.selectedContent}>
            <span>
              <i
                style={{ backgroundColor: selectedOption.color }}
                className={`${selectedOption.icon} ${styles.icon} ${styles.selectedIcon}`}
              ></i>
            </span>
            <span>{selectedOption.name}</span>
          </span>

          <div className={styles.chevronIcon}>
            <i
              className={`bx ${isOpen ? "bx-chevron-up" : "bx-chevron-down"} ${
                styles.icon
              }`}
            ></i>
          </div>
          {isOpen && (
            <div className={styles.optionList}>
              {options.map((option) => (
                <div
                  key={option.name}
                  className={styles.optionItem}
                  onClick={(e) => handleSelect(option, e)}
                >
                  <i
                    style={{ backgroundColor: option.color }}
                    className={`${option.icon} ${styles.icon} ${styles.optionIcon}`}
                  ></i>{" "}
                  <span>{option.name}</span>
                </div>
              ))}
            </div>
          )}
        </div>

        <Crud
          formMode={formMode}
          setFormMode={setFormMode}
          setModal={setModal}
        />
      </div>
    </div>
  );
};

export default DropdownSelector;
