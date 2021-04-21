import React from "react";
import {Link} from "react-router-dom";
import {makeStyles} from '@material-ui/core/styles';
import UserMenu from "./UserMenu";
import useCurrentUser from "../../hoc/user/useCurrentUser";
import {useTranslation} from "react-i18next";

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
    const {isUserLoggedIn} = useCurrentUser();
    const {t} = useTranslation();

    return (
        <div className={classes.root}>
            <div>
                <Link className={classes.navbarLink} to="/">{t('home')}</Link>
            </div>

            <ul className={classes.navbarLinkWrapper}>
         {/*       <li>
                    <Link className={classes.navbarLink} to="/about">about</Link>
                </li>*/}
                <li>
                    <Link className={classes.navbarLink} to="/vacancies">{t('vacancies')}</Link>
                </li>
                <li>
                    <Link className={classes.navbarLink} to="/candidates">{t('candidates')}</Link>
                </li>
                <li>
                    <UserMenu/>
                </li>
                <li>
                    {
                        !isUserLoggedIn && <Link className={classes.navbarLink} to="/login">{t('login')}</Link>
                    }
                </li>
            </ul>
        </div>

    )
};

export default Header;