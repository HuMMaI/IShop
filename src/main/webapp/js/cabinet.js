$(".info-edit").click(function (event) {
    event.preventDefault();

    var btnId = $(this).attr("id");

    switch (btnId) {
        case "edit-btn":
            editBtnListener();
            break;

        case "cancel-btn":
        case "save-btn":
            cancelAndSaveBtnListener(btnId);
            break;
    }
});

function editBtnListener(){
    $("#save-btn").removeClass("hidden");
    $("#cancel-btn").removeClass("hidden");
    $("#edit-btn").addClass("hidden");

    $("ul.about").children().css("height", "52px");
    $("#bio-div").css("height", "107px");

    $("#name-inputs").removeClass("hidden");
    $("#username-input").removeClass("hidden");
    $("#bio-area").removeClass("hidden");
    $("#phone-input").removeClass("hidden");
    $("#address-input").removeClass("hidden");
    $("#email-input").removeClass("hidden");
    $("#age-input").removeClass("hidden");
    $("#gender-select").removeClass("hidden");
    $("#prof-select").removeClass("hidden");

    $("#name-span").addClass("hidden");
    $("#username-span").addClass("hidden");
    $("#bio-span").addClass("hidden");
    $("#phone-span").addClass("hidden");
    $("#address-span").addClass("hidden");
    $("#email-span").addClass("hidden");
    $("#age-span").addClass("hidden");
    $("#gender-span").addClass("hidden");
    $("#prof-span").addClass("hidden");
}

function cancelAndSaveBtnListener(btnId) {
    $("#save-btn").addClass("hidden");
    $("#cancel-btn").addClass("hidden");
    $("#edit-btn").removeClass("hidden");

    $("ul.about").children().removeAttr("style");
    $("#bio-div").removeAttr("style");

    $("#name-span").removeClass("hidden");
    $("#username-span").removeClass("hidden");
    $("#bio-span").removeClass("hidden");
    $("#phone-span").removeClass("hidden");
    $("#address-span").removeClass("hidden");
    $("#email-span").removeClass("hidden");
    $("#age-span").removeClass("hidden");
    $("#gender-span").removeClass("hidden");
    $("#prof-span").removeClass("hidden");

    $("#name-inputs").addClass("hidden");
    $("#username-input").addClass("hidden");
    $("#bio-area").addClass("hidden");
    $("#phone-input").addClass("hidden");
    $("#address-input").addClass("hidden");
    $("#email-input").addClass("hidden");
    $("#age-input").addClass("hidden");
    $("#gender-select").addClass("hidden");
    $("#prof-select").addClass("hidden");

    if (btnId === "save-btn"){
        var email = $("input[name='email']").val();
        var firstName = $("input[name='firstName']").val();
        var lastName = $("input[name='lastName']").val();
        var username = $("input[name='username']").val();
        var bio = $("textarea[name='bio']").val();
        var phoneNumber = $("input[name='phoneNumber']").val();
        var address = $("input[name='address']").val();
        var age = $("input[name='age']").val();
        var gender = $("select[name='gender']").val();
        var profession = $("select[name='profession']").val();

        var edit = {
            email,
            firstName,
            lastName,
            username,
            bio,
            phoneNumber,
            address,
            age,
            gender,
            profession
        }

        $.post("cabinet", edit)
            .done(function () {
                alert("Success!!");

                location.reload();
            })
            .fail(function () {
                alert("Fail!!")
            });
    }
}