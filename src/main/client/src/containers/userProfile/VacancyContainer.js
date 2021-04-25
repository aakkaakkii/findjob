import React, {useEffect, useState} from "react";
import DataPanel from "../../components/UserProfile/DataPanel";
import usePanelDataRenderers from "../../hooks/usePanelDataRenderers";
import {
    addVacancy,
    deleteVacancy,
    getVacancy,
    loadOrganisationVacancies,
    loadVacancyTypes,
    updateVacancy
} from "../../api/vacancyApi";
import {useTranslation} from "react-i18next";
import {loadProfessionTags} from "../../api/vacancyTagApi";


const VacancyContainer = ({organisationId}) => {
    const [vacancies, setVacancies] = useState([]);
    const [formVacancy, setFormVacancy] = useState({
        id: "",
        title: "",
        description: "",
        salary: "",
        vacancyType: "",
        professionTags: [],
        organisationId: organisationId
    });
    const [professionTags, setProfessionTags] = useState([]);
    const [vacancyTypes, setVacancyTypes] = useState([]);
    const {vacancyRenderer} = usePanelDataRenderers();
    const {t} = useTranslation();

    useEffect(() => {
        fetchVacancies(organisationId);
        fetchVacancyTypes();
        fetchProfessionTags();
    }, [organisationId]);

    const fetchVacancies = async (organisationId) => {
        const res = await loadOrganisationVacancies(organisationId);
        setVacancies(res.data)
    };

    const fetchVacancyTypes = async () => {
        const res = await loadVacancyTypes();
        const data = res.data;
        setVacancyTypes(data.map(d => ({data: d, label: t(d)})));
    }

    const fetchProfessionTags = async () => {
        const res = await loadProfessionTags();
        const data = res.data;
        setProfessionTags(data.map(d => ({data: d.id, label: d.title})));
    }

    const clear = () => {
        setFormVacancy({
            id: "",
            title: "",
            description: "",
            salary: "",
            vacancyType: "",
            professionTags: [],
            organisationId: organisationId
        });
    };

    const submit = async () => {
        formVacancy["organisationId"] = organisationId;
        formVacancy.vacancyType = formVacancy.vacancyType === "" ? null : formVacancy.vacancyType;
        if (formVacancy.id) {
            await updateVacancy(formVacancy, formVacancy.id);
        } else {
            await addVacancy(formVacancy);
        }
        fetchVacancies(organisationId);
        clear()
    }

    const itemDelete = async (id) => {
        await deleteVacancy(id);
        fetchVacancies(organisationId);
    }

    const editExperience = async (id) => {
        const res = await getVacancy(id);
        const data = res.data;
        setFormVacancy({
            id: data.id,
            title: data.title,
            description: data.description,
            salary: data.salary,
            vacancyType: data.vacancyType,
            professionTags: data.professionTags && data.professionTags.map(d => d.id),
            organisationId: organisationId
        });
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
            title: "salary",
            dataIndex: "salary",
            type: "string"
        },
        {
            title: "vacancyType",
            dataIndex: "vacancyType",
            type: "select",
            data: vacancyTypes,
            displayField: "data",
            valueField: "data",
        },
        {
            title: "professionTags",
            dataIndex: "professionTags",
            type: "multiSelect",
            data: professionTags,
            displayField: "label",
            valueField: "data",
        },
    ]

    return (
        <DataPanel
            title={t("vacancies")}
            formTitle={t("add vacancies")}
            listData={vacancies}
            formData={formVacancy}
            formDataDescription={dataDescription}
            setFormData={setFormVacancy}
            submitForm={submit}
            windowCloseCallback={clear}
            deleteListItem={itemDelete}
            editListItem={editExperience}
            dataRenderer={vacancyRenderer}
        />
    )

};

export default VacancyContainer;