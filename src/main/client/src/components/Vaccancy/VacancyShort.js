import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";

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


const VacancyShort = ({title, companyName, date, id}) => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <div>
                <Link className={classes.title} to={`vacancy/${id}`}>{title}</Link>
            </div>
            <div>
                <span className={classes.companyName}>{companyName}</span>
                <span>{date.toString()}</span>
            </div>
        </div>
    )
}


export default VacancyShort;