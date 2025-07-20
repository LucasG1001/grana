import React from "react";
import api from "../api/axios";

const CreateContext = React.createContext({});

export const CategoryProvider = ({ children }) => {
  const [categories, setCategories] = React.useState([]);
  const [category, setCategory] = React.useState({});

  const get = async () => {
    const response = await api.get("/categories");
    setCategories(response.data);
  };

  const getById = async (id) => {
    const response = await api.get(`/categories/${id}`);
    setCategory(response.data);
  };

  const add = async (category) => {
    const response = await api.post("/categories", category);
    setCategories([...categories, response.data]);
  };

  const edit = async (category) => {
    const { id, ...rest } = category;
    const response = await api.put(`/categories/${id}`, rest);
    setCategories(
      categories.map((c) => (c.id === category.id ? response.data : c))
    );
  };

  const remove = async (id) => {
    await api.delete(`/categories/${id}`);
    setCategories(categories.filter((c) => c.id !== id));
  };

  return (
    <CreateContext.Provider
      value={{ categories, category, get, getById, add, edit, remove }}
    >
      {children}
    </CreateContext.Provider>
  );
};

export const useCategory = () => React.useContext(CreateContext);
