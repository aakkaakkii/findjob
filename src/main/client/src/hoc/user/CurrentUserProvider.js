import React, {createContext, useEffect, useState} from "react";
import {getCurrentUser} from "../../api/userApi";

export const ChangeUserContext = createContext(null);
export const UserContext = createContext({roles: []});

const CurrentUserProvider = ({children}) => {
    const [user, setUser] = useState({roles: []});

    useEffect( () => {
        changeUser();
    }, [])

    const changeUser = async () => {
        const userResponse = await getCurrentUser();
        const userData = userResponse.data;
        setUser({
            id: userData.id,
            nickname: userData.nickname,
            username: userData.username,
            roles: userData.roles
        })
    }

    return (
        <ChangeUserContext.Provider value={changeUser}>
            <UserContext.Provider value={user}>
                {children}
            </UserContext.Provider>
        </ChangeUserContext.Provider>
    )
}

export default CurrentUserProvider;