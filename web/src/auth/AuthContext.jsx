import React, { createContext, useState, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import { toast } from "react-toastify";
import setupInterceptors from "./authInterceptor";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [loading, setLoading] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    setupInterceptors(api, logout);
  }, []);

  const validateToken = async () => {
    try {
      const response = await api.get("/auth/validate-token");
      return response.data;
    } catch (error) {
      logout();
    }
  };

  useEffect(() => {
    const initAuth = async () => {
      const accessToken = localStorage.getItem("accessToken");

      if (!accessToken) {
        setLoading(false);
        return;
      }

      try {
        const response = await validateToken();
        if (response) {
          setIsAuthenticated(true);
        }
      } catch (error) {
        toast.error("Sessão expirada. Por favor, faça login novamente.");
      } finally {
        setLoading(false);
      }
    };

    initAuth();
  }, []);

  const login = async (accessToken) => {
    localStorage.setItem("accessToken", accessToken);

    try {
      const response = await validateToken();
      if (response) {
        setIsAuthenticated(true);
        navigate("/");
        toast.success("Login realizado com sucesso!");
      }
    } catch (error) {
      toast.error("Falha ao validar o token de acesso");
    }
  };

  const logout = () => {
    localStorage.removeItem("accessToken");
    setIsAuthenticated(false);
    navigate("/login");
    toast.info("Você foi desconectado");
  };

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        loading,
        login,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
