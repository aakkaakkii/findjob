import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_CV = `${BASE_REST_URL}/cvs`;

export const getCV = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_CV}/${id}`);
}

export const loadCV = () => {
    return axios.get(`${BASE_REST_URL_WITH_CV}`);
}

export const loadUserCV = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_CV}/user/${id}`);
}

export const addCV = (cv) => {
    return axios.post(`${BASE_REST_URL_WITH_CV}`, cv);
}

export const updateCV = (cv) => {
    return axios.put(`${BASE_REST_URL_WITH_CV}`, cv);
}

export const adminDeleteCV = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_CV}/admin/${id}`);
}

export const deleteCV = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_CV}/${id}`);
}

export const blockCV = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_CV}/admin/${id}/block`);
}

export const unblockCV = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_CV}/admin/${id}/unblock`);
}


export const disableCV = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_CV}/${id}/disable`);
}

export const enableCV = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_CV}/${id}/enable`);
}
