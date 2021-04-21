import React from "react";
import ArrowBackIosIcon from "@material-ui/icons/ArrowBackIos";
import ArrowForwardIosIcon from "@material-ui/icons/ArrowForwardIos";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    pagination: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
    },
    paginationButton: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        borderRadius: "50%",
        margin: "5px",
        cursor: "pointer",
        fontSize: "16px",
        color: theme.palette.primary.main,
    },
    disabledPaginationButton: {
        cursor: "default",
        color: theme.palette.primary.light
    }
}));

const Pagination = ({currPage, totalPages, fetchData, pageChangeCallback}) => {
    const classes = useStyles();
    const isPrevDisabled = currPage === 0;
    const isNextDisabled = currPage + 1 === totalPages;

    const nextPage = () => {
        if (!isNextDisabled) {
            fetchData(currPage + 1);
            if (typeof pageChangeCallback === 'function') {
                pageChangeCallback();
            }
        }
    }

    const prevPage = () => {
        if (!isPrevDisabled) {
            fetchData(currPage - 1);
            if (typeof pageChangeCallback === 'function') {
                pageChangeCallback();
            }
        }
    }


    return (
        <div className={classes.pagination}>
            <ArrowBackIosIcon
                className={`${classes.paginationButton} ${isPrevDisabled && classes.disabledPaginationButton}`}
                onClick={prevPage}/>
            {currPage + 1}
            <ArrowForwardIosIcon
                className={`${classes.paginationButton} ${isNextDisabled && classes.disabledPaginationButton}`}
                onClick={nextPage}/>
        </div>
    )
};

export default Pagination;