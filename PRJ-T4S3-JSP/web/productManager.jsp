<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link rel="stylesheet" type="text/css" href="CSS/admin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <style>
            .inactive-product {
                background-color: #ffcccc; /* Light red background for inactive products */
                color: #ff0000;           /* Red text color */
            }
            .inactive-product p {
                margin: 0;
            }
            .modal-header .close {
                margin: -1rem -1rem -1rem auto;
            }
        </style>
    </head>
    <body>
        <c:if test ="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
            <c:redirect url ="Login.html"></c:redirect>
        </c:if>

        <div class="header">
            <h1>Product Management</h1>
            <div>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addProductModal">
                    Add Product
                </button>
            </div>

            <div class="search-form">
                <form action="MainController" method="POST">
                    <input type="text" name="search" value="${param.search}" placeholder="Search products..." />
                    <button type="submit" name="action" value="Product"><i class="fas fa-search"></i></button>
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
                                <button class="btn btn-outline-dark" type="submit" name="action" value="Home">UserManager</button>
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

            <c:if test="${not empty requestScope.ADD_MESS}">
                <div class="alert alert-info" role="alert">
                    ${requestScope.ADD_MESS}
                </div>
            </c:if>

            <c:if test="${requestScope.LIST_PRODUCT != null && not empty LIST_PRODUCT}">
                <table border="1" class="table table-striped">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Image</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="products" varStatus="counter" items="${requestScope.LIST_PRODUCT}">
                            <tr class="<c:if test='${products.status == 0}'>inactive-product</c:if>">
                            <form action="MainController" method="POST">
                                <td>${counter.count}</td>
                            <td>${products.productID}
                                <input type="hidden" name="productID" value="${products.productID}"/>
                            </td>
                            <td>${products.name}
                                <input type="hidden" name="name" value="${products.name}"/>
                            </td>
                            <td>$${products.price}
                                <input type="hidden" name="price" value="${products.price}"/>
                            </td>
                            <td>${products.quantity}
                                <input type="hidden" name="quantity" value="${products.quantity}"/>
                            </td>
                            <td>
                                <img style="max-width: 100px; max-height: 100px" src="${products.image}" alt="${products.name}"/>
                                <input type="hidden" name="image" value="${products.image}"/>
                            </td>
                            <td>
                                <button class="btn btn-primary" type="submit" name="action" value="EditProduct"><i class="fas fa-edit"></i></button>
                                <input type="hidden" name="search" value="${param.search}"/>
                            </td>
                            <td>
                                <c:url var="Delete" value="MainController">
                                    <c:param name="action" value="Product"></c:param>
                                    <c:param name="actionProduct" value="Delete"></c:param>
                                    <c:param name="productID" value="${products.productID}"></c:param>
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
        <c:if test="${empty requestScope.LIST_PRODUCT}">
            <div class="alert alert-danger d-flex align-items-center" role="alert">
                <i class="bi bi-exclamation-triangle-fill">${requestScope.NOT_FOUND_PRODUCT}</i>
                <div>
                </div>
            </div>
        </c:if>

        <!-- Add Product Modal -->
        <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProductModalLabel">Add Product</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="UploadFileController" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="productID">Product ID</label>
                                <input type="text" class="form-control" id="productID" name="productID" required>
                            </div>
                            <div class="form-group">
                                <label for="name">Product Name</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <input type="number" class="form-control" id="price" name="price" min="1" step="any" required>
                            </div>
                            <div class="form-group">
                                <label for="quantity">Quantity</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" min="1" required>
                            </div>
                            <div class="form-group">
                                <label for="image">Image</label>
                                <input type="file" class="form-control" id="image" name="upfile" accept="image/*" required>
                            </div>
                            <input type="hidden" name="uploadType" value="product"/>
                            <input type="hidden" name="productType" value="Add"/>
                            <button type="submit" class="btn btn-primary" name="actionProduct" value="Add">Add Product</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </body>
</html>
