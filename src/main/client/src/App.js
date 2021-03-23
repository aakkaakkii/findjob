import './App.css';
import Application from "./containers/Application";
import {HashRouter as Router, Route, Switch} from "react-router-dom";
import React from "react";
import AdminApplication from "./containers/AdminApplication";
import LoginPage from "./containers/pages/LoginPage";
import "./locale/i18n";

function App() {
    return (
            <Router>
                <Switch>
                    <Route path={"/admin"} render={({match: {url}}) => <AdminApplication url={url}/>}/>
                    <Route path={`/login`} render={() => <LoginPage/>}/>
                    <Route path={"/"} render={() => <Application/>}/>
                </Switch>
            </Router>
    );
}

export default App;
