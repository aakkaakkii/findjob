import React, {useEffect, useState} from "react";
import OrganisationPage from "../components/organisation/OrganisationPage";
import {useParams} from "react-router-dom";
import {getOrganisation} from "../api/organisationApi";

const OrganisationContainer = () => {
    const [organisation, setOrganisation] = useState({
        id: "",
        title: "",
        description: "",
        address: "",
        phone: "",
        mail: "",
        website: "",
        vacancies: []
    });
    const { id } = useParams();

    useEffect(() => {
        fetchData();
    },[]);

    const fetchData = async () => {
        const res = await getOrganisation(id);
        setOrganisation(res.data)
    };

    return (
       <OrganisationPage organisation={organisation}/>
    )

};

export default OrganisationContainer;