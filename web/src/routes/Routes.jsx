import { Route, Routes } from "react-router-dom";
import Login from "../pages/Login";
import Transactions from "../pages/Transactions";
import ProtectedRoute from "./ProtectedRoute";
import { CategoryProvider } from "../context/useCategory";

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route
        path="/"
        element={
          <ProtectedRoute>
            <CategoryProvider>
              <Transactions />
            </CategoryProvider>
          </ProtectedRoute>
        }
      />
      <Route path="*" element={<Login />} />
    </Routes>
  );
};

export default AppRoutes;
