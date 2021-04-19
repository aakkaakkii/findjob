import React, {useEffect, useState} from "react";
import {addEducation, deleteEducation, getEducation, loadCurrentUserEducations} from "../../api/educationApi";
import DataPanel from "../../components/UserProfile/DataPanel";
import usePanelDataRenderers from "../../hooks/usePanelDataRenderers";
import {updateExperience} from "../../api/experienceApi";
import {useTranslation} from "react-i18next";


const EducationContainer = () => {
    const [educations, setEducations] = useState([]);
    const [formEducation, setFormEducation] = useState({
        id: "",
        school: "",
        degree: "",
        startDate: null,
        endDate: null,
    });
    const {educationRenderer} = usePanelDataRenderers();
    const {t} = useTranslation();

    useEffect(() => {
        fetchEducation();
    },[]);

    const fetchEducation = async () => {
        const res = await loadCurrentUserEducations();
        setEducations(res.data)
    };

    const handleClose = () => {
        setFormEducation({
            id: "",
            school: "",
            degree: "",
            startDate: null,
            endDate: null,
        });
    };

    const submit = async () => {
        if(formEducation.id) {
            await updateExperience(formEducation, formEducation.id);
            fetchEducation();
        } else {
            let res = await addEducation(formEducation);
            setEducations([...educations, res.data])
        }
    }

    const itemDelete = async (id) => {
        await deleteEducation(id);
        fetchEducation();
    }

    const editExperience = async (id) => {
        const res = await getEducation(id);
        setFormEducation(res.data);
    }


    const dataDescription = [
        {
            title: "school",
            dataIndex: "school",
            type: "string"
        },
        {
            title: "degree",
            dataIndex: "degree",
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
            title={t("educations")}
            formTitle={t("add educations")}
            listData={educations}
            formData={formEducation}
            formDataDescription={dataDescription}
            setFormData={setFormEducation}
            submitForm={submit}
            windowCloseCallback={handleClose}
            deleteListItem={itemDelete}
            editListItem={editExperience}
            dataRenderer={educationRenderer}
        />
    )

};

export default EducationContainer;