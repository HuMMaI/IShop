$.get("api/buckets")
    .done(function (data) {
        var tableContent = "";

        jQuery.each(data, function (i, item) {
            // var number = i + 1;

            tableContent += "<tr style='color: white'>" +
                "<td>" + (i + 1) + "</td>" +
                "<td>" + item.product.name + "</td>" +
                "<td>" + item.product.description + "</td>" +
                "<td>" + item.product.price + "</td>" +
                "<td>" + item.purchaseDate + "</td>" +
                "<td>" +
                    "<button type='button' class='btn btn-primary remove-from-bucket' bucket-id='" + item.id + "'>Remove</button>" +
                "</td>" +
                "</tr>";
        });

        $("#bucket-items").html(tableContent);
        addListenerToRemoveButton();
    })
    .fail(function () {
        alert("Fail!");
    });

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