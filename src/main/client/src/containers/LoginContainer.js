import React from "react";
import {Button, TextField} from "@material-ui/core";

const LoginContainer = () => {

    return (
        <div>
            <form method={"POST"} action="/login">
                <TextField name={"nickname"} id={"username"}/>
                <TextField name={"password"} id={"password"} type="password"/>
                <Button type={"submit"} name="submit">login</Button>
            </form>
        </div>
    )
};

export default LoginContainer;