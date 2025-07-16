import { AuthProvider, useAuth } from "./auth/AuthContext";
import AppRoutes from "./routes/routes";
import { use, useEffect, useState } from "react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "boxicons/css/boxicons.min.css";

const App = () => {
  const auth = useAuth();

  return (
    <>
      <AppRoutes />
      <ToastContainer
        position="top-right"
        autoClose={1500}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        pauseOnHover
        draggable
      />
    </>
  );
};

export default App;
