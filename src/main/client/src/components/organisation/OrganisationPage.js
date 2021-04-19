import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";
import {Link} from "react-router-dom";


const useStyles = makeStyles((theme) => ({
    root: {},
    vacancies: {
        display: "grid",
        gridTemplateColumns: "0.4fr",

        justifyContent: "center"
    }
}));


const OrganisationPage = ({organisation}) => {
    const classes = useStyles();

    return (
        <Box>
            <div>{organisation.title}</div>
            <div>{organisation.description}</div>
            <div>{organisation.address}</div>
            <div>{organisation.mail}</div>
            <div>{organisation.phone}</div>
            <div>{organisation.website}</div>
            <br/>
            <div>
                {organisation.vacancies.map(v =>
                    <div>
                        <Link to={`/vacancy/${v.id}`}>{v.title}</Link>
                        <div>{v.description}</div>
                        <br/>
                    </div>)}
            </div>
        </Box>
    )
}

export default OrganisationPage;