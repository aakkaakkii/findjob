import React from "react";
import {Drawer, ListItem} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import {Link} from "react-router-dom";

const drawerWidth = 120

const useStyles = makeStyles((theme) => ({
    root: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
    },
}));


const AdminNavbar = ({url}) => {
    const classes = useStyles();


    return (
        <div className={classes.root}>

        <Drawer
            variant="permanent"
            open
            classes={{
                paper: classes.drawerPaper,
            }}
        >
            <ListItem button component={Link} to={`${url}`}>
                main
            </ListItem>
            <ListItem button component={Link} to={`${url}/users`}>
                users
            </ListItem>
            <ListItem button component={Link} to={`${url}/organisations`}>
                Organisations
            </ListItem>
        </Drawer>
        </div>
    )

};

export default AdminNavbar;