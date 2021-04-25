import React, {useState} from "react";
import Modal from "@material-ui/core/Modal";
import makeStyles from "@material-ui/core/styles/makeStyles";
import {useTranslation} from "react-i18next";

const useStyles = makeStyles((theme) => ({
    modal: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
    },
    paper: {
        background: "#FFFFFF 0% 0% no-repeat padding-box",
        borderRadius: "10px",
        padding: "25px",
    },
    title: {
        textAlign: "center",
        color: "#626262",
        font: "normal normal medium 12px/20px FiraGO",
    },
    files: {
        display: "grid",
        gridTemplateColumns: "1fr 1fr",
    },
}));

export const WindowOpenContext = React.createContext(null);
export const WindowContentContext = React.createContext(null);

const Window = ({children}) => {
    const classes = useStyles();
    const [open, setOpen] = useState(false);
    const [content, setContent] = useState(<div> asd </div>);
    const {t} = useTranslation();


    const changeOpen = (isOpen) => {
        setOpen(isOpen);
    };

    const changeContent = (content) => {
        setContent(content);
    };

    return (
        <WindowOpenContext.Provider value={changeOpen}>
            <WindowContentContext.Provider value={changeContent}>
                <div>{children}</div>
                <Modal
                    open={open}
                    onClose={() => setOpen(false)}
                    className={classes.modal}
                >
                    <div className={classes.paper}>
                        {content}
                    </div>
                </Modal>
            </WindowContentContext.Provider>
        </WindowOpenContext.Provider>
    );
}

export default Window;