import React from "react";
import {Link} from "react-router-dom";
import {makeStyles} from '@material-ui/core/styles';
import UserMenu from "./UserMenu";

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
            color:theme.palette.primary.main
        }
    },
    themeSwitch: {
        cursor: 'pointer'
    }
}));

const Header = () => {
    const classes = useStyles();

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
                    <UserMenu/>
                </li>
            </ul>
        </div>

    )
};

export default Header;