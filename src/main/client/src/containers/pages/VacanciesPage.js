import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core/styles";
import VacancyShort from "../../components/Vaccancy/VacancyShort";

const useStyles = makeStyles((theme) => ({
    root: {},
    vacancies: {
        display: "grid",
        gridTemplateColumns: "0.4fr",

        justifyContent: "center"
    }
}));


const VacanciesPage = () => {
    const [vacancies, setVacancies] = useState([]);
    const classes = useStyles();

    useEffect(() => {
        setVacancies(vacancyList);
    }, []);

    return (
        <div>
            <div className={classes.vacancies}>
                <div> Hot Jobs</div>
                {vacancies.map(vacancy =>
                    <VacancyShort
                        key={vacancy.id}
                        id={vacancy.id}
                        companyName={vacancy.name}
                        date={vacancy.date}
                        title={vacancy.title}/>)}
            </div>
        </div>
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


export default VacanciesPage;