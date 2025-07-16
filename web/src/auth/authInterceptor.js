const setupInterceptors = (axiosInstance, logoutFunction) => {
  axiosInstance.interceptors.request.use(
    (config) => {
      const accessToken = localStorage.getItem("accessToken");

      if (accessToken) {
        config.headers.Authorization = `Bearer ${accessToken}`;
      }
      return config;
    },
    (error) => Promise.reject(error)
  );

  axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
      if (error.response?.status === 401) {
        // Verificar se authContext existe e tem o método logout
        if (authContext && typeof logoutFunction === "function") {
          logoutFunction();
        }
      }
      return Promise.reject(error);
    }
  );
};
export default setupInterceptors;
