import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_USERS = `${BASE_REST_URL}/users`;

export const loadUsers = () => {
    return axios.get(`${BASE_REST_URL_WITH_USERS}`)
}

export const loadRoles = () => {
    return axios.get(`${BASE_REST_URL_WITH_USERS}/roles`)
}

export const addUser = (user) => {
    return axios.post(`${BASE_REST_URL_WITH_USERS}`, user)
}

export const getCurrentUser = () => {
    return axios.get(`${BASE_REST_URL_WITH_USERS}/current`)
}

export const updateProfile = (profileRequest) => {
    return axios.put(`${BASE_REST_URL_WITH_USERS}/profile`, profileRequest)
}