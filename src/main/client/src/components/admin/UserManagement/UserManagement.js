import React from "react";
import {makeStyles} from "@material-ui/core/styles";
import UserForm from "./UserForm";
import GridElement from "../../GridElement";
import {useTranslation} from "react-i18next";


const useStyles = makeStyles((theme) => ({
    root: {
        display: "flex"
    },
    userList: {},
    userForm: {},
    test: {
        width: '500px'
    }
}));

let elements = [
    {
        test: "asd",
        test1: "test1",
        test2: "test2",
    },
    {
        test: "132123",
        test1: "tesdddasdt1",
        test2: "asdsad",
    },
    {
        test: "a55 54 54sd",
        test1: "test1",
        test2: "test2",
    },
    {
        test: "asdf fasdf",
        test2: "test2",
    }
];

let columns = [
    {
        name: "col1",
        dataIndex: "test",
        flex: 1,
    },
    {
        name: "col1",
        dataIndex: "test1",
        flex: 2
    },
    {
        name: "col3",
        dataIndex: "test2",
        flex: 1
    }
]


const UserManagement = ({roles, users, selectedUser, selectUser}) => {
    const classes = useStyles();
    const {t} = useTranslation();

    const rowOnClick = (data) => {
        console.log(data)
    }

    return (
        <div className={classes.root}>
            <GridElement
                columns={[
                    {
                        name: t('users'),
                        dataIndex: "username",
                    }
                ]}
                data={users}
                rowSelection={(rowData) => selectUser(rowData.id)}/>
            <div className={classes.userForm}>
                <UserForm selectedUser={selectedUser} roles={roles}/>
            </div>
            <div className={classes.test}>
                <GridElement columns={columns} data={elements} rowSelection={rowOnClick} isHeaderDisabled={false}/>
            </div>
        </div>
    )
};

export default UserManagement;