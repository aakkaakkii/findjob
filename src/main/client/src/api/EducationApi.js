import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_EDUCATIONS = `${BASE_REST_URL}/educations`;

export const getEducation = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_EDUCATIONS}/${id}`);
}

export const loadUserEducations = () => {
    return axios.get(`${BASE_REST_URL_WITH_EDUCATIONS}/user/${id}`);
}

export const addEducation = (education) => {
    return axios.post(`${BASE_REST_URL_WITH_EDUCATIONS}`, education);
}

export const updateEducation = (education) => {
    return axios.put(`${BASE_REST_URL_WITH_EDUCATIONS}`, education);
}

export const adminDeleteEducation = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_EDUCATIONS}/admin/${id}`);
}

export const deleteEducation = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_EDUCATIONS}/${id}`);
}
