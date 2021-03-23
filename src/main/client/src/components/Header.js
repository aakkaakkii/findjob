import React, {useContext} from "react";
import {Link} from "react-router-dom";
import {makeStyles} from '@material-ui/core/styles';
import {ThemeContext, ThemeUpdateContext} from "../styles/ThemeWrapper";
import Brightness7Icon from '@material-ui/icons/Brightness7';
import Brightness4Icon from '@material-ui/icons/Brightness4';
import {Menu, MenuItem} from "@material-ui/core";
import useCurrentUser from "../hoc/user/useCurrentUser";

const useStyles = makeStyles((theme) => ({
    root: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",

        "& li": {
            display: "inline-block",
            padding: "0 15px",
        }
    },
    navbarLinkWrapper: {
        display: "flex",
        alignItems: "center",
    },
    navbarLink: {
        color: theme.plainTextColor,
        textDecoration: 'none',
        margin: '5px 5px',
        "&:hover": {
            color:theme.primaryColor
        }
    },
    themeSwitch: {
        cursor: 'pointer'
    }
}));

const Header = () => {
    const classes = useStyles();
    const changeTheme = useContext(ThemeUpdateContext);
    const theme = useContext(ThemeContext);
    const [anchorEl, setAnchorEl] = React.useState(null);
    const {username, isUserLoggedIn, nickname} = useCurrentUser();

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <div className={classes.root}>
            <div>
                <Link to="/">home</Link>
            </div>

            <ul className={classes.navbarLinkWrapper}>
                <li>
                    <Link className={classes.navbarLink} to="/about">about</Link>
                </li>
                <li>
                    <Link className={classes.navbarLink} to="/vacancies">vacancies</Link>
                </li>
                <li>
                    <Link className={classes.navbarLink} to="/candidates">candidates</Link>
                </li>
                <li>
                    <div onClick={handleClick}>{
                        isUserLoggedIn ? username.toUpperCase().slice(0, 1): '...'
                    }</div>
                </li>
            </ul>
            <Menu
                id="simple-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                <div
                    className={classes.themeSwitch}
                    onClick={() => changeTheme()}>
                    { theme === 'light' ? <Brightness4Icon/> : <Brightness7Icon/> }
                </div>
                {
                    isUserLoggedIn && <>
                        <MenuItem onClick={handleClose}>{username}</MenuItem>
                        <MenuItem onClick={handleClose}>vacancies</MenuItem>
                        <MenuItem onClick={handleClose}>cvs</MenuItem>
                    </>
                }
                <MenuItem onClick={handleClose}>Profile</MenuItem>
            </Menu>
        </div>

    )
};

export default Header;