

import axiosInstance from "../axios/axios.js";

const accommodationRepository = {
    findAll: async () => {
        return await axiosInstance.get("/accommodation");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/accommodation/${id}`);
    },
    add: async (data) => {
        return await axiosInstance.post("/accommodation/add", data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/accommodation/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/accommodation/delete/${id}`);
    },
};

export default accommodationRepository;
