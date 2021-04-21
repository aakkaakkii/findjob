import React, {useState} from "react";
import PasswordChange from "../../components/UserProfile/PasswordChange";
import {changePassword} from "../../api/passwordApi";

const PasswordChangeContainer = () => {
    const [passwordModel, setPasswordModel] = useState({
        currentPassword: "",
        newPassword: "",
        repeatPassword: "",
    });

    const dataDescription = [
        {
            title: "currentPassword",
            dataIndex: "currentPassword",
            type: "string",
            fieldType: "password"
        },
        {
            title: "newPassword",
            dataIndex: "newPassword",
            type: "string",
            fieldType: "password"
        },
        {
            title: "repeatPassword",
            dataIndex: "repeatPassword",
            type: "string",
            fieldType: "password"
        },
    ];

    const change = async () => {
        try {
            await changePassword(passwordModel);
            clear();
        } catch (error) {

        }
    }

    const clear = () => {
        setPasswordModel({
            currentPassword: "",
            newPassword: "",
            repeatPassword: "",
        });
    }

    return (
        <PasswordChange onSubmit={change}
                        onClear={clear}
                        dataDescription={dataDescription}
                        data={passwordModel}
                        setData={setPasswordModel}/>
    )
};

export default PasswordChangeContainer;