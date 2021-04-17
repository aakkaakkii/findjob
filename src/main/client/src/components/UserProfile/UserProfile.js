import {makeStyles} from "@material-ui/core/styles";
import React, {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {Box, Button, TextField} from "@material-ui/core";
import ExperienceContainer from "../../containers/userProfile/ExperienceContainer";
import EducationContainer from "../../containers/userProfile/EducationContainer";

const useStyles = makeStyles((theme) => ({
    root: {
        width: "100%",
        display: "flex",
        alignItems: "center"
    },
    title: {
      fontSize: theme.title1FontSize,
        marginBottom: "30px"
    },
    dataPanel: {
        marginTop: "40px",
        minWidth: "500px",
    }
}));

const UserProfile = ({updateUserProfile, userProfile, educations}) => {
    const classes = useStyles();
    const [formUserProfile, setFormUserProfile] = useState({
        nickname: ""
    });
    const {t} = useTranslation();

    useEffect(() => {
        setFormUserProfile(userProfile);
    }, [userProfile]);

    const detectUserProfileChange = (e) => {
        let tmpUser = {...formUserProfile};
        tmpUser[e.target.name] = e.target.value;
        setFormUserProfile(tmpUser);
    }

    return (
        <Box display={"flex"} flexDirection={"column"} className={classes.root}>
            <div className={classes.title}>{t("profile")}</div>
            <Box display={"flex"}>
                <TextField
                    label="nickname"
                    name="nickname"
                    value={formUserProfile.nickname}
                    onChange={detectUserProfileChange}/>
                <Button variant={"contained"} color={"primary"}
                        onClick={() => updateUserProfile(formUserProfile)}>{t('save')}</Button>
            </Box>
            <Box className={classes.dataPanel}>
                <ExperienceContainer/>
            </Box>
            <Box className={classes.dataPanel}>
                <EducationContainer/>
            </Box>
        </Box>

    )
};

export default UserProfile;
