import React from "react";
import {Button, makeStyles, TextField} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({}));

const LoginPage = () => {
    const classes = useStyles();
    return <div>
        <form method={"POST"} action="/login">
            <TextField name={"username"} id={"username"}/>
            <TextField name={"password"} id={"password"} type="password"/>
            <Button type={"submit"} name="submit">login</Button>
        </form>
    </div>
}

export default LoginPage;