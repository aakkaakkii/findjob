import React from "react";
import {makeStyles} from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    root: {
        // display: "flex",
        // justifyContent: "space-between",
        // alignItems: "center",
    },
}));

const Footer = () => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
           <div>about</div>
           <div>
               Copyright ©2021
           </div>
        </div>

    )
};

export default Footer;