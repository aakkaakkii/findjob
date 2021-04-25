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
import {useTranslation} from "react-i18next";

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
    const {t} = useTranslation();

    useEffect(() => {
        fetchExperience();
    },[]);

    const fetchExperience = async () => {
        const res = await loadCurrentUserExperiences();
        setExperiences(res.data)
    };

    const clear = () => {
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
        } else {
            let res = await addExperience(formExperience);
            setExperiences([...experiences, res.data])
        }
        clear();
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
            type: "text"
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
            title={t("experiences")}
            formTitle={t("add experience")}
            listData={experiences}
            formData={formExperience}
            formDataDescription={dataDescription}
            setFormData={setFormExperience}
            submitForm={submit}
            windowCloseCallback={clear}
            deleteListItem={itemDelete}
            editListItem={editExperience}
            dataRenderer={experienceRenderer}
        />
    )

};

export default ExperienceContainer;