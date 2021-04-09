import {makeStyles} from "@material-ui/core/styles";
import React from "react";
import {useTranslation} from "react-i18next";

const useStyles = makeStyles((theme) => ({
    root: { },
}));

const Organisation = ({organisations}) => {
    const classes = useStyles();
    const { t } = useTranslation();


    return (
        <div className={classes.root}>

        </div>
    )
};

export default Organisation;