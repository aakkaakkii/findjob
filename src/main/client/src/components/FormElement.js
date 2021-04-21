import React from "react";
import {useTranslation} from "react-i18next";
import {MenuItem, Select, TextField} from "@material-ui/core";
import Aux from "../hoc/Aux";
import {KeyboardDatePicker, MuiPickersUtilsProvider,} from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';

const FormElement = ({dataDescription, data, setData}) => {
    const {t} = useTranslation();

    const detectFormChange = (val, name) => {
        let tmp = {...data};
        tmp[name] = val;
        setData(tmp);
    }

    return (
        <Aux>
            {dataDescription.map((d) => {
                switch (d.type) {
                    case "date":
                        return <MuiPickersUtilsProvider key={d.dataIndex} utils={DateFnsUtils}>
                            <KeyboardDatePicker
                                disableToolbar
                                variant="inline"
                                name={d.dataIndex}
                                format="dd/MM/yyyy"
                                margin="normal"
                                label={d.title}
                                value={data[d.name]}
                                onChange={(val) => detectFormChange(val, d.dataIndex)}
                            />
                        </MuiPickersUtilsProvider>
                    case "select":
                        return <Select
                            label={t(d.title)}
                            name={d.dataIndex}
                            value={data[d.dataIndex]}
                            onChange={(e) => detectFormChange(e.target.value, d.dataIndex)}
                            key={d.dataIndex}
                        >
                            <MenuItem value=""/>
                            {d.data.map(item => <MenuItem key={item[d.valueField]} value={item[d.valueField]}>
                                {item[d.displayField]}
                            </MenuItem>)
                            }
                        </Select>
                    case "multiSelect":
                        return <Select
                            multiple
                            label={t(d.title)}
                            name={d.dataIndex}
                            value={data[d.dataIndex]}
                            onChange={(e) => detectFormChange(e.target.value, d.dataIndex)}
                            key={d.dataIndex}
                        >
                            <MenuItem value=""/>
                           {d.data.map(item => <MenuItem key={item[d.valueField]} value={item[d.valueField]}>
                                {item[d.displayField]}
                            </MenuItem>)
                            }
                        </Select>
                    case "text":
                        return <TextField
                            multiline
                            rows={4}
                            key={d.dataIndex}
                            label={t(d.title)}
                            name={d.dataIndex}
                            value={data[d.dataIndex]}
                            onChange={(e) => detectFormChange(e.target.value, d.dataIndex)}/>
                    case "number":
                    case "string":
                    default:
                        return <TextField
                            type={d.fieldType ? d.fieldType : "string"}
                            key={d.dataIndex}
                            label={t(d.title)}
                            name={d.dataIndex}
                            value={data[d.dataIndex]}
                            onChange={(e) => detectFormChange(e.target.value, d.dataIndex)}/>
                }
            })}
        </Aux>
    )
}

export default FormElement;