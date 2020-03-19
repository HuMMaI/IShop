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

$('#login-btn').click(function () {
    $('.login-box').slideUp("slow");
    if ($('#login-btn').text() === "Login") {
        var email = $('input.email').val();
        var password = $('input.password').val();

        if (email == '' || password == ''){
            alert("Please fill all fields!!!");
            $('.login-box').slideDown("slow");
        } else {
            var userLogin = {
                email,
                password
            }

            $.post("login", userLogin)
                .done(async function (data, textStatus, xhr) {
                    if (xhr.status === 200) {
                        $('.success-box').delay(600).fadeIn("slow");

                        await sleep(1300);

                        var info = {
                            email
                        };

                        $.post("cabinet", info, function () {

                        }).done(function () {
                                window.location.href = "cabinet.jsp";
                            });
                    } else {
                        alert("error while authorizing a user");
                    }
                })
                .fail(function () {
                    alert("error while authorizing a user");
                });
        }
    } else {
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
                firstName,
                lastName,
                email,
                password
            };

            $.post("register", userRegistration)
                .done(function (data, textStatus, xhr) {
                    if (xhr.status === 201) {
                        $("input.firstName").val('');
                        $("input.lastName").val('');
                        $("input.email").val('');
                        $("input.password").val('');

                        $('.success-box h1').text('You have successfully registered!');
                        $('.success-box').delay(600).fadeIn("slow");
                        $('.success-box').delay(600).fadeOut(550);

                        $('.login-box').delay(1850).slideDown("slow");

                        showForm();
                    } else {
                        alert("error while creating a user");
                    }
                })
                .fail(function () {
                    alert("error while creating a user");
                });
        }
    }
});

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}