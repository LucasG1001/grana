import React, { useEffect } from "react";
import Modal from "../Modal";
import CategoriesEdit from "./CategoriesEdit";
import ButtonsChoise from "../buttons/ButtonsChoise";
import { useCategory } from "../../context/useCategory";

const CategoryCrud = ({ input, formMode, modal, setModal }) => {
  const [title, setTitle] = React.useState("");
  const { remove } = useCategory();

  useEffect(() => {
    if (formMode === "edit") {
      setTitle("Editar Categoria");
    } else if (formMode === "delete") {
      setTitle(
        <span>
          Você está <span style={{ color: "red" }}>DELETANDO</span> a categoria{" "}
          {input.name}
        </span>
      );
    } else {
      setTitle("Nova Categoria");
    }
  }, [modal]);

  return (
    <Modal title={title} isOpen={modal} onClose={() => setModal(false)}>
      {(formMode === "edit" || formMode === "new") && (
        <CategoriesEdit
          category={formMode === "new" ? {} : input}
          formMode={formMode}
          setModal={setModal}
        />
      )}
      {formMode === "delete" && (
        <ButtonsChoise
          handleSubmit={() => {
            remove(input.id);
            setModal(false);
          }}
          action="Deletar"
          setModal={setModal}
        />
      )}
    </Modal>
  );
};

export default CategoryCrud;
