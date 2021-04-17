import React, {useEffect, useState} from "react";
import {
    addExperience,
    deleteExperience,
    getExperience,
    loadCurrentUserExperiences,
    updateExperience
} from "../../api/experienceApi";
import DataPanel from "../../components/UserProfile/DataPanel";
import usePanelDataRenderers from "../../hooks/usePanelDataRenderers";

const ExperienceContainer = () => {
    const [experiences, setExperiences] = useState([]);
    const [formExperience, setFormExperience] = useState({
        id: "",
        title: "",
        description: "",
        startDate: null,
        endDate: null,
    });
    const {experienceRenderer} = usePanelDataRenderers();

    useEffect(() => {
        fetchExperience();
    },[]);

    const fetchExperience = async () => {
        const res = await loadCurrentUserExperiences();
        setExperiences(res.data)
    };

    const handleClose = () => {
        setFormExperience({
            id: "",
            title: "",
            description: "",
            startDate: null,
            endDate: null,
        });
    };

    const submit = async () => {
        if(formExperience.id) {
            await updateExperience(formExperience, formExperience.id);
            fetchExperience();
        } else {
            let res = await addExperience(formExperience);
            setExperiences([res.data, ...experiences])
        }
    }

    const itemDelete = async (id) => {
        await deleteExperience(id);
        fetchExperience();
    }

    const editExperience = async (id) => {
        const res = await getExperience(id);
        setFormExperience(res.data);
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
            title: "startDate",
            dataIndex: "startDate",
            type: "date"
        },
        {
            title: "endDate",
            dataIndex: "endDate",
            type: "date"
        },
    ]

    return (
        <DataPanel
            title={"experiences"}
            formTitle={"add experience"}
            listData={experiences}
            formData={formExperience}
            formDataDescription={dataDescription}
            setFormData={setFormExperience}
            submitForm={submit}
            windowCloseCallback={handleClose}
            deleteListItem={itemDelete}
            editListItem={editExperience}
            dataRenderer={experienceRenderer}
        />
    )

};

export default ExperienceContainer;