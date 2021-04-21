import React from "react";
import {useTranslation} from "react-i18next";
import FormElement from "../FormElement";
import {Button} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import Box from "@material-ui/core/Box";

const useStyles = makeStyles((theme) => ({
    root: {
        display: "flex",
        justifyContent: "center"
    },
    btnWrapper: {
        marginTop: 20
    }
}));

const PasswordChange = ({onSubmit, onClear, dataDescription, data, setData}) => {
    const {t} = useTranslation();
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Box display={"flex"} flexDirection={"column"}>
                <FormElement dataDescription={dataDescription} data={data} setData={setData}/>
                <Box display={"flex"} justifyContent={"space-between"} className={classes.btnWrapper}>
                    <Button
                        variant={"contained"}
                        color={"primary"}
                        onClick={onSubmit}
                    >{t('save')}</Button>
                    <Button
                        variant={"outlined"}
                        color={"primary"}
                        onClick={onClear}
                    >{t('clear')}</Button>
                </Box>
            </Box>
        </div>

    )
};

export default PasswordChange;