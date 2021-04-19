import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";
import format from "date-fns/format";
import {useTranslation} from "react-i18next";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        paddingBottom: '15px',
        marginBottom: '20px',
        borderBottom: `1px solid ${theme.palette.primary.main}`
    },
    title: {
        color: theme.plainTextColor,
        textDecoration: 'none',
        fontSize: theme.title2FontSize,
        cursor: "pointer",
        "&:hover": {
            color: theme.plainTextColorLight,
        }
    },
    companyName: {
        color: theme.palette.primary.main,
        marginRight: '5px',
        cursor: "pointer",
        "&:hover": {
            color: theme.palette.primary.light,
        }
    },
}));


const VacancyShort = ({title, organisationName, date, id, organisationId}) => {
    const classes = useStyles();
    const {t} = useTranslation();

    return (
        <div className={classes.root}>
            <div>
                <Link className={classes.title} to={`/vacancy/${id}`}>{title}</Link>
            </div>
            <div>
                <Link className={classes.companyName} to={`/organisation/${organisationId}`}>{organisationName}</Link>
                <span>{`${t('create date') }:`}  {date && format(Date.parse(date), "dd/MM")}</span>
            </div>
        </div>
    )
}


export default VacancyShort;