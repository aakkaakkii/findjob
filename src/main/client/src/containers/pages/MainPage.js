import React, {useEffect, useState} from "react";
import Banner from "../../components/Banner/Banner";
import {makeStyles} from "@material-ui/core/styles";
import VacancyShort from "../../components/Vaccancy/VacancyShort";
import {useTranslation} from "react-i18next";

const useStyles = makeStyles((theme) => ({
    root: {
        // display: "flex",
        // justifyContent: "center"
    },
    vacancies: {
        display: "grid",
        gridTemplateColumns: "0.4fr",
        justifyContent: "center",
    },
    vacanciesTitle: {
        fontSize: theme.title1FontSize,
        marginBottom: '20px'
    }
}));

const MainPage = () => {
    const [vacancies, setVacancies] = useState([]);
    const classes = useStyles();
    const { t, i18n } = useTranslation();

    console.log(i18n)

    useEffect(() => {
        setVacancies(vacancyList);
    }, []);

    return (
        <div className={classes.root}>
            <Banner/>
            {t("test")}

            <div className={classes.vacancies}>
                <div className={classes.vacanciesTitle}>Hot Jobs</div>
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

export default MainPage;