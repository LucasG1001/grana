import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      const { status } = error.response;

      if (status === 403) {
        window.location.href = '/login';
      }
    }

    return Promise.reject(error);
  },
);

export default api;
