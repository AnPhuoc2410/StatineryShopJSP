<%-- 
    Document   : admin
    Created on : May 29, 2024, 2:31:22 PM
    Author     : ANPHUOC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Page</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link rel="stylesheet" type="text/css" href="CSS/admin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
        <style>
            .inactive-product {
                background-color: #ffcccc;
                color: #ff0000;
            }
            .inactive-product p {
                margin: 0;
            }
        </style>
    </head>
    <body>
        <c:if test ="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
            <c:redirect url ="Login.html"></c:redirect>
        </c:if>
        <div class="header">
            <h1>Welcome, ${sessionScope.LOGIN_USER.fullName}</h1>
            <div class="search-form">
                <form action="MainController" method="POST">
                    <input type="text" name="search" value="${param.search}" placeholder="Search users..." />
                    <button  type="submit" name="action" value="Search"><i class="fas fa-search"></i></button>
                </form>
            </div>
            <div class="avatar-container">
                <img src="${sessionScope.LOGIN_USER.avatar}" alt="Avatar" class="avatar" id="avatar">
                <div class="dropdown-menu" id="dropdownMenu">
                    <ul>
                        <li>
                            <form action="MainController">
                                <button class="btn btn-outline-dark" type="submit" name="action" value="Profile">Profile</button>
                            </form>
                        </li>
                        <li>
                           <form action="MainController">
                                <button class="btn btn-outline-dark" type="submit" name="action" value="Product">ProductsManager</button>
                            </form>
                        </li>
                        <li>
                            <form action="MainController" method="POST">
                                <button class="btn btn-outline-dark" type="submit" name="action" value="Logout">Logout</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="container">
            <h3 class="error_message">${requestScope.ERROR}</h3>
            <c:if test="${requestScope.LIST_USER != null && not empty LIST_USER}">
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Password</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                            <tr class="<c:if test='${user.status == 0}'>inactive-product</c:if>">
                        <form action="MainController" method="POST">
                            <td>${counter.count}</td>
                            <td>${user.userID}
                                <input type="hidden" name="userID" value="${user.userID}">
                            </td>
                            <td>
                                <input type="text" name="fullName" value="${user.fullName}" required>
                            </td>
                            <td>
                                <input type="text" name="roleID" value="${user.roleID}" required>
                            </td>
                            <td>${user.password}</td>
                            <td>
                                <button type="submit" name="action" value="Update"><i class="fas fa-edit"></i></button>
                                <input type="hidden" name="search" value="${param.search}">
                                <input type="hidden" name="avatar" value="${user.avatar}">
                                <input type="hidden" name="authType" value="${user.authType}">
                            </td>
                            <td>
                                <c:url var="Delete" value="MainController">
                                    <c:param name="action" value="Delete"></c:param>
                                    <c:param name="userID" value="${user.userID}"></c:param>
                                    <c:param name="search" value="${param.search}"></c:param>
                                </c:url>
                                <a href="${Delete}" class="delete-button"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

        <script src="JS/admin.js"></script>
    </body>
</html>


