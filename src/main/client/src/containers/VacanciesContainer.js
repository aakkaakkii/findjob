import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core/styles";
import VacancyShort from "../components/Vaccancy/VacancyShort";
import {loadVacancies} from "../api/vacancyApi";
import VacanciesPage from "../components/Vaccancy/VacanciesPage";


const VacanciesContainer = () => {
    const PAGE_LIMIT = 3;
    const [vacancies, setVacancies] = useState([]);
    const [currPage, setCurrPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        fetchData(0);
    }, []);

    const fetchData = async (page) => {
        const res = await loadVacancies(page, PAGE_LIMIT);
        const data = res.data;
        // setVacancies([...data.content, ...data.content, ...data.content, ...data.content, ...data.content, ...data.content]);
        setVacancies(data.content);
        setCurrPage(data.number);
        setTotalPages(data.totalPages);
    }

    return (
        <VacanciesPage
            vacancies={vacancies}
            fetchData={fetchData}
            currPage={currPage}
            totalPages={totalPages}
        />
    )
};

export default VacanciesContainer;