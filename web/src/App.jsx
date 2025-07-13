import { AuthProvider, useAuth } from "./auth/AuthContext";
import AppRoutes from "./routes/routes";
import { setupAxiosInterceptors } from "./api/axios";
import { useEffect } from "react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const App = () => {
  const auth = useAuth();

  useEffect(() => {
    if (!auth.loading) {
      setupAxiosInterceptors(auth);
    }
  }, [auth]);

  return (
    <>
      <AppRoutes />
      <ToastContainer
        position="top-right"
        autoClose={5000}
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
