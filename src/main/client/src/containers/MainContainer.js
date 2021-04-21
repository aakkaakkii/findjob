import React, {useEffect, useState} from "react";
import HomePage from "../components/Home/HomePage";
import {loadVacancies} from "../api/vacancyApi";

const MainContainer = () => {
    const [vacancies, setVacancies] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const res = await loadVacancies(0, 10);
        setVacancies(res.data.content);
    }

    return (
     <HomePage vacancies={vacancies}/>
    )
};

export default MainContainer;