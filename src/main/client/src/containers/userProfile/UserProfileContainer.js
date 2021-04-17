import React, {useEffect, useState} from "react";
import UserProfile from "../../components/UserProfile/UserProfile";
import {updateProfile} from "../../api/userApi";
import useCurrentUser from "../../hoc/user/useCurrentUser";

const UserProfileContainer = () => {
    const [educations, setEducations] = useState([]);
    const {nickname} = useCurrentUser();

    useEffect(() => {
        fetchEducation();
    },[]);

    const fetchEducation = async () => {

    };

    const updateUserProfile = async (userProfile) => {
        const res = await updateProfile(userProfile);
    };

    return (
        <UserProfile
            updateUserProfile={updateUserProfile}
            userProfile={{nickname: nickname}}
            educations={educations}/>
    )
};

export default UserProfileContainer;