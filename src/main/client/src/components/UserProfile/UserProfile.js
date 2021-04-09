import {makeStyles} from "@material-ui/core/styles";
import React, {useState} from "react";
import {useTranslation} from "react-i18next";
import {Box, Button, TextField} from "@material-ui/core";
import useCurrentUser from "../../hoc/user/useCurrentUser";

const useStyles = makeStyles((theme) => ({
    root: {},
}));

const UserProfile = ({updateUser, educations, experiences}) => {
    const classes = useStyles();
    const [formUser, setFormUser] = useState({});
    const {userNickname} = useCurrentUser();
    const {t} = useTranslation();

    const detectUserChange = (e) => {
        // let tmpUser = {...user};
        // tmpUser[e.target.name] = e.target.value;
        // setUser(tmpUser);
    }

    return (
        <Box display={"flex"} flexDirection={"column"} className={classes.root}>

            <div>profile</div>
            <div>
                <TextField
                    label="nickname"
                    name="nickname"
                    value={userNickname}
                    onChange={detectUserChange}/>
                <Button>save</Button>
            </div>
            <div>
                experience
            </div>
            <div>
                education
            </div>
        </Box>

    )
};

export default UserProfile;
