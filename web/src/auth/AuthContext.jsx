import React, { createContext, useState, useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import { toast } from "react-toastify";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [loading, setLoading] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const navigate = useNavigate();

  const validateToken = async () => {
    try {
      const response = await api.get("/auth/validate-token");

      return response.data;
    } catch (error) {
      toast.error("invalid token");
      logout();
    }
  };

  useEffect(() => {
    const initAuth = async () => {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        try {
          const response = await validateToken();
          if (response) {
            setIsAuthenticated(true);
            navigate("/");
          }
        } catch (error) {
          logout();
        }
      }
      setLoading(false);
    };

    initAuth();
  }, []);

  const login = async (accessToken) => {
    localStorage.setItem("accessToken", accessToken);
    const response = await validateToken();

    if (response) {
      setIsAuthenticated(true);
      navigate("/");
    }
  };

  const logout = () => {
    navigate("/login");
    setIsAuthenticated(false);
    localStorage.removeItem("accessToken");
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
