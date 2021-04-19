import {makeStyles} from "@material-ui/core/styles";
import React, {useState} from "react";
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import FormWindow from "../FormWindow";
import Box from "@material-ui/core/Box";

const useStyles = makeStyles((theme) => ({
    root: {},
    title: {
        fontSize: theme.title2FontSize,
      color: theme.plainTextColorLight
    },
    header: {
        // padding: "10px 10px",
    },
    icons: {
        ...theme.icon
    }
}));

const DataPanel =
    ({
         title,
        formTitle,
         listData,
         formData,
         setFormData,
         formDataDescription,
         submitForm,
         windowCloseCallback,
         deleteListItem,
         editListItem,
        dataRenderer
     }) => {
        const classes = useStyles();
        const [open, setOpen] = useState(false);

        const onAddExperience = () => {
            setOpen(true);
        }

        const onEdit = (id) => {
            editListItem(id);
            setOpen(true);
        }

        return (
            <Box className={classes.root}>
                <Box display={"flex"} justifyContent={"space-between"} className={classes.header} >
                    <div className={classes.title}>
                        {title}
                    </div>
                    <AddCircleOutlineIcon
                        className={classes.icons}
                        onClick={onAddExperience}
                    />
                </Box>
                <div>
                    {listData && listData.map(d => dataRenderer(d, onEdit, deleteListItem))}
                </div>
                <FormWindow
                    title={formTitle}
                    dataDescription={formDataDescription}
                    data={formData}
                    setData={setFormData}
                    open={open}
                    setOpen={setOpen}
                    onSubmit={submitForm}
                    closeCallback={windowCloseCallback}
                />
            </Box>
        )
    };

export default DataPanel;
