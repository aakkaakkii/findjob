import {makeStyles} from "@material-ui/core/styles";
import React from "react";
import Search from "./Search";

const useStyles = makeStyles((theme) => ({
    root: {
        display: "flex",
        justifyContent: "space-between"
        // flexDirection: "row"
    },
    bannerLogo: {
        height: 'calc(100vh - 120px)',
        width: '100%',
        // position: 'relative',
        // zIndex: '-1',
    },
    title: {
        fontSize: "60px",
        fontWeight: "900"
    },
    leftSection: {
        display: "flex",
        alignItems: "center",
    }
}));

const Banner = () => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <div className={classes.leftSection}>
                <div>
                    <div className={classes.title}>Largets Job Site In</div>
                    <div className={classes.title}>The World</div>
                    <Search/>
                </div>


            </div>
            <div>
                <img src={process.env.PUBLIC_URL +'/worker.svg'} className={classes.bannerLogo} />
            </div>



        </div>
    )
};

export default Banner;