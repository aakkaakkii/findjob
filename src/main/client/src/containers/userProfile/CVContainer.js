import React, {useEffect, useState} from "react";
import CV from "../../components/UserProfile/CV";

const CVContainer = () => {
    const [cvs, setCVs] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {

    }

    return (
        <CV cvs={cvs}/>
    )
};

export default CVContainer;