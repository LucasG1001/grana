import axios from "axios";

import setupInterceptors from "../auth/authInterceptor.js";

const api = axios.create({
  baseURL: "http://localhost:8080/api",
});

export const setupAxiosInterceptors = (store) => {
  setupInterceptors(api, store);
};

export default api;
