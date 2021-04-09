import {HashRouter as Router, Route, Switch} from "react-router-dom";
import React from "react";
import AdminMainPage from "./containers/admin/AdminMainPage";
import AdminNavbar from "./components/admin/AdminNavnar/AdminNavbar";
import {makeStyles} from "@material-ui/core/styles";
import AdminUserManagementPage from "./containers/admin/AdminUserManagementPage";
import AdminOrganisationPage from "./containers/admin/AdminOrganisationsPage";
import ThemeWrapper from "./styles/ThemeWrapper";

const useStyles = makeStyles((theme) => ({
    root: {
        display: "flex"
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing(3)
    }
}));


const AdminApplication = ({url}) => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <ThemeWrapper disableDarkMod={true}>
                <AdminNavbar url={url}/>
                <div className={classes.content}>
                    <Router>
                        <Switch>
                            <Route exact path={`${url}/`} render={() => <AdminMainPage/>}/>
                            <Route exact path={`${url}/users`} render={() => <AdminUserManagementPage/>}/>
                            <Route exact path={`${url}/organisations`} render={() => <AdminOrganisationPage/>}/>
                        </Switch>
                    </Router>
                </div>
            </ThemeWrapper>
        </div>
    )
};

export default AdminApplication;
