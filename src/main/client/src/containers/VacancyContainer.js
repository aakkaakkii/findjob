import React from "react";
import {useParams} from "react-router-dom";

const VacancyContainer = () => {
    let { id } = useParams();

    return (
        <div>
            {id}
        </div>
    )
};

export default VacancyContainer;