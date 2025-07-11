import axios from "axios";

import setupInterceptors from "../auth/authInterceptor.js";

const api = axios.create({
    baseURL: "http://localhost:3001/",
});

export const setupAxiosInterceptors = (store) => {setupInterceptors(api, store);};

export default api;
