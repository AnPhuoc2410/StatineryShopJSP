<%-- 
    Document   : profile
    Created on : Jun 15, 2024, 10:48:56 AM
    Author     : ANPHUOC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Page</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="CSS/profile.css"/>
    </head>
    <body>
        <div class="container profile-container">
            <c:if test="${sessionScope.LOGIN_USER == null}">
                <c:redirect url="Login.html" />
            </c:if>
            <c:set var="loginUser" value="${sessionScope.LOGIN_USER}" />

            <c:choose>
                <c:when test="${not empty loginUser.avatar}">
                    <c:set var="avatarURL" value="${loginUser.avatar}" />
                </c:when>
                <c:otherwise>
                    <c:set var="avatarURL" value="img/avatar.png" />
                </c:otherwise>
            </c:choose>

            <div class="profile-header">
                <form action="UploadFileController" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="uploadType" value="user">
                    <img src="${avatarURL}" alt="Avatar" id="avatarPreview" style="cursor: pointer;">
                    <input type="file" name="upfile" id="avatarFile" class="form-control mt-2" style="display: none;" required>
                </form>
            </div>
            <h1>Welcome: ${loginUser.fullName}!</h1>
            <div class="profile-info">
                <strong>User ID:</strong> ${loginUser.userID} <br>
            </div>
            <div class="profile-info">
                <strong>Full Name:</strong> ${loginUser.fullName} <br>
            </div>
            <div class="profile-info">
                <strong>Role ID:</strong> ${loginUser.roleID} <br>
            </div>
                <h4 style="color:green">${requestScope.MESSAGE}</h4>
            <div class="text-center mt-4">
                <form action="MainController" method="POST">
                    <button type="submit" name="action" value="Logout" class="btn btn-outline-dark">Logout</button>
                </form>
            </div>
            <div class="text-center mt-4">
                <form action="MainController" method="POST">
                    <button type="submit" name="action" value="Home" class="btn btn-outline-dark">Home Page</button>
                </form>
            </div>
        </div>
        <script>
            document.getElementById('avatarPreview').addEventListener('click', function () {
                document.getElementById('avatarFile').click();
            });

            document.getElementById('avatarFile').addEventListener('change', function () {
                document.forms[0].submit();
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
