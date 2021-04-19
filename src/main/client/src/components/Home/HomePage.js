import {makeStyles} from "@material-ui/core/styles";
import React, {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import Banner from "./Banner/Banner";
import VacancyShort from "../Vaccancy/VacancyShort";

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

const HomePage = ({vacancies}) => {
    const classes = useStyles();
    const { t } = useTranslation();


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
                        organisationName={vacancy.organisation.title}
                        organisationId={vacancy.organisation.id}
                        date={vacancy.creationTime}
                        title={vacancy.title}/>)}
            </div>
        </div>
    )
};

export default HomePage;