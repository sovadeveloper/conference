function removeUserFromTalk(id, user){
    let talkDTOUpdated = {
        id: id,
        users: [
            {
                id: user
            }
]
    }
    $.ajax({
        url: '/api/talk/' + talkDTOUpdated.id,
        type: 'PATCH',
        data: JSON.stringify(talkDTOUpdated),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Пользователь успешно удален из доклада")
            console.log(result)
            window.location.href = "/talk/list";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}