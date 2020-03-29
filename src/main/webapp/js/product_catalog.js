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

    if (name == '' || description == '' || price == '') {
        alert("Please, fill all fields!");
    } else {
        var productInfo = {
            name,
            description,
            price
        }

        $.post("add-product", productInfo)
            .done(function (data, textStatus, xhr) {
                if (xhr.status == 200) {
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


$.get("api/product-catalog")
    .done(function (data) {
        var newRow = "";

        jQuery.each(data, function (i, item) {
            newRow += "<tr style='color: white'>" +
                "<td>" + item.id + "</td>" +
                "<td>" + item.name + "</td>" +
                "<td>" + item.description + "</td>" +
                "<td>" + item.price + "</td>" +
                "<td>" +
                "<button type=\"button\" class=\"btn btn-outline-danger btn-xs remove-product\" product-id='" + item.id + "'>" +
                "<i class=\"fa fa-trash\" aria-hidden=\"true\"></i>" +
                "</button>" +
                "</td>" +
                "</tr>";
        });

        $("#product-items").html(newRow);
        addListenerToRemoveButton();
    })
    .fail(function () {
        alert("Fail");
    });

function addListenerToRemoveButton() {
    $("button.remove-product").click(function (event) {
        event.preventDefault();

        var productId = event.target.attributes["product-id"].value;

        $.ajax({
            url: 'api/product-catalog?productId=' + productId,
            type: 'DELETE'
        })
            .done(function () {
                alert("Success!!");
                location.reload();
            })
            .fail(function () {
                alert("Fail!!");
            });
    });
}