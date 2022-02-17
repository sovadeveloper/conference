function addSchedule(){
    let start = document.getElementById("start").value;
    let end = document.getElementById("end").value;
    let talk_id = Number(document.getElementsByClassName("talk")[0].value);
    let room_id = Number(document.getElementsByClassName("room")[0].value);
    let scheduleDTO = {
        dateStart: start,
        dateEnd: end,
        talk: {
                id: talk_id
        },
        room: {
                id: room_id
            }
    }
    $.ajax({
        url: '/api/schedule',
        type: 'POST',
        data: JSON.stringify(scheduleDTO),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Расписание успешно обновлено")
            console.log(result)
            window.location.href = "/talk/" + talk_id;
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}