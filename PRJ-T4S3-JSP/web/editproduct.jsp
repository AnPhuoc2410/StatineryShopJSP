<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Product</title>
    <link rel="icon" type="image/x-icon" href="img/icon.png" />
    <link rel="stylesheet" type="text/css" href="CSS/admin.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>
<body>
    <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
        <c:redirect url="Login.html" />
    </c:if>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Edit Product</h3>
                    </div>
                    <div class="card-body">
                        <c:if test="${param.productID != null}">
                            <!-- File upload form -->
                            <form action="UploadFileController" method="POST" enctype="multipart/form-data">
                                <div class="mb-3">
                                    <label for="image" class="form-label">Image:</label>
                                    <img style="max-width: 100px; max-height: 100px; cursor: pointer" src="${param.image}" alt="${param.name}" id="avatarPreview">
                                    <input type="file" name="upfile" id="avatarFile" class="form-control mt-2" style="display: none;">
                                </div>
                                <input type="hidden" name="productType" value="Edit">
                                <input type="hidden" name="productID" value="${param.productID}">
                                <input type="hidden" name="uploadType" value="product">
                                <input type="hidden" name="name" value="${param.name}">
                                <input type="hidden" name="price" value="${param.price}">
                                <input type="hidden" name="quantity" value="${param.quantity}">
                            </form>

                            <!-- Product details form -->
                            <form action="MainController" method="POST">
                                <input type="hidden" name="action" value="Product">
                                <input type="hidden" name="actionProduct" value="Edit">
                                <input type="hidden" name="image" value="${param.image}">
                                <input type="hidden" name="productID" value="${param.productID}">
                                <div class="mb-3">
                                    <label for="productName" class="form-label">Product Name:</label>
                                    <input type="text" name="name" class="form-control" value="${param.name}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="price" class="form-label">Price:</label>
                                    <input type="number" name="price" class="form-control" value="${param.price}" min="0" step="any" required>
                                </div>
                                <div class="mb-3">
                                    <label for="quantity" class="form-label">Quantity:</label>
                                    <input type="number" name="quantity" class="form-control" value="${param.quantity}" min="0" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Update Product</button>
                            </form>

                            <!-- Success message -->
                            <c:if test="${not empty requestScope.UPDATE_MESS}">
                                <div class="alert alert-info mt-3" role="alert">
                                    ${requestScope.UPDATE_MESS}
                                </div>
                            </c:if>
                        </c:if>

                        <!-- Back button -->
                        <c:url var="Back" value="MainController">
                            <c:param name="search" value="${param.search}"></c:param>
                            <c:param name="action" value="Product"></c:param>
                        </c:url>
                        <a href="${Back}" class="btn btn-secondary mt-3">Back to Products</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="JS/admin.js"></script>
    <script>
        document.getElementById('avatarPreview').addEventListener('click', function () {
            document.getElementById('avatarFile').click();
        });

        document.getElementById('avatarFile').addEventListener('change', function () {
            document.forms[0].submit();
        });
    </script>
</body>
</html>
