import {useContext} from "react";
import {ChangeUserContext, UserContext} from "./CurrentUserProvider";

const useCurrentUser = () => {
    const changeUserContext = useContext(ChangeUserContext);
    const userContext = useContext(UserContext);

    const getUserInitial = () => {
        let username = userContext.nickname ? userContext.nickname : userContext.username;
        return username.toUpperCase().slice(0, 1);
    }

    const changeUser = () => {
        if(typeof changeUserContext === 'function') {
            changeUserContext();
        }
    }

    return {
        userId: userContext.id,
        nickname: userContext.nickname,
        username: userContext.username,
        roles: userContext.roles,
        isUserLoggedIn: !!userContext.id,
        changeUser: changeUser,
        userInitial: getUserInitial
    }

}

export default useCurrentUser;