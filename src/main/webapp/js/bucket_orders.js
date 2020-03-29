function addListenerToRemoveButton(){
    $("button.remove-from-bucket").click(function (event) {
        event.preventDefault();

        var bucketId = event.target.attributes["bucket-id"].value;

        $.ajax({
            url: 'api/buckets?bucketId=' + bucketId,
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

$.get("api/buckets")
    .done(function (data) {
        var bucketContent = "";
        var fullPrice = 0;

        jQuery.each(data, function (i, item) {
            bucketContent += "<div class=\"row\">\n" +
                "<div class=\"col-12 col-sm-12 col-md-2 text-center\">" +
                "<img class=\"img-responsive\" src=\"http://placehold.it/120x80\" alt=\"prewiew\" width=\"120\" height=\"80\">" +
                "</div>" +
                "<div class=\"col-12 text-sm-center col-sm-12 text-md-left col-md-6\">" +
                "<h4 class=\"product-name\"><strong>" + item.product.name + "</strong></h4>" +
                "<h4>" +
                "<small>" + item.product.description + "</small>" +
                "</h4>" +
                "</div>" +
                "<div class=\"col-12 col-sm-12 text-sm-center col-md-4 text-md-right row\">" +
                "<div class=\"col-3 col-sm-3 col-md-6 text-md-right\" style=\"padding-top: 5px\">" +
                "<h6><strong>" + item.product.price + "<span class=\"text-muted\">x</span></strong></h6>" +
                "</div>" +
                "<div class=\"col-4 col-sm-4 col-md-4\">" +
                "<div class=\"quantity\">" +
                "<input type=\"button\" value=\"+\" class=\"plus\">" +
                "<input type=\"number\" step=\"1\" max=\"99\" min=\"1\" value=\"1\" title=\"Qty\" class=\"qty\" size=\"4\">" +
                "<input type=\"button\" value=\"-\" class=\"minus\">" +
                "</div>" +
                "</div>" +
                "<div class=\"col-2 col-sm-2 col-md-2 text-right\">" +
                "<button type=\"button\" class=\"btn btn-outline-danger btn-xs remove-from-bucket\" bucket-id='" + item.id +"'>" +
                "<i class=\"fa fa-trash\" aria-hidden=\"true\"></i>" +
                "</button>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "<hr>";

            fullPrice += item.product.price;
        });

        $(".bucket-products").append(bucketContent);

        var fullPriceHtml = "Total price: <b>" + fullPrice.toFixed(2) + "</b>";
        $("#full-price").html(fullPriceHtml)
        addListenerToRemoveButton();
    })
    .fail(function () {
        alert("Fail!");
    });

$("#btn-update").click(function () {
    location.reload();
});