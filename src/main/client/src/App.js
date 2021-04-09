import './App.css';
import Application from "./Application";
import {HashRouter as Router, Route, Switch} from "react-router-dom";
import React from "react";
import AdminApplication from "./AdminApplication";
import LoginContainer from "./containers/LoginContainer";
import "./locale/i18n";

function App() {
    return (
            <Router>
                <Switch>
                    <Route path={"/admin"} render={({match: {url}}) => <AdminApplication url={url}/>}/>
                    <Route path={`/login`} render={() => <LoginContainer/>}/>
                    <Route path={"/"} render={() => <Application/>}/>
                </Switch>
            </Router>
    );
}

export default App;
