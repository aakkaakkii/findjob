import VacancyShort from "./VacancyShort";
import React from "react";
import {makeStyles} from "@material-ui/core/styles";


const useStyles = makeStyles((theme) => ({
    root: {},
    vacancies: {
        display: "grid",
        gridTemplateColumns: "0.4fr",

        justifyContent: "center"
    }
}));


const VacanciesPage = ({vacancy}) => {
    const classes = useStyles();

    return (
        <div>
            <div className={classes.vacancies}>
                    <VacancyShort
                        key={vacancy.id}
                        id={vacancy.id}
                        organisationName={vacancy.organisation.title}
                        organisationId={vacancy.organisation.id}
                        date={vacancy.creationTime}
                        title={vacancy.title}/>
            </div>
        </div>
    )
}

export default VacanciesPage;