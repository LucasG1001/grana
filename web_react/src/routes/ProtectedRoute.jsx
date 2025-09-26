import { Navigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";

import React from "react";

const ProtectedRoute = ({ children, roles }) => {
  const { isAuthenticated, loading } = useAuth();

  if (loading) return <div>Loading...</div>;

  if (!isAuthenticated) return <Navigate to="/login" replace />;

  return children;
};

export default ProtectedRoute;
