import React from "react";
import {HashRouter as Router, Route, Switch} from "react-router-dom";
import MainPage from "./pages/MainPage";
import AboutPage from "./pages/AboutPage";
import Header from "../components/Header/Header";
import Footer from "../components/Footer";
import VacancyPage from "./pages/VacancyPage";
import VacanciesPage from "./pages/VacanciesPage";
import CandidatesPage from "./pages/CandidatesPage";
import CandidatePage from "./pages/CandidatePage";
import RegistrationPage from "./pages/RegistrationPage";
import ThemeWrapper from "../styles/ThemeWrapper";
import {Paper} from "@material-ui/core";
import CurrentUserProvider from "../hoc/user/CurrentUserProvider";
import UserProfilePage from "./pages/UserProfilePage";
import UserCVsPage from "./pages/UserCVsPage";
import UserVacanciesPage from "./pages/UserVacnciesPage";
import CreateCVPage from "./pages/CreateCVPage";
import CreateVacancyPage from "./pages/CreateVacancyPage";

const Application = () => {

    return (
        <CurrentUserProvider>
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
                            <Route exact path={`/userProfile`} render={() => <UserProfilePage/>}/>
                            <Route exact path={`/userCVs`} render={() => <UserCVsPage/>}/>
                            <Route exact path={`/userVacancies`} render={() => <UserVacanciesPage/>}/>
                            <Route exact path={`/createCV`} render={() => <CreateCVPage/>}/>
                            <Route exact path={`/createVacancy`} render={() => <CreateVacancyPage/>}/>
                        </Switch>
                        <Footer/>
                    </Router>
                </Paper>
            </ThemeWrapper>
        </CurrentUserProvider>

    )
};

export default Application;