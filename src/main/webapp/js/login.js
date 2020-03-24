$(".reg-btn").click(function () {
    showForm();
});

function showForm() {
    $(".hidden").slideToggle("slow");
    if ($("#login-btn").text() === "Login") {
        $("#login-btn").text("Register");
        $(".question").text("Already a member?");
        $(".reg-btn").text("Login");
    } else {
        $("#login-btn").text("Login");
        $(".question").text("Not a member?");
        $(".reg-btn").text("Register");
    }
}

function returnForm() {
    $('.login-box').delay(1850).slideDown("slow");

    $("input.email").val('');
    $("input.password").val('');

    if ($("#login-btn").text() === "Register") {
        $("input.firstName").val('');
        $("input.lastName").val('');
    }
}

$('#login-btn').click(function () {
    $('.login-box').slideUp("slow");
    if ($('#login-btn').text() === "Login") {
        loginListener();
    } else {
        registrationListener();
    }
});

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function loginPost(userLogin) {
    $.post("login", userLogin)
        .done(async function (data, textStatus, xhr) {
            if (xhr.status === 200) {
                $('.success-box').delay(600).fadeIn("slow");

                await sleep(1300);

                window.location = window.origin + "/IShop/cabinet";
            } else {
                alert("error while authorizing a user");

                returnForm();
            }
        })
        .fail(function () {
            alert("error while authorizing a user");
            returnForm();
        });
}

function loginListener() {
    var email = $('input.email').val();
    var password = $('input.password').val();

    if (email == '' || password == ''){
        alert("Please fill all fields!!!");
        $('.login-box').slideDown("slow");
    } else {
        var userLogin = {
            email: email,
            password: password
        }

        loginPost(userLogin);
    }
}

function registrationListener() {
    var firstName = $("input.firstName").val();
    var lastName = $("input.lastName").val();
    var email = $("input.email").val();
    var password = $("input.password").val();

    if (firstName == '' || lastName == '' || email == '' || password == '') {
        alert("Please fill all fields!!!");
        $('.login-box').slideDown("slow");
    } else if (password.length < 8) {
        alert("Password should atleast 8 character in length!!!");
        $('.login-box').slideDown("slow");
    } else {
        var userRegistration = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password
        };

        registrationPost(userRegistration);
    }
}

function registrationPost(userRegistration) {
    $.post("register", userRegistration)
        .done(async function (data, textStatus, xhr) {
            if (xhr.status === 201) {
                $("input.firstName").val('');
                $("input.lastName").val('');
                $("input.email").val('');
                $("input.password").val('');

                $('.success-box h1').text('You have successfully registered!');;

                await sleep(1000);

                window.location = window.origin + "/IShop/cabinet";
            } else {
                alert("error while creating a user");

                returnForm();
            }
        })
        .fail(function () {
            alert("error while creating a user");

            returnForm();
        });
}