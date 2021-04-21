import React, {useContext, useState} from "react";
import {Link} from "react-router-dom";
import {makeStyles} from '@material-ui/core/styles';
import {ThemeContext, ThemeUpdateContext} from "../../styles/ThemeWrapper";
import Brightness7Icon from '@material-ui/icons/Brightness7';
import Brightness4Icon from '@material-ui/icons/Brightness4';
import {Menu, MenuItem, Select} from "@material-ui/core";
import useCurrentUser from "../../hoc/user/useCurrentUser";
import {useTranslation} from "react-i18next";
import LanguageIcon from "@material-ui/icons/Language";
import {EN, GE} from "../../locale/i18n";

const useStyles = makeStyles((theme) => ({
    userLogo: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        width: "24px",
        height: "24px",
        fontWeight: "700",
        borderRadius: "50%",
        backgroundColor: theme.palette.primary.main,
        color: "#FFFFFF"
    },
    userLogoOutside: {
        cursor: 'pointer'
    },
    themeSwitch: {
        cursor: 'pointer'
    },
    langChangeSelect: {
        marginLeft: "10px",
        fontSize: "14px",
        border: "none",
        "&:focus": {
            backgroundColor: "transparent",
        },
        "&:before": {
            borderColor: "transparent",
        },
        "&:after": {
            borderColor: "transparent",
        },
    },
    userMenu: {
        boxShadow: "none",
        border: 'none',
        outline: 'none'
    },
    wrapper: {
        padding: "0 10px",
        display: "flex",
        justifyContent: "inline",
        alignItems: "center",
    },
    navbarLink: {
        color: theme.palette.primary.main,
        fontWeight: "bold",
        textDecoration: 'none',
        "&:hover": {
            color: theme.palette.primary.dark
        }
    },
    userLogoInfoWrapper: {
        marginLeft: "10px"
    },
    icon: {
        fontSize: 20,
        color: theme.palette.primary.main,
        marginRight: "5px"
    }
}));

const UserMenu = () => {
    const classes = useStyles();
    const changeTheme = useContext(ThemeUpdateContext);
    const theme = useContext(ThemeContext);
    const [anchorEl, setAnchorEl] = React.useState(null);
    const {username, isUserLoggedIn, nickname, userInitial} = useCurrentUser();

    const {t, i18n} = useTranslation();
    const [locale, setLocale] = useState(i18n.language);

    const handleLocaleChange = (e) => {
        let value = e.target.value;
        i18n.changeLanguage(value);
        setLocale(value);
    };

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <>
            <div
                className={`${classes.userLogo} ${classes.userLogoOutside}`}
                onClick={handleClick}>{
                isUserLoggedIn ? userInitial() : '...'
            }</div>
            <Menu
                id="simple-menu"
                anchorEl={anchorEl}
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                <div className={classes.userMenu}>
                    {
                        isUserLoggedIn && <>
                            <div className={classes.wrapper}>
                                <div className={classes.userLogo}>
                                    {userInitial()}
                                </div>
                                <div className={classes.userLogoInfoWrapper}>
                                    <div>{nickname ? nickname : username}</div>
                                    <Link
                                        className={classes.navbarLink}
                                        to={"/userProfile"}
                                        onClick={handleClose}
                                    >
                                        {t("profile")}
                                    </Link>
                                </div>
                            </div>
                            <hr/>
                            <div className={classes.wrapper}>
                                <div>
                                    <div>
                                        <Link
                                            className={classes.navbarLink}
                                            to={"/userCVs"}
                                            onClick={handleClose}>
                                            {t("cvs")}
                                        </Link>
                                    </div>
                                    <div>
                                        <Link
                                            className={classes.navbarLink}
                                            to={"/userProfile/organisation"}
                                            onClick={handleClose}>
                                            {t("organisations")}
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                        </>
                    }
                    <div
                        className={`${classes.themeSwitch} ${classes.wrapper}`}
                        onClick={() => changeTheme()}>
                        {theme === 'light' ? <Brightness4Icon className={classes.icon}/>
                            : <Brightness7Icon className={classes.icon}/>}
                        {t('appearance')} {theme === 'light' ? t('light') : t('dark')}
                    </div>
                    <div className={classes.wrapper}>
                        <LanguageIcon className={classes.icon}/>
                        <span>{t("language")}</span>
                        <Select
                            className={classes.langChangeSelect}
                            value={locale}
                            onChange={handleLocaleChange}
                        >
                            <MenuItem value={EN}>EN</MenuItem>
                            <MenuItem value={GE}>GE</MenuItem>
                        </Select>
                    </div>
                </div>
            </Menu>
        </>

    )
};

export default UserMenu;