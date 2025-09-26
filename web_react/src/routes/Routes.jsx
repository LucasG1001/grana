import { Route, Routes } from "react-router-dom";
import Login from "../pages/Login";
import Transactions from "../pages/Transactions";
import ProtectedRoute from "./ProtectedRoute";

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/login" element={<FormLogin />} />
      <Route
        path="/"
        element={
          <ProtectedRoute>
            <Transactions />
          </ProtectedRoute>
        }
      />
      <Route path="*" element={<Login />} />
    </Routes>
  );
};

export default AppRoutes;
