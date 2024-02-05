const URLNavbarUser = 'http://localhost:8080/api/user/authorizedInfo';
const navbarUser = document.getElementById('navbarUser');
const tableUserUser = document.getElementById('tableUser');

function getCurrentUser() {
    fetch(URLNavbarUser)
        .then((res) => res.json())
        .then((user) => {

            let rolesStringUser = rolesToStringForUser(user.roles);
            let dataOfUser = '';

            dataOfUser += `<tr>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.age}</td>
            <td>${user.username}</td>
            <td>${rolesStringUser}</td>
            </tr>`;
            tableUserUser.innerHTML = dataOfUser;
            navbarUser.innerHTML = `<b><span>${user.username}</span></b>
                             <span>with roles:</span>
                             <span>${rolesStringUser}</span>`;
        });
}



function rolesToStringForUser(roles) {
    let rolesString = '';
    for (let element of roles) {
        rolesString += (element.role.replace('ROLE_', '') + ', ');
    }
    rolesString = rolesString.substring(0, rolesString.length - 2);
    return rolesString;
}


getCurrentUser()