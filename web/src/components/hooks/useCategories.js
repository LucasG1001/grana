import React from "react";
import api from "../../api/axios";

const useCategories = () => {
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
    const response = await api.put(`/categories/${category.id}`, category);
    setCategories(
      categories.map((c) => (c.id === category.id ? response.data : c))
    );
  };

  const remove = async (category) => {
    await api.delete(`/categories/${category.id}`);
    setCategories(categories.filter((c) => c.id !== category.id));
  };

  return { categories, category, get, getById, add, edit, remove };
};

export default useCategories;
