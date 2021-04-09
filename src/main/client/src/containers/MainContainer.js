import React, {useEffect, useState} from "react";
import HomePage from "../components/Home/HomePage";

const MainContainer = () => {
    const [vacancies, setVacancies] = useState([]);

    useEffect(() => {
        setVacancies(vacancyList);
    }, []);

    return (
     <HomePage vacancies={vacancies}/>
    )
};

const vacancyList = [
    {
        id: 1,
        title: "Frontend Development",
        date: "2019",
        name: "FACEBOOK, INC."
    },
    {
        id: 2,
        title: "Open Source Interactive Developer",
        date: "2019",
        name: "FACEBOOK, INC."
    },
    {
        id: 3,
        title: "Full Stack Developer",
        date: "2019",
        name: "FACEBOOK, INC."
    }
]

export default MainContainer;