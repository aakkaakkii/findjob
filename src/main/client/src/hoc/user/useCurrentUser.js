import { useContext } from "react";
import {ChangeUserContext, UserContext} from "./CurrentUserProvider";

const useCurrentUser = () => {
    const changeUserContext = useContext(ChangeUserContext);
    const userContext = useContext(UserContext);

    return {
        userId: userContext.id,
        nickname: userContext.nickname,
        username: userContext.username,
        roles: userContext.roles,
        isUserLoggedIn: !!userContext.id
    }

}

export default useCurrentUser;