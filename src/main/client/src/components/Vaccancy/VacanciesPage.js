import VacancyShort from "./VacancyShort";
import React, {useRef} from "react";
import {makeStyles} from "@material-ui/core/styles";
import Pagination from "../Pagination";


const useStyles = makeStyles((theme) => ({
    root: {},
    vacancies: {
        display: "grid",
        gridTemplateColumns: "0.4fr",

        justifyContent: "center"
    }
}));


const VacanciesPage = ({vacancies, fetchData, currPage, totalPages}) => {
    const classes = useStyles();
    const headRef = useRef();

    const pageChangeCallback = () => {
        headRef.current.scrollIntoView();
    }

    return (
        <div>
            <div className={classes.vacancies}>
                <div ref={headRef}> Hot Jobs</div>
                {vacancies.map(vacancy =>
                    <VacancyShort
                        key={vacancy.id}
                        id={vacancy.id}
                        organisationName={vacancy.organisation.title}
                        organisationId={vacancy.organisation.id}
                        date={vacancy.creationTime}
                        title={vacancy.title}/>)}
                        <Pagination
                            fetchData={fetchData}
                            currPage={currPage}
                            totalPages={totalPages}
                            pageChangeCallback={pageChangeCallback}
                        />
            </div>
        </div>
    )
}

export default VacanciesPage;