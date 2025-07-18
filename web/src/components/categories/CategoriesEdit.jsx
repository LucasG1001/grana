import React from "react";
import styles from "./CategoriesEdit.module.css";
import Input from "../Input";
import boxicons from "boxicons";
import { Gallery } from "../gallery/Gallery";
import { COLORS, ICONS } from "../constants";

const FORM_INPUTS = [
  {
    id: "name",
    label: "Nome",
    type: "text",
    value: "",
    errorMessage: "",
  },
];

const CategoriesEdit = () => {
  const [formInputs, setFormInputs] = React.useState(FORM_INPUTS);
  const [galleryOpen, setGalleryOpen] = React.useState(false);
  const [galleryColors, setGalleryColors] = React.useState(false);
  const [icon, setIcon] = React.useState("bx bx-x");
  const [color, setColor] = React.useState("#87998d");

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
            <Input key={input.id} input={input} setInput={setFormInputs} />
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
          </div>
        );
      })}
    </div>
  );
};

export default CategoriesEdit;
