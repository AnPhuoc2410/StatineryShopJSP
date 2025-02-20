<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Phuoc's Store - View Order</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="CSS/admin.css"/>
    </head>
    <body>
        <div class="container">
            <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'US'}">
                <c:redirect url="Login.html"></c:redirect>
            </c:if>

            <h1>Your Orders</h1>
            <c:set var="orders" value="${requestScope.ORDERS}" />
            <c:choose>
                <c:when test="${orders != null && not empty orders}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Order Date</th>
                                <th>Total Amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" varStatus="counter" items="${ORDERS}">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${order.orderID}</td>
                                    <td>${order.date}</td>
                                    <td>${order.total}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="mt-3">No Orders. <a href="MainController?action=Product" class="btn btn-outline-dark">Start Shopping</a></p>
                </c:otherwise>
            </c:choose>
            <c:if test="${not empty requestScope.MESSAGE_REMOVE}">
                <div class="alert alert-warning" role="alert">
                    ${MESSAGE_REMOVE}
                </div>
            </c:if>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
