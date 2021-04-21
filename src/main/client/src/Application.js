import React from "react";
import {HashRouter as Router, Route, Switch} from "react-router-dom";
import MainContainer from "./containers/MainContainer";
import AboutContainer from "./containers/AboutContainer";
import Header from "./components/Header/Header";
import Footer from "./components/Footer";
import VacancyContainer from "./containers/VacancyContainer";
import VacanciesContainer from "./containers/VacanciesContainer";
import CandidatesContainer from "./containers/CandidatesContainer";
import CandidateContainer from "./containers/CandidateContainer";
import RegistrationContainer from "./containers/RegistrationContainer";
import ThemeWrapper from "./styles/ThemeWrapper";
import {Paper} from "@material-ui/core";
import CurrentUserProvider from "./hoc/user/CurrentUserProvider";
import UserVacanciesContainer from "./containers/UserVacnciesContainer";
import CreateCVContainer from "./containers/CreateCVContainer";
import CreateVacancyContainer from "./containers/CreateVacancyContainer";
import UserProfilePage from "./components/UserProfile/UserProfilePage";
import OrganisationContainer from "./containers/OrganisationContainer";

const Application = () => {

    return (
        <CurrentUserProvider>
            <ThemeWrapper>
                <Paper style={{minHeight:"100vh", height: "100%", borderRadius: '0'}}>
                    <Router>
                        <Header/>
                        <Switch>
                            <Route exact path={`/`} render={() => <MainContainer/>}/>
                            <Route exact path={`/about`} render={() => <AboutContainer/>}/>
                            <Route exact path={`/vacancies`} render={() => <VacanciesContainer/>}/>
                            <Route exact path={`/vacancy/:id`} render={() => <VacancyContainer/>}/>
                            <Route exact path={`/candidates`} render={() => <CandidatesContainer/>}/>
                            <Route exact path={`/candidate/:id`} render={() => <CandidateContainer/>}/>
                            <Route exact path={`/registration`} render={() => <RegistrationContainer/>}/>
                            <Route exact path={`/userVacancies`} render={() => <UserVacanciesContainer/>}/>
                            <Route exact path={`/createCV`} render={() => <CreateCVContainer/>}/>
                            <Route exact path={`/createVacancy`} render={() => <CreateVacancyContainer/>}/>
                            <Route exact path={`/organisation/:id`} render={() => <OrganisationContainer/>}/>
                            <Route path={`/userProfile`}  render={({match: {url}}) => <UserProfilePage url={url}/>}/>
                        </Switch>
                        <Footer/>
                    </Router>
                </Paper>
            </ThemeWrapper>
        </CurrentUserProvider>
    )
};

export default Application;