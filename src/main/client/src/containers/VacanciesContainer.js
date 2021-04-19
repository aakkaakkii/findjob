import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core/styles";
import VacancyShort from "../components/Vaccancy/VacancyShort";
import {loadVacancies} from "../api/vacancyApi";
import VacanciesPage from "../components/Vaccancy/VacanciesPage";


const VacanciesContainer = () => {
    const [vacancies, setVacancies] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const res = await loadVacancies();
        setVacancies(res.data);
    }

    return (
        <VacanciesPage vacancies={vacancies}/>
    )
};

export default VacanciesContainer;