const URL = 'http://localhost:8080/api/admin/authorizedInfo';
const navbarAdmin = document.getElementById('navbarAdmin');
const tableUserAdmin = document.getElementById('tableAdmin');

function getCurrentAdmin() {
    fetch(URL)
        .then((res) => res.json())
        .then((admin) => {

            let rolesStringAdmin = rolesToStringForAdmin(admin.roles);
            let data = '';

            data += `<tr>
            <td>${admin.id}</td>
            <td>${admin.firstname}</td>
            <td>${admin.lastname}</td>
            <td>${admin.age}</td>
            <td>${admin.username}</td>
            <td>${rolesStringAdmin}</td>
            </tr>`;
            tableUserAdmin.innerHTML = data;
            navbarAdmin.innerHTML = `<b><span>${admin.username}</span></b>
                             <span>with roles:</span>
                             <span>${rolesStringAdmin}</span>`;
        });
}

getCurrentAdmin()

function rolesToStringForAdmin(roles) {
    let rolesString = '';

    for (const element of roles) {
        rolesString += (element.role.toString().replace('ROLE_', '') + ', ');
    }
    rolesString = rolesString.substring(0, rolesString.length - 2);
    return rolesString;
}
async function getUserById(id) {
    let response = await fetch("http://localhost:8080/api/admin/users/" + id);
    return await response.json();
}

async function open_fill_modal(form, modal, id) {
    modal.show();
    let user = await getUserById(id);
    form.id.value = user.id;
    form.firstname.value = user.firstname;
    form.lastname.value = user.lastname;
    form.age.value = user.age;
    form.username.value = user.username;
    form.password.value = user.password;
    form.roles.value = user.roles;
}

