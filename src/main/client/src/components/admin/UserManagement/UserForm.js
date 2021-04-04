import {makeStyles} from "@material-ui/core/styles";
import React, {useEffect, useState} from "react";
import {Button, Select, TextField} from "@material-ui/core";
import {addUser} from "../../../api/admin/userManagementApi";

const useStyles = makeStyles((theme) => ({
    root: {
        display:'grid'
    }
}));


const UserForm = ({selectedUser, roles}) => {
    const classes = useStyles();
    const [user, setUser] = useState({
        id: -1,
        username: '',
        nickname: '',
        email: '',
        password: '',
        active: false,
        blocked: false,
        roles: [],
    });

    useEffect(() => {
        if(selectedUser) {
            setUser(selectedUser);
        }
    }, [selectedUser]);

    const detectUserChange = (e) => {
        let tmpUser = {...user};
        tmpUser[e.target.name] = e.target.value;
        setUser(tmpUser);
    }

    const saveUser = async () => {
        await addUser(user);
    }

    return (
        <div className={classes.root}>
                <TextField
                    label="username"
                    name="username"
                    value={user.username}
                    onChange={detectUserChange}/>
                <TextField
                    label="nickname"
                    name="nickname"
                    value={user.nickname}
                    onChange={detectUserChange}/>
                <TextField
                    label="email"
                    name="email"
                    value={user.email}
                    onChange={detectUserChange}/>
                <TextField
                    label="password"
                    name="password"
                    value={user.password}
                    onChange={detectUserChange}/>

                <Select
                    name="active"
                    label="active"
                    value={user.active}
                    onChange={detectUserChange}
                >
                    <option value={true}>true</option>
                    <option value={false}>false</option>
                </Select>
                <Select
                    name="blocked"
                    label="blocked"
                    value={user.blocked}
                    onChange={detectUserChange}
                >
                    <option value={true}>true</option>
                    <option value={false}>false</option>
                </Select>
                <Select
                    multiple
                    label="roles"
                    name="roles"
                    value={user.roles}
                    onChange={detectUserChange}
                >
                    <option value=""/>
                    {roles.map(role => <option key={role} value={role}>{role}</option>)}
                </Select>

                <Button onClick={saveUser} variant={"contained"} color={"primary"}>save</Button>
        </div>
    )
};

export default UserForm;