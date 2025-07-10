import { Route, Routes } from "react-router-dom";
import Login from "../pages/Login";
import Transactions from "../pages/Transactions";
import ProtectedRoute from "./ProtectedRoute";

const AppRoutes = () => {
    console.log("routes");
    return (
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route
                path="/"
                element={
                    <ProtectedRoute>
                        <Transactions />
                    </ProtectedRoute>
                }
            />
        </Routes>
    );
};

export default AppRoutes;
