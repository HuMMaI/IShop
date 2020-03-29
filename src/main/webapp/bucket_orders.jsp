<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/cabinet.css">
    <link rel="stylesheet" href="css/bucket_orders.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
          integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<div class="page-wrapper chiller-theme">
    <jsp:include page="user_panel.jsp"></jsp:include>
    <!-- sidebar-wrapper  -->
    <main class="page-content">
        <div class="container-fluid">
            <div class="container">
                <div class="card shopping-cart">
                    <div class="card-header bg-dark text-light">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                        Shipping cart
                    </div>
                    <div class="card-body">
                        <div class="bucket-products">

                        </div>

                        <div class="pull-right">
                            <a href="" class="btn btn-outline-secondary float-right" id="btn-update">
                                Update shopping cart
                            </a>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="coupon col-md-5 col-sm-5 no-padding-left float-left">
                            <div class="row">
                                <div class="col-6">
                                    <input type="text" class="form-control" placeholder="cupone code">
                                </div>
                                <div class="col-6">
                                    <input type="submit" class="btn btn-default" value="Use cupone">
                                </div>
                            </div>
                        </div>
                        <div class="pull-right" style="margin: 10px">
                            <a href="" class="btn btn-success float-right">Checkout</a>
                            <div class="float-right" id="full-price" style="margin: 5px">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <!-- page-content" -->
</div>
<!-- page-wrapper -->

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="js/bucket_orders.js"></script>
</body>
</html>