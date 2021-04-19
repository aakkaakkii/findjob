import React, {useEffect, useState} from "react";
import HomePage from "../components/Home/HomePage";
import {loadVacancies} from "../api/vacancyApi";

const MainContainer = () => {
    const [vacancies, setVacancies] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const res = await loadVacancies();
        setVacancies(res.data);
    }

    return (
     <HomePage vacancies={vacancies}/>
    )
};

export default MainContainer;