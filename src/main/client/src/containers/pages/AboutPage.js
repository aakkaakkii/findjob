import React from "react";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        color: theme.primaryColor,
    },
}));

const AboutPage = () => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            about
        </div>
    )
};

export default AboutPage;