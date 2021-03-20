import {makeStyles} from "@material-ui/core/styles";
import React, {useState} from "react";
import TabPanel from "../TabPanel";
import {Button, TextField, withStyles} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    root: {},
    searchBar: {
        display: 'flex',
        // alignItems: "flex-start",
        // flexDirection: "row",
        backgroundColor: theme.primaryColor,
        padding: "10px",
        borderRadius: "4px"
    },
    activeBtn: {
        backgroundColor: theme.primaryColor,
        borderRadius: "4px 4px 0 0",
        color: 'white',
        "&:hover": {
            backgroundColor: theme.primaryColor,
        }
    },
    searchInputField: {
        width: "100%",
        marginRight: "10px",
        // margin: "5px",
        padding: "15px",
        outline: "none",
        border: "1px solid black",
        borderRadius: "8px"
    },
}));

const Search = () => {
    const classes = useStyles();
    const [tabPosition, setTabPosition] = useState(0);

    return (
        <div className={classes.root}>
            <div>
                <Button
                    className={tabPosition === 0 && classes.activeBtn}
                    onClick={() => setTabPosition(0)}>job</Button>
                <Button
                    className={tabPosition === 1 && classes.activeBtn}
                    onClick={() => setTabPosition(1)}>candidate</Button>
            </div>
            <TabPanel value={tabPosition} index={0}>
                <div
                    style={tabPosition === 0 && {borderRadius: "0 4px 4px 4px"}}
                    className={classes.searchBar}>
                    <input placeholder={"job"} className={classes.searchInputField} type={"text"}/>
                    <Button variant="contained" color="secondary">search</Button>
                </div>
            </TabPanel>
            <TabPanel value={tabPosition} index={1}>
                <div className={classes.searchBar}>
                    <input placeholder={"candidate"} className={classes.searchInputField} type={"text"}/>
                    <Button variant="contained" color="secondary">search</Button>
                </div>
            </TabPanel>
        </div>
    )
};

export default Search;
