import React, {useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import Box from "@material-ui/core/Box";
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import ArrowForwardIosIcon from '@material-ui/icons/ArrowForwardIos';

const useStyles = makeStyles((theme) => ({
    root: {},
    cell: {
        padding: "0 2px"
    },
    header: {
        backgroundColor: '#ececec',
        borderRadius: 8,
        marginBottom: 5,
        padding: "4px 0",
    },
    row: {
        padding: "4px 0",
        borderBottom: "solid 1px #AAAAAA"
    },
    evenRow: {
        backgroundColor: '#ececec'
    },
    selectedRow: {
        backgroundColor: theme.palette.primary.main,
        color: "#ffffff"
    },
    pagination: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
    },
    paginationButton: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        border: "solid 1px #AAAAAA",
        borderRadius: "50%",
        margin: "5px",
        width: "18px",
        height: "18px",
        cursor: "pointer"
    },
    paginationInput: {
        width: "30px",
        height: "18px",
    }
}));

/**
 * @param {Object[]} columns
 * @param {string} columns[].name - title of column
 * @param {string} columns[].dataIndex - data field name
 * @param {number} columns[].flex - flex size
 * @param {function} columns[].renderer - render value function
 * @param {function} rowSelection - select row / row on click event
 * @param {function} pageChange - change page function
 *
 * @param {boolean} isHeaderDisabled - is header disabled
 *
 * @param {Object[]} data
 */

const GridElement = ({columns, data, rowSelection, isHeaderDisabled, pageChange}) => {
    const classes = useStyles();
    const [selectedRowIndex, setSelectedRowIndex] = useState(null);
    const [page, setPage] = useState(1);

    const rowOnClick = (rowData, index) => {
        setSelectedRowIndex(index)
        if (typeof rowSelection === 'function') {
            rowSelection(rowData);
        }
    }

    const onInputPageChange = (e) => {
        setPage(Number.parseInt(e.target.value))
        pageChange(page);
    }

    const onPageChange = (val) => {
        if (page + val > 0) {
            setPage(page + val);
            pageChange(page);
        }
    }

    return (
        <div className={classes.root}>
            {!isHeaderDisabled &&
            <Box
                className={classes.header}
                display="flex"
                flexDirection="row">
                {columns.map(
                    (col, index) =>
                        <Box
                            key={index}
                            flex={col.flex || 1}
                            className={classes.cell}>
                            {col.name}
                        </Box>
                )}
            </Box>
            }
            {data.map((d, index) =>
                <Box
                    key={index}
                    display="flex"
                    flexDirection="row"
                    onClick={() => rowOnClick(d, index)}
                    className={`${classes.row} ${selectedRowIndex === index && classes.selectedRow}`}>
                    {
                        columns.map((col, colIndex) =>
                            <Box
                                key={colIndex}
                                flex={col.flex || 1}
                                className={classes.cell}>
                                {
                                    typeof col.renderer === 'function'
                                        ? col.renderer(d[col.dataIndex], d, index)
                                        : d[col.dataIndex]
                                }
                            </Box>
                        )
                    }
                </Box>
            )}

            {
                typeof pageChange === 'function' &&
                <div className={classes.pagination}>
                    <div
                        className={classes.paginationButton}
                        onClick={() => onPageChange(-1)}
                    >
                        <ArrowBackIosIcon style={{fontSize: 14}}/>
                    </div>
                    <input
                        className={classes.paginationInput}
                        value={page}
                        onChange={onInputPageChange}
                    />
                    <div
                        className={classes.paginationButton}
                        onClick={() => onPageChange(1)}
                    >
                        <ArrowForwardIosIcon style={{fontSize: 14}}/>
                    </div>
                </div>
            }
        </div>
    )
};

export default GridElement;