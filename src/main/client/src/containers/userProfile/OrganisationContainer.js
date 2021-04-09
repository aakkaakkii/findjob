import React, {useEffect, useState} from "react";
import Organisation from "../../components/UserProfile/Organisation";

const OrganisationContainer = () => {
    const [organisations, setOrganisations] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {

    }

    return (
        <Organisation organisations={organisations}/>
    )
};

export default OrganisationContainer;