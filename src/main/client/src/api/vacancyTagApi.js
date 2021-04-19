import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_PROFESSION_TAGS = `${BASE_REST_URL}/professionTags`;

export const getProfessionTag = (id) => {
    return axios.get(`${BASE_REST_URL_WITH_PROFESSION_TAGS}/${id}`);
}

export const loadProfessionTags = () => {
    return axios.get(`${BASE_REST_URL_WITH_PROFESSION_TAGS}`);
}

export const addProfessionTag = (professionTag) => {
    return axios.post(`${BASE_REST_URL_WITH_PROFESSION_TAGS}`, professionTag);
}

export const deleteProfessionTag= (id) => {
    return axios.delete(`${BASE_REST_URL_WITH_PROFESSION_TAGS}/${id}`);
}
