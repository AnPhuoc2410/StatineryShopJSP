<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="java.math.BigDecimal"%>
<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.Product"%>
<%@page import="sample.product.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Phuoc's Store - View Cart</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="CSS/viewcart.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Your Cart</h1>
            <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'US'}">
                <c:redirect url="Login.html"></c:redirect>
            </c:if>
            <c:set var="cart" value="${sessionScope.CART}" />
            <c:choose>            
                <c:when test="${cart != null && not empty cart.cart}">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="total"  value="0" />
                            <c:forEach var="product" varStatus="counter" items="${cart.cart.values()}">
                                <c:set var="total" value="${total + (product.price * product.quantity)}" />
                                <tr>
                            <form action="MainController" method="POST">
                                <td>${counter.count}</td>
                                <td>${product.productID}
                                    <input type="hidden" name="id" value="${product.productID}"/>
                                </td>
                                <td>${product.name}</td>
                                <td>
                                    <img class="product-image" src="${product.image}" alt="${product.name}" />
                                </td>
                                <td class="d-flex justify-content-center flex-column">
                                    <div class="d-flex justify-content-center">
                                        <c:url var="Decrease" value="MainController">
                                            <c:param name="action" value="Edit"/>
                                            <c:param name="id" value="${product.productID}"/>
                                            <c:param name="quantity" value="${product.quantity}"/>
                                            <c:param name="updateCart" value="dec"/>
                                        </c:url>
                                        <a class="btn btn-outline-dark" href="${Decrease}" 
                                           <c:if test="${product.quantity <= 1}">
                                               style="pointer-events: none;"
                                           </c:if>>-</a>
                                        <input type="number" name="quantity" value="${product.quantity}" min="1" class="form-control quantity-input mx-2" style="width: 70px;">
                                        <c:url var="Increase" value="MainController">
                                            <c:param name="action" value="Edit"/>
                                            <c:param name="id" value="${product.productID}"/>
                                            <c:param name="quantity" value="${product.quantity}"/>
                                            <c:param name="updateCart" value="inc"/>
                                        </c:url>
                                        <a class="btn btn-outline-dark" href="${Increase}">+</a>
                                    </div>
                                    <c:if test="${not empty requestScope.MESSAGE_PRODUCT && requestScope.ID eq product.productID}">
                                        <div class="alert alert-info" role="alert">
                                            ${requestScope.MESSAGE_PRODUCT}
                                        </div>
                                    </c:if>
                                </td>
                                <td><fmt:formatNumber value="${(product.price * product.quantity)}" type="currency" />
                                </td>
                                <td>
                                    <input type="hidden" name="id" value="${product.productID}"/>
                                    <button class="btn btn-outline-dark" type="submit" name="action" value="Edit">Edit</button>
                                </td>
                                <td>
                                    <input type="hidden" name="id" value="${product.productID}"/>
                                    <button class="btn btn-outline-dark" type="submit" name="action" value="Remove">Remove</button>
                                </td>
                            </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="total">
                        <strong>Total: <fmt:formatNumber value="${total}" type="currency" /></strong>
                    </div>
                    <div class="actions d-flex justify-content-center mt-3">
                        <a href="MainController?action=Product" class="btn btn-outline-dark mx-2">Add More</a>
                        <form action="MainController" method="POST" class="mx-2">
                            <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.userID}"/>
                            <input type="hidden" name="total" value="${total}"/>
                            <input type="hidden" name="order" value="Order"/>
                            <button type="submit" name="action" value="View" class="btn btn-outline-dark">Order</button>
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <p class="mt-3">Your cart is empty. <a href="MainController?action=Product" class="btn btn-outline-dark">Start Shopping</a></p>

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
