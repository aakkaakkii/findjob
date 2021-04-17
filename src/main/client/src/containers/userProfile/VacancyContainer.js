import React, {useEffect, useState} from "react";
import DataPanel from "../../components/UserProfile/DataPanel";
import usePanelDataRenderers from "../../hooks/usePanelDataRenderers";
import {addVacancy, deleteVacancy, getVacancy, loadOrganisationVacancies, updateVacancy} from "../../api/vacancyApi";


const VacancyContainer = ({organisationId}) => {
    const [vacancies, setVacancies] = useState([]);
    const [formVacancy, setFormVacancy] = useState({
        id: "",
        title: "",
        description: "",
        salary: "",
        vacancyType: "",
        professionTags: [],
    });
    const {vacancyRenderer} = usePanelDataRenderers();


    useEffect(() => {
        fetchVacancies(organisationId);
    },[organisationId]);

    const fetchVacancies = async (organisationId) => {
        const res = await loadOrganisationVacancies(organisationId);
        setVacancies(res.data)
    };

    const handleClose = () => {
        setFormVacancy({
            id: "",
            title: "",
            description: "",
            salary: "",
            vacancyType: "",
            professionTags: [],
        });
    };

    const submit = async () => {
        if(formVacancy.id) {
            await updateVacancy(formVacancy, formVacancy.id);
            fetchVacancies(organisationId);
        } else {
            await addVacancy(formVacancy);
            fetchVacancies(organisationId);
        }
    }

    const itemDelete = async (id) => {
        await deleteVacancy(id);
        fetchVacancies(organisationId);
    }

    const editExperience = async (id) => {
        const res = await getVacancy(id);
        setFormVacancy(res.data);
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
            title: "salary",
            dataIndex: "salary",
            type: "string"
        },
        {
            title: "vacancyType",
            dataIndex: "vacancyType",
            type: "string"
        },
        {
            title: "professionTags",
            dataIndex: "professionTags",
            type: "string"
        },
    ]

    return (
        <DataPanel
            title={"vacancies"}
            formTitle={"add vacancies"}
            listData={vacancies}
            formData={formVacancy}
            formDataDescription={dataDescription}
            setFormData={setFormVacancy}
            submitForm={submit}
            windowCloseCallback={handleClose}
            deleteListItem={itemDelete}
            editListItem={editExperience}
            dataRenderer={vacancyRenderer}
        />
    )

};

export default VacancyContainer;