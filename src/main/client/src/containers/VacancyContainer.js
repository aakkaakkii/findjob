import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {getVacancy} from "../api/vacancyApi";
import VacancyPage from "../components/Vaccancy/VacancyPage";

const VacancyContainer = () => {
    const [vacancy, setVacancy] = useState({
        id: "",
        title: "",
        description: "",
        salary: "",
        vacancyType: "",
        creationTime: null,
        professionTags: [],
        organisation: {id: "", title: ""}
    });
    const { id } = useParams();


    useEffect(() => {
        fetchData();
    },[]);

    const fetchData = async () => {
        const res = await getVacancy(id);
        setVacancy(res.data);
    }

    return (
        <VacancyPage vacancy={vacancy}/>
    )
};

export default VacancyContainer;