import { AuthProvider, useAuth } from "./auth/AuthContext";
import AppRoutes from "./routes/routes";
import { setupAxiosInterceptors } from "./api/axios";
import { useEffect } from "react";

const App = () => {
  const auth = useAuth();

  useEffect(() => {
    if (!auth.loading) {
      setupAxiosInterceptors(auth);
    }
  }, [auth]);

  return <AppRoutes />;
};

export default App;
