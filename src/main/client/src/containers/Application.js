import React from "react";
import {HashRouter as Router, Route, Switch} from "react-router-dom";
import MainPage from "./pages/MainPage";
import AboutPage from "./pages/AboutPage";
import Header from "../components/Header";
import Footer from "../components/Footer";
import VacancyPage from "./pages/VacancyPage";
import VacanciesPage from "./pages/VacanciesPage";
import CandidatesPage from "./pages/CandidatesPage";
import CandidatePage from "./pages/CandidatePage";
import RegistrationPage from "./pages/RegistrationPage";
import ThemeWrapper from "../styles/ThemeWrapper";
import {Paper} from "@material-ui/core";

const Application = () => {

    return (
        <ThemeWrapper>
            <Paper style={{height: "100%", borderRadius: '0'}}>
                <Router>
                    <Header/>
                    <Switch>
                        <Route exact path={`/`} render={() => <MainPage/>}/>
                        <Route exact path={`/about`} render={() => <AboutPage/>}/>
                        <Route exact path={`/vacancies`} render={() => <VacanciesPage/>}/>
                        <Route exact path={`/vacancy/:id`} render={() => <VacancyPage/>}/>
                        <Route exact path={`/candidates`} render={() => <CandidatesPage/>}/>
                        <Route exact path={`/candidate/:id`} render={() => <CandidatePage/>}/>
                        <Route exact path={`/registration`} render={() => <RegistrationPage/>}/>
                    </Switch>
                    <Footer/>
                </Router>
            </Paper>
        </ThemeWrapper>
    )
};

export default Application;