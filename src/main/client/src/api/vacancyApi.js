import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_VACANCY = `${BASE_REST_URL}/vacancies`;

export const getVacancy = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_VACANCY}/${id}`);
}

export const loadVacancies = () => {
    return axios.get(`${BASE_REST_URL_WITH_VACANCY}`);
}

export const loadOrganisationVacancies = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_VACANCY}/organisation/${id}`);
}

export const loadVacancyTypes = () => {
    return axios.get(`${BASE_REST_URL_WITH_VACANCY}/vacancyTypes`);
}

export const addVacancy = (vacancy) => {
    return axios.post(`${BASE_REST_URL_WITH_VACANCY}`, vacancy);
}

export const updateVacancy = (vacancy, id) => {
    return axios.put(`${BASE_REST_URL_WITH_VACANCY}/${id}`, vacancy);
}

export const adminDeleteVacancy = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_VACANCY}/admin/${id}`);
}

export const deleteVacancy= (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_VACANCY}/${id}`);
}

export const blockVacancy = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_VACANCY}/admin/${id}/block`);
}

export const unblockVacancy = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_VACANCY}/admin/${id}/unblock`);
}
