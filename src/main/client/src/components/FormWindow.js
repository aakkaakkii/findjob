import React from "react";
import makeStyles from "@material-ui/core/styles/makeStyles";
import {Box, Button, Modal} from "@material-ui/core";
import CloseIcon from '@material-ui/icons/Close';
import FormElement from "./FormElement";
import {useTranslation} from "react-i18next";

const useStyles = makeStyles((theme) => ({
    modal: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
    },
    close: {
        cursor: "pointer"
    },
    paper: {
        width: "600px",
        backgroundColor: theme.backgroundColor,
        borderRadius: 8,
        padding: 25,
        outline: "none",
    }
}));

const FormWindow = ({title, open, setOpen, dataDescription, data, setData, closeCallback, onSubmit}) => {
    const classes = useStyles();
    const {t} = useTranslation();

    const handleClose = () => {
        if(typeof closeCallback === 'function') {
            closeCallback();
        }
        setOpen(false);
    };

    const handleSave = async () => {
        if(typeof onSubmit === 'function') {
            await onSubmit();
        }
        setOpen(false);
    }


    return (
        <Modal
            open={open}
            onClose={handleClose}
            className={classes.modal}
        >
            <div className={classes.paper}>
                <Box display={"flex"} justifyContent={"space-between"}>
                    {title}
                    <CloseIcon
                        className={classes.close}
                        onClick={handleClose}
                    />
                </Box>
                <Box display={"flex"}
                     flexDirection={"column"}>
                    <FormElement dataDescription={dataDescription} data={data} setData={setData}/>
                </Box>
                <Button
                    variant={"contained"}
                    color={"primary"}
                    onClick={handleSave}
                >{t('save')}</Button>
            </div>
        </Modal>
    )
}

export default FormWindow;