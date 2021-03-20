import React, {useEffect, useState} from "react";
import {makeStyles} from "@material-ui/core/styles";
import {loadRoles, loadUsers} from "../../../api/admin/userManagementApi";
import UserForm from "./UserForm";


const useStyles = makeStyles((theme) => ({
    root: {
        display: "flex"
    },
    userList: {

    },
    userForm: {

    }
}));


const UserManagement = () => {
    const classes = useStyles();
    const [users, setUsers] = useState([]);
    const [roles, setRoles] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);

    useEffect(async () => {
        let usersResponse = await loadUsers();
        setUsers(usersResponse.data);
        let rolesResponse = await loadRoles();
        setRoles(rolesResponse.data)
    },[]);

    const selectUser = (id) => {
        let user = users.find(u => u.id === id);
        setSelectedUser(user);
        console.log(user)
    };

    return (
        <div className={classes.root}>
            <div className={classes.userList}>
                <div>users</div>
                {users.map(u => <div key={u.id} onClick={() => selectUser(u.id)}>{u.username} </div>)}
            </div>
            <div className={classes.userForm}>
                <UserForm selectedUser={selectedUser} roles={roles}/>
            </div>
        </div>
    )
};

export default UserManagement;