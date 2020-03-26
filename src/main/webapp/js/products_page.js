$.get("api/product")
    .done(function (products) {
        var cardsInfo = "";
        jQuery.each(products, function (i, product) {
            if (i % 4 === 0){
                cardsInfo += "<div class=\"row row-cols-1 row-cols-md-3\">";
            }

            var idLink = "window.location = window.origin + '/IShop/products?id=" + product.id + "'";

            cardsInfo +=
                "<div class=\"col-3 mb-4\">" +
                "<div class=\"card h-100\">" +
                "<img src=\"...\" class=\"card-img-top\" alt=\"...\">" +
                "<div class=\"card-body\">" +
                "<h5 class=\"card-title\">" + product.name + "</h5>" +
                "<p class=\"card-text\">" + product.description + "</p>" +
                "<p class=\"card-text\">" + product.price + "</p>" +
                "<button type=\"button\" class=\"btn btn-primary\" onclick=\" " + idLink + " \">Product info</button>" +
                "</div>" +
                "</div>" +
                "</div>";

            if ((i + 1) % 4 === 0 || i === products.length - 1){
                cardsInfo += "</div>";
            }
        });
        $(".product-cards").html(cardsInfo);

    })
    .fail(function () {
        alert("Fail");
    });
