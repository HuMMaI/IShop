$("#add-product-btn").click(function () {
    $(".product-form").addClass("active");
    $(".product-list").addClass("active");
});

$(".close-form").click(function () {
    $(".product-form").removeClass("active");
    $(".product-list").removeClass("active");
});

$("#add-btn").click(function (event) {
    event.preventDefault()

    var name = $("#product-name").val();
    var description = $("#product-description").val();
    var price = $("#product-price").val();

    if (name == '' || description == '' || price == ''){
        alert("Please, fill all fields!");
    } else {
        var productInfo = {
            name,
            description,
            price
        }

        $.post("add-product", productInfo)
            .done(function (data, textStatus, xhr) {
                if (xhr.status == 200){
                    $("#product-name").val('');
                    $("#product-description").val('');
                    $("#product-price").val('');

                    getProducts();
                } else {
                    alert("Error");
                }
            })
            .fail(function () {
                alert("Fail");
            });
    }
});

$(document).ready(function () {
    getProducts();
});

function getProducts (){
    if ($("#product-items").children().length !== 0){
        $("#product-items").empty();
    }

    $.getJSON("add-product")
        .done(function (data) {
            for (var i = 0; i < data.length; i++){
                var $newRow = $("<tr style='color: white'>" +
                    "<td>" + data[i].id + "</td>" +
                    "<td>" + data[i].name + "</td>" +
                    "<td>" + data[i].description + "</td>" +
                    "<td>" + data[i].price + "</td>" +
                    "<td> SOON </td>" +
                    "</tr>");

                $("#product-items").append($newRow);
            }
        })
        .fail(function () {
            alert("Fail");
        });
}