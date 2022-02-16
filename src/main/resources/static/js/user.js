function deleteUser(id, user){
    let data = {
        id: id,
    }
    $.ajax({
        url: '/api/user/' + data.id,
        type: 'DELETE',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Пользователь успешно удален")
            console.log(result)
            window.location.href = "/user/list";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function newRoleUser(id){
    let userDTOUpdated = {
        id: id
    }
    $.ajax({
        url: '/api/user/' + userDTOUpdated.id,
        type: 'PATCH',
        data: JSON.stringify(userDTOUpdated),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Роль успешно изменена")
            console.log(result)
            window.location.href = "/user/list";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}