import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_ORGANISATIONS = `${BASE_REST_URL}/organisations`;

export const loadOrganisations = () => {
    return axios.get(`${BASE_REST_URL_WITH_ORGANISATIONS}`);
}

export const getOrganisation = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_ORGANISATIONS}/${id}`);
}

export const loadCurrentUserOrganisations = () => {
    return axios.get(`${BASE_REST_URL_WITH_ORGANISATIONS}/currentUser`);
}

export const addOrganisation = (organisation) => {
    return axios.post(`${BASE_REST_URL_WITH_ORGANISATIONS}`, organisation);
}

export const updateOrganisation = (organisation, id) => {
    return axios.put(`${BASE_REST_URL_WITH_ORGANISATIONS}/${id}`, organisation);
}

export const adminDeleteOrganisation = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_ORGANISATIONS}/admin/${id}`);
}

export const deleteOrganisation = (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_ORGANISATIONS}/${id}`);
}

export const blockOrganisation = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_ORGANISATIONS}/admin/${id}/block`);
}

export const unblockOrganisation = (id) => {
    return axios.put(`${BASE_REST_URL_WITH_ORGANISATIONS}/admin/${id}/unblock`);
}
