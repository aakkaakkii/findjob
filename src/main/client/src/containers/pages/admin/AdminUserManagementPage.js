import {useEffect, useState} from 'react'
import UserManagement from "../../../components/admin/UserManagement/UserManagement";
import {loadRoles, loadUsers} from "../../../api/admin/userManagementApi";


const AdminUserManagementPage = () => {
    const [users, setUsers] = useState([]);
    const [roles, setRoles] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        let usersResponse = await loadUsers();
        setUsers(usersResponse.data);
        let rolesResponse = await loadRoles();
        setRoles(rolesResponse.data)
    }

    const selectUser = (id) => {
        let user = users.find(u => u.id === id);
        setSelectedUser(user);
    };

    return <div>
        <UserManagement roles={roles} users={users} selectedUser={selectedUser} selectUser={selectUser}/>
    </div>
};

export default AdminUserManagementPage;