import axiosInstance from "../axios/axios.js";

const hostRepository = {
    findAll: async () => {
        return await axiosInstance.get("/host");
    },
    findById: async (id) => {
        return await axiosInstance.get(`/host/${id}`);
    },
    add: async (data) => {
        return await axiosInstance.post("/host/add",data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/host/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/host/delete/${id}`);
    },
};

export default hostRepository;
