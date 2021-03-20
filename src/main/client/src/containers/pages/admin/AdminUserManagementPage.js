import react from 'react'
import {makeStyles} from "@material-ui/core/styles";
import UserManagement from "../../../components/admin/UserManagement/UserManagement";

const useStyles = makeStyles((theme) => ({
    root: {},
}));


const AdminUserManagementPage = () => {
    const classes = useStyles();


    return <div>
        <UserManagement/>
    </div>
};

export default AdminUserManagementPage;