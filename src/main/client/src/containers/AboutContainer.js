import React from "react";
import {makeStyles} from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
    root: {
        color: theme.palette.primary.main,
    },
}));

const AboutContainer = () => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            about
        </div>
    )
};

export default AboutContainer;