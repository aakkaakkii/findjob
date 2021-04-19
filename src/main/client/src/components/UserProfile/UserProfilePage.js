import React from "react";
import {makeStyles} from '@material-ui/core/styles';
import {Box, Grid} from "@material-ui/core";
import {HashRouter as Router, Link, Route, Switch, useLocation} from "react-router-dom";
import UserProfileContainer from "../../containers/userProfile/UserProfileContainer";
import CVContainer from "../../containers/userProfile/CVContainer";
import OrganisationContainer from "../../containers/userProfile/OrganisationContainer";

const useStyles = makeStyles((theme) => ({
    root: {
        padding: 20,
    },
    left: {
        borderRight: `1px solid ${theme.palette.primary.main}`
    },
    link: {
        padding: 10,
        textDecoration: 'none',
        color: theme.plainTextColor,
    },
    selectedLink: {
        color: "#ffffff",
        backgroundColor: theme.palette.primary.main
    },
    organisation: {
        margin: "10px"
    }
}));

const UserProfilePage = ({url}) => {
    const classes = useStyles();
    const location = useLocation();

    const isLocated = (path) => {
        const currUrlParts = location.pathname.split("/");
        const currUrl = currUrlParts[currUrlParts.length - 1];
        return currUrl === path
    }

    return (

        <Grid container className={classes.root}>
            <Grid item md={2}>
                <Box display={"flex"}
                     flexDirection={"column"}
                     className={classes.left}
                >
                    <Link className={`${classes.link} ${isLocated("userProfile") && classes.selectedLink}`}
                          to={`${url}`}>General</Link>
                    <Link className={`${classes.link} ${isLocated("password") && classes.selectedLink}`}
                          to={`${url}/password`}>password</Link>
                    <Link className={`${classes.link} ${isLocated("cv") && classes.selectedLink}`}
                          to={`${url}/cv`}>cv</Link>
                    <Link className={`${classes.link} ${isLocated("organisation") && classes.selectedLink}`}
                          to={`${url}/organisation`}>organisation</Link>
                </Box>
            </Grid>
            <Grid item md={10}>
                <Router>
                    <Switch>
                        <Route exact path={`${url}/`} render={() => <UserProfileContainer/>}/>
                        <Route exact path={`${url}/password`} render={() => <div>12</div>}/>
                        <Route exact path={`${url}/cv`} render={() => <CVContainer/>}/>
                        <Route exact path={`${url}/organisation`} render={() =>
                            <div className={classes.organisation}>
                                <OrganisationContainer/>
                            </div>
                        }/>
                    </Switch>
                </Router>
            </Grid>
        </Grid>

    )
};

export default UserProfilePage;