import React, {useEffect, useState} from "react";
import DataPanel from "../../components/UserProfile/DataPanel";
import usePanelDataRenderers from "../../hooks/usePanelDataRenderers";
import {
    addOrganisation,
    deleteOrganisation, getOrganisation,
    loadCurrentUserOrganisations,
    updateOrganisation
} from "../../api/organisationApi";


const OrganisationContainer = () => {
    const [organisations, setOrganisations] = useState([]);
    const [formOrganisation, setFormOrganisation] = useState({
        id: "",
        title: "",
        description: "",
        address: "",
        phone: "",
        mail: "",
        website: "",
    });
    const {organisationRenderer} = usePanelDataRenderers();


    useEffect(() => {
        fetchOrganisations();
    },[]);

    const fetchOrganisations = async () => {
        const res = await loadCurrentUserOrganisations();
        setOrganisations(res.data)
    };

    const handleClose = () => {
        setFormOrganisation({
            id: "",
            title: "",
            description: "",
            address: "",
            phone: "",
            mail: "",
            website: "",
        });
    };

    const submit = async () => {
        if(formOrganisation.id) {
            await updateOrganisation(formOrganisation, formOrganisation.id);
            fetchOrganisations();
        } else {
            let res = await addOrganisation(formOrganisation);
            setOrganisations([res.data, ...organisations])
        }
    }

    const itemDelete = async (id) => {
        await deleteOrganisation(id);
        fetchOrganisations();
    }

    const editOrganisation = async (id) => {
        const res = await getOrganisation(id);
        setFormOrganisation(res.data);
    }


    const dataDescription = [
        {
            title: "title",
            dataIndex: "title",
            type: "string"
        },
        {
            title: "description",
            dataIndex: "description",
            type: "string"
        },
        {
            title: "address",
            dataIndex: "address",
            type: "string"
        },
        {
            title: "phone",
            dataIndex: "phone",
            type: "string"
        },
        {
            title: "mail",
            dataIndex: "mail",
            type: "string"
        },
        {
            title: "website",
            dataIndex: "website",
            type: "string"
        },
    ]

    return (
        <DataPanel
            title={"organisations"}
            formTitle={"add organisation"}
            listData={organisations}
            formData={formOrganisation}
            formDataDescription={dataDescription}
            setFormData={setFormOrganisation}
            submitForm={submit}
            windowCloseCallback={handleClose}
            deleteListItem={itemDelete}
            editListItem={editOrganisation}
            dataRenderer={organisationRenderer}
        />
    )

};

export default OrganisationContainer;