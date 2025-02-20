<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.utils.ImageChecker"%>
<%@page import="java.util.List"%>
<%@page import="sample.product.Product"%>
<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.Cart"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="CSS/user.css" rel="stylesheet" />
    </head>
    <body>
        <c:set var="loginUser" value="${sessionScope.LOGIN_USER}" />
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${loginUser == null || loginUser.roleID ne 'US'}">
            <c:redirect url="Login.html" ></c:redirect>
        </c:if>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="MainController?action=Home">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="MainController?action=Order">Order</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex me-auto align-items-center" action="MainController" method="GET">
                        <input class="form-control me-2" type="text" name="search" value ="${param.search}" placeholder="Search for products">
                        <button class="btn btn-outline-dark" type="submit" name="action" value="Product">Search</button>
                    </form>
                    <div class="d-flex align-items-center position-relative">
                        <form class="d-flex" action="MainController">
                            <button class="btn btn-outline-dark" type="submit" name="action" value="View">
                                <i class="bi-cart-fill me-1"></i>
                                Cart
                                <c:set var="numberProduct" value="${cart.numberOfProduct()}" />
                                <span class="badge bg-dark text-white ms-1 rounded-pill">${numberProduct}</span>
                            </button>
                        </form>
                        <a href="MainController?action=Profile" class="ms-3"><img src="${loginUser.avatar}" alt="Avatar" class="avatar"></a>
                        <!-- Notification Message -->
                        <c:if test="${not empty MESSAGE}">
                            <div class="alert alert-success alert-dismissible fade show position-absolute top-100 start-50 translate-middle-x mt-2" style="max-width: 400px;" role="alert">
                                ${MESSAGE}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                        <c:if test="${not empty MESSAGE_ERROR}">
                            <div class="alert alert-warning alert-dismissible fade show position-absolute top-100 start-50 translate-middle-x mt-2" style="max-width: 400px;" role="alert">
                                ${MESSAGE_ERROR}
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Stationery Shop</h1>
                    <p class="lead fw-normal text-white-50 mb-0">Everything is here</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section >
            <div class="container px-4 px-lg-5 mt-5">
                <div class="d-flex justify-content-between mb-4">
                    <form class="d-flex" action="MainController" method="GET">
                        <input type="hidden" name="search" value="${param.search}">
                        <select class="form-select me-2" name="sortOrder" style="max-width: 200px;">
                            <option value="default">Default</option>
                            <option value="asc" ${param.sortOrder == 'asc' ? 'selected' : ''}>Price: Low to High</option> 
                            <option value="desc" ${param.sortOrder == 'desc' ? 'selected' : ''}>Price: High to Low</option>
                        </select>
                        <button class="btn btn-outline-dark" type="submit" name="action" value="Product">Sort</button>
                    </form>
                </div>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <c:set var="listProduct" value="${requestScope.LIST_PRODUCT}" />
                    <c:if test="${listProduct != null && not empty listProduct}">
                    <c:forEach var="product" items="${listProduct}">
                    <div class="col mb-5">
                        <form class="card h-100" action="MainController" method="POST">
                            <img class="card-img-top" src="${product.image}" alt="${product.name}" />
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <h5 class="fw-bolder">${product.name}</h5>
                                    <p>$${product.price}</p>
                                </div>
                                <input type="hidden" name="cmbProduct" value="${product}">
                            </div>
                            <input type="hidden" name="search" value="${param.search}"/>
                            <input type="hidden" name="sortOrder" value="${param.sortOrder}"/>
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent text-center">
                                <button class="btn btn-outline-dark mt-auto" type="submit" name="action" value="Add">Add to cart</button>
                            </div>
                        </form>
                    </div>
                            </c:forEach>
                </c:if>
                    <c:if test="${empty listProduct}">
                    <div class="alert alert-danger d-flex align-items-center" role="alert">
                        <i class="bi bi-exclamation-triangle-fill">${requestScope.NOT_FOUND_PRODUCT}</i>
                        <div>
                        </div>
                    </div>
                     </c:if>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Phuoc's Store Website 2024</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
