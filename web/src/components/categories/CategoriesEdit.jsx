import React, { useEffect } from "react";
import styles from "./CategoriesEdit.module.css";
import Input from "../Input";
import boxicons from "boxicons";
import { Gallery } from "../gallery/Gallery";
import { COLORS, ICONS } from "../constants";
import { useCategory } from "../../context/useCategory";
import ButtonsChoise from "../buttons/ButtonsChoise";

const FORM_INPUTS = [
  {
    id: "category",
    label: "Nome",
    type: "text",
    value: "",
    errorMessage: "",
  },
];

const CategoriesEdit = ({ category, formMode, setModal }) => {
  const [formInputs, setFormInputs] = React.useState(FORM_INPUTS);
  const [galleryOpen, setGalleryOpen] = React.useState(false);
  const [galleryColors, setGalleryColors] = React.useState(false);
  const [icon, setIcon] = React.useState(category.icon || "bx bx-x");
  const [color, setColor] = React.useState(category.color || "#87998d");
  const { add, edit } = useCategory();

  const updateInputs = (inputs, id, changes) => {
    return inputs.map((item) => {
      if (item.id === id) {
        return {
          ...item,
          ...changes,
        };
      }
      return item;
    });
  };

  useEffect(() => {
    formMode === "edit" &&
      setFormInputs((prev) =>
        updateInputs(prev, "category", { value: category.name })
      );
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    if (formMode === "edit") {
      edit(category.id, {
        name: formInputs[0].value,
        icon: icon,
        color: color,
      });
    } else {
      add({ name: formInputs[0].value, icon: icon, color: color });
    }

    setModal(false);
  };

  return (
    <div className={styles.container}>
      {galleryOpen && <Gallery setIcon={setIcon} icons={ICONS} />}
      {galleryColors && <Gallery setIcon={setColor} icons={COLORS} />}

      {formInputs.map((input) => {
        return (
          <div className={styles.inputGroup} key={input.id}>
            <div
              style={{ backgroundColor: color }}
              className={`${styles.icon} ${styles.iconSelected}`}
            >
              <i className={`${icon} `}></i>
            </div>
            <Input input={input} setInput={setFormInputs} />
            <div className={styles.action}>
              <i
                onClick={() => {
                  setGalleryOpen(false);
                  setGalleryColors(!galleryColors);
                }}
                className={`${"bx bx-palette"} ${styles.icon} `}
              ></i>
              <i
                onClick={() => {
                  setGalleryColors(false);
                  setGalleryOpen(!galleryOpen);
                }}
                className={`${"bx bx-smile"} ${styles.icon} `}
              ></i>
            </div>
            <div style={{ gridColumn: "1 / -1" }}>
              <ButtonsChoise
                setModal={setModal}
                handleSubmit={handleSubmit}
                action="Salvar"
              />
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default CategoriesEdit;
