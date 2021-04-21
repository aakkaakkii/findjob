import axios from "axios";
import {BASE_REST_URL} from "../utils/AppUtil";

const BASE_REST_URL_WITH_PASSWORD = `${BASE_REST_URL}/password`;

export const changePassword = (passwordModel) => {
    return axios.post(`${BASE_REST_URL_WITH_PASSWORD}/change`, passwordModel);
}
