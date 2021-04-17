import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_EXPERIENCES = `${BASE_REST_URL}/experiences`;

export const getExperience = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_EXPERIENCES}/${id}`);
}

export const loadUserExperiences = (userId) => {
    return axios.get(`${BASE_REST_URL_WITH_EXPERIENCES}/user/${userId}`);
}

export const loadCurrentUserExperiences = () => {
    return axios.get(`${BASE_REST_URL_WITH_EXPERIENCES}/currentUser`);
}

export const addExperience = (experience) => {
    return axios.post(`${BASE_REST_URL_WITH_EXPERIENCES}`, experience);
}

export const updateExperience = (experience, id) => {
    return axios.put(`${BASE_REST_URL_WITH_EXPERIENCES}/${id}`, experience);
}

export const adminDeleteExperience = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_EXPERIENCES}/admin/${id}`);
}

export const deleteExperience = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_EXPERIENCES}/${id}`);
}
