import React, {useEffect, useState} from "react";
import {ThemeProvider} from "@material-ui/styles";
import applicationTheme from "./AplicationTheme";
import {createMuiTheme} from "@material-ui/core";

export const ThemeContext = React.createContext('light');
export const ThemeUpdateContext = React.createContext();

const ThemeWrapper = ({disableDarkMod, children}) => {
    const [theme, setTheme] = useState(createMuiTheme(applicationTheme()));
    const [mode, setMode] = useState('light');

    useEffect(() => {
        if(!disableDarkMod) {
            changeTheme(window.localStorage.getItem('mode') || 'light')
        }
    }, [disableDarkMod]);

    const changeTheme = (changedMode) => {
        setTheme(createMuiTheme(applicationTheme(changedMode)));
        setMode(changedMode);
        window.localStorage.setItem('mode', changedMode);
    };

    const toggleTheme = () => {
        changeTheme(mode === 'light' ? 'dark' : 'light');
    };

    return (
        <ThemeContext.Provider value={mode}>
            <ThemeUpdateContext.Provider value={toggleTheme}>
                <ThemeProvider theme={theme}>
                    {children}
                </ThemeProvider>
            </ThemeUpdateContext.Provider>
        </ThemeContext.Provider>
    )
};


export default ThemeWrapper;