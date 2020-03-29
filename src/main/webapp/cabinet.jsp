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
    <link rel="stylesheet" href="css/profile_page.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/4.4.95/css/materialdesignicons.css">

    <title>Hello, world!</title>
</head>
<body>
<div class="page-wrapper chiller-theme">
    <jsp:include page="user_panel.jsp"></jsp:include>
    <!-- sidebar-wrapper  -->
    <main class="page-content">
        <div class="container-fluid">
            <div class="main-panel">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 grid-margin stretch-card">
                            <div class="card">
                                <div class="profile-card">
                                    <div class="profile-header">
                                        <div class="cover-image">
                                            <img src="https://cdn.pixabay.com/photo/2019/10/19/14/16/away-4561518_960_720.jpg" class="img img-fluid">
                                        </div>
                                        <div class="user-image">
                                            <img class="img-responsive img-rounded"
                                                 src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg"
                                                 alt="User picture">
                                        </div>
                                    </div>

                                    <div class="profile-content">
                                        <div class="profile-name"><%=session.getAttribute("firstName")%> <%=session.getAttribute("lastName")%></div>
                                        <div class="profile-designation"><%=session.getAttribute("role")%></div>
                                        <p class="profile-description"><%=session.getAttribute("bio")%></p>
                                        <ul class="profile-info-list">
                                            <a href="" class="profile-info-list-item"><i class="mdi mdi-account"></i>About</a>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-8 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <p class="card-title font-weight-bold">About</p>
                                    <hr>
                                    <p class="card-description">User Information</p>
                                    <ul class="about user-information">
                                        <li class="about-items">
                                            <i class="mdi mdi-account icon-sm "></i>
                                            <span class="about-item-name">Name:</span>
                                            <span class="about-item-detail" id="name-span">
                                                <%=session.getAttribute("firstName")%> <%=session.getAttribute("lastName")%>
                                            </span>
                                            <div class="input-group input-group-sm mb-3 hidden" id="name-inputs">
                                                <input type="text" class="form-control" aria-describedby="inputGroup-sizing-sm" value="<%=session.getAttribute("firstName")%>" name="firstName">
                                                <input type="text" class="form-control" aria-describedby="inputGroup-sizing-sm" value="<%=session.getAttribute("lastName")%>" name="lastName">
                                            </div>
                                        </li>
                                        <li class="about-items">
                                            <i class="mdi mdi-mail-ru icon-sm "></i>
                                            <span class="about-item-name">username:</span>
                                            <span class="about-item-detail" id="username-span">
                                                <%=session.getAttribute("username")%>
                                            </span>
                                            <div class="input-group input-group-sm mb-3 hidden" id="username-input">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text" id="addon-wrapping">@</span>
                                                </div>
                                                <input type="text" class="form-control" aria-describedby="inputGroup-sizing-sm" name="username" value="<%=session.getAttribute("username")%>">
                                            </div>
                                        </li>
                                        <li class="about-items" id="bio-div">
                                            <i class="mdi mdi-format-align-left icon-sm "></i>
                                            <span class="about-item-name">Bio:</span>
                                            <span class="about-item-detail" id="bio-span">
                                                <%=session.getAttribute("bio")%>
                                            </span>
                                            <div class="hidden" id="bio-area">
                                                <textarea class="form-control" rows="3" name="bio">
                                                    <%=session.getAttribute("bio")%>
                                                </textarea>
                                            </div>
                                        </li>
                                    </ul>
                                    <p class="card-description">Contact Information</p>
                                    <ul class="about">
                                        <li class="about-items">
                                            <i class="mdi mdi-phone icon-sm "></i>
                                            <span class="about-item-name">Phone:</span>
                                            <span class="about-item-detail" id="phone-span">
                                                <%=session.getAttribute("phoneNumber")%>
                                            </span>
                                            <div class="input-group input-group-sm mb-3 hidden" id="phone-input">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">+</span>
                                                </div>
                                                <input type="tel" class="form-control" aria-describedby="inputGroup-sizing-sm" name="phoneNumber" value="<%=session.getAttribute("phoneNumber")%>">
                                            </div>
                                        </li>
                                        <li class="about-items">
                                            <i class="mdi mdi-map-marker icon-sm "></i>
                                            <span class="about-item-name">Address:</span>
                                            <span class="about-item-detail" id="address-span">
                                                <%=session.getAttribute("address")%>
                                            </span>
                                            <div class="input-group input-group-sm mb-3 hidden" id="address-input">
                                                <input type="text" class="form-control" aria-describedby="inputGroup-sizing-sm" name="address" value="<%=session.getAttribute("address")%>">
                                            </div>
                                        </li>
                                        <li class="about-items">
                                            <i class="mdi mdi-email-outline icon-sm "></i>
                                            <span class="about-item-name">Email:</span>
                                            <span class="about-item-detail" id="email-span">
                                                <%=session.getAttribute("email")%>
                                            </span>
                                            <div class="input-group input-group-sm mb-3 hidden" id="email-input">
                                                <input type="email" class="form-control" aria-describedby="inputGroup-sizing-sm" name="email" value="<%=session.getAttribute("email")%>">
                                            </div>
                                        </li>
                                    </ul>
                                    <p class="card-description">Basic Information</p>
                                    <ul class="about">
                                        <li class="about-items">
                                            <i class="mdi mdi-cake icon-sm "></i>
                                            <span class="about-item-name">Age:</span>
                                            <span class="about-item-detail" id="age-span">
                                                <%=session.getAttribute("age")%>
                                            </span>
                                            <div class="input-group input-group-sm mb-3 hidden" id="age-input">
                                                <input type="number" class="form-control" aria-describedby="inputGroup-sizing-sm" min="1" max="100" name="age" value="<%=session.getAttribute("age")%>">
                                            </div>
                                        </li>
                                        <li class="about-items">
                                            <i class="mdi mdi-account icon-sm "></i>
                                            <span class="about-item-name">Gender:</span>
                                            <span class="about-item-detail" id="gender-span">
                                                <%=session.getAttribute("gender")%>
                                            </span>
                                            <select class="form-control form-control-sm hidden" id="gender-select" name="gender">
                                                <option>None</option>
                                                <option>Male</option>
                                                <option>Female</option>
                                            </select>
                                        </li>
                                        <li class="about-items">
                                            <i class="mdi mdi-clipboard-account icon-sm "></i>
                                            <span class="about-item-name">Profession:</span>
                                            <span class="about-item-detail" id="prof-span">
                                                <%=session.getAttribute("profession")%>
                                            </span>
                                            <select class="form-control form-control-sm hidden" id="prof-select" name="profession">
                                                <option>None</option>
                                                <option>Student</option>
                                                <option>Worker</option>
                                            </select>
                                        </li>
                                    </ul>
                                    <div>
                                        <button type="button" class="btn btn-outline-success float-left info-edit hidden" id="save-btn">Save</button>
                                        <button type="button" class="btn btn-outline-danger float-left info-edit hidden" id="cancel-btn">Cancel</button>

                                        <button type="button" class="btn btn-outline-secondary float-right info-edit" id="edit-btn">Edit</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="js/cabinet.js"></script>
</body>
</html>