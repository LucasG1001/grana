const setupInterceptors = (axiosInstance, authContext) => {
    axiosInstance.interceptors.request.use((config) => {
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            config.headers.Authorization = `Bearer ${accessToken}`;
        }
        return config;
    }, (error) => Promise.reject(error));

    axiosInstance.interceptors.response.use((response) => response, (error) => {
        if (error.response.status === 401) {
            authContext.logout();
        }
        return Promise.reject(error);
    });
}

export default setupInterceptors