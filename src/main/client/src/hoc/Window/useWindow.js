import React, {useContext} from "react";
import {WindowContentContext, WindowOpenContext} from "./Window";
import makeStyles from "@material-ui/core/styles/makeStyles";
import {Box, Button} from "@material-ui/core";
import {useTranslation} from "react-i18next";

const useStyles = makeStyles((theme) => ({}));

const useWindow = () => {
    const classes = useStyles();
    const {t} = useTranslation();
    const openContext = useContext(WindowOpenContext);
    const contentContext = useContext(WindowContentContext);

    const onConfirmAction = (action) => {
        if(typeof action === 'function') {
            action();
            openContext(false);
        }
    }

    const openConfirmWindow = (confirmMessage, confirmAction) => {
        const content =
            <Box display={"flex"} flexDirection={"column"}>
                <div>
                    {confirmMessage}
                </div>
                <div>
                    <Button
                        variant={"contained"}
                        color={"primary"}
                        onClick={() => onConfirmAction(confirmAction)}
                    >{t('confirm')}</Button>
                    <Button
                        variant={"contained"}
                        color={"primary"}
                        onClick={() => openContext(false)}
                    >{t('cancel')}</Button>
                </div>
            </Box>

        contentContext(content);
        openContext(true);

    }

    return {
        openConfirmWindow
    }
}

export default useWindow;