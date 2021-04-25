import React from "react";
import Box from "@material-ui/core/Box";
import EditIcon from "@material-ui/icons/Edit";
import DeleteForeverIcon from "@material-ui/icons/DeleteForever";
import {makeStyles} from "@material-ui/core/styles";
import format from "date-fns/format";
import {useTranslation} from "react-i18next";
import VacancyContainer from "../containers/userProfile/VacancyContainer";
import useWindow from "../hoc/Window/useWindow";

const useStyles = makeStyles((theme) => ({
    root: {
        maxWidth: "500px"
    },
    item: {
        padding: "20px 0",
        borderBottom: `1px solid ${theme.palette.primary.main}`,
    },
    icons: {
        ...theme.icon
    },
    title: {
        fontSize: "20px",
        fontWeight: 700,
        marginBottom: 8
    },
    vacanciesWrapper: {
        "& $item": {
            width: "500px",
        },
    }
}));

const usePanelDataRenderers = () => {
    const classes = useStyles();
    const {t} = useTranslation();
    const {openConfirmWindow} = useWindow();

    const onElementDelete = (onDelete) => {
        openConfirmWindow("sure?", onDelete)
    }

    const experienceRenderer = (d, onEdit, onDelete) => {
        return (
            <Box key={d.id} display={"flex"} justifyContent={"space-between"} className={classes.item}>
                <Box>
                    <div className={classes.title}>{d.title}</div>
                    <div>
                        {d.description && d.description.split("\n").map(description => <div>{description}</div>)}
                    </div>
                    {
                        d.startDate &&
                        <div>{format(Date.parse(d.startDate), "MM yyyy")}
                            - {d.endDate && format(Date.parse(d.endDate), "MM yyyy")}</div>
                    }
                </Box>
                <Box display={"flex"} flexDirection={"column"}>
                    <EditIcon className={classes.icons} onClick={() => onEdit(d.id)}/>
                    <DeleteForeverIcon className={classes.icons} onClick={() => onDelete(d.id)}/>
                </Box>
            </Box>
        )
    }

    const educationRenderer = (d, onEdit, onDelete) => {
        return (
            <Box key={d.id} display={"flex"} justifyContent={"space-between"} className={classes.item}>
                <Box>
                    <div className={classes.title}>{d.school}</div>
                    <div>{d.degree}</div>
                    {
                        d.startDate &&
                        <div>{format(Date.parse(d.startDate), "MM yyyy")}
                            - {d.endDate && format(Date.parse(d.endDate), "MM yyyy")}</div>
                    }
                </Box>
                <Box display={"flex"} flexDirection={"column"}>
                    <EditIcon className={classes.icons} onClick={() => onEdit(d.id)}/>
                    <DeleteForeverIcon className={classes.icons} onClick={() => onDelete(d.id)}/>
                </Box>
            </Box>
        )
    }

    const organisationRenderer = (d, onEdit, onDelete) => {
        return (
            <Box key={d.id} display={"flex"} flexDirection={"column"} className={classes.item}>
                <Box display={"flex"} justifyContent={"space-between"}>
                    <Box>
                        <div className={classes.title}>{d.title}</div>
                        <div>{d.description}</div>
                        <div>{d.address}</div>
                        <div>{d.mail}</div>
                        <div>{d.phone}</div>
                        <div>{d.website}</div>
                    </Box>
                    <Box display={"flex"} flexDirection={"column"}>
                        <EditIcon className={classes.icons} onClick={() => onEdit(d.id)}/>
                        <DeleteForeverIcon className={classes.icons} onClick={() => onDelete(d.id)}/>
                    </Box>
                </Box>
                <Box
                    display={"flex"}
                    flexDirection={"column"}
                    alignItems={"flex-end"}
                    className={classes.vacanciesWrapper}>
                    <Box display={"flex"} justifyContent={"space-between"}>
                        <VacancyContainer organisationId={d.id}/>
                    </Box>
                </Box>
            </Box>
        )
    }

    const vacancyRenderer = (d, onEdit, onDelete) => {
        return (
            <Box key={d.id} display={"flex"} flexDirection={"column"} className={classes.item}>
                <Box display={"flex"} justifyContent={"space-between"}>
                    <Box>
                        <div className={classes.title}>{d.title}</div>
                    </Box>
                    <Box display={"flex"} flexDirection={"column"}>
                        <EditIcon className={classes.icons} onClick={() => onEdit(d.id)}/>
                        {/*<DeleteForeverIcon className={classes.icons} onClick={() => onDelete(d.id)}/>*/}
                        <DeleteForeverIcon className={classes.icons} onClick={() => onElementDelete(() => onDelete(d.id))}/>
                    </Box>
                </Box>
            </Box>
        )
    }


    return {
        experienceRenderer,
        educationRenderer,
        organisationRenderer,
        vacancyRenderer
    }

}

export default usePanelDataRenderers;