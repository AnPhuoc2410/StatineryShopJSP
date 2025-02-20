<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Checkout</title>        
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <style>
            body {
                padding: 20px;
            }
            .container {
                max-width: 800px;
            }
            .form-control {
                margin-bottom: 1rem;
            }
            .total-box {
                background: #f8f9fa;
                padding: 20px;
                border-radius: 5px;
            }
            .btn-primary {
                width: 100%;
            }
        </style>
        <script>
            function validateForm() {
                const phone = document.getElementById("phone").value;
                const error = document.getElementById("errorPhone");
                const phonePattern = /^(03|05|07|08|09|01[2|6|8|9])[0-9]{8}$/;

                if (!phonePattern.test(phone)) {
                    error.innerHTML = "Enter phone again";
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${cart == null || empty cart.cart}">
            <c:redirect url="ProductController"></c:redirect>
        </c:if>
        <div class="container">
            <h1 class="mb-4">Checkout</h1>

            <div class="row">
                <div class="col-md-8">
                    <h2 class="mb-3">Shipping Address</h2>
                    <form action="MainController" method="POST" onsubmit="return validateForm()">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name" required>
                            </div>
                        </div>
                        <input type="text" class="form-control" id="companyName" name="companyName" placeholder="Company name">
                        <input type="text" class="form-control" id="address" name="address" placeholder="Address" required>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" pattern="[a-zA-Z0-9._%+-]+@gmail\.com" required>
                        <input type="tel" class="form-control" id="phone" name="phone" placeholder="Phone" pattern="(03|05|07|08|09|01[2|6|8|9])[0-9]{8}" required>
                        <p id="errorPhone" style="color: red;font-size: medium" ></p>
                        <textarea class="form-control" id="additionalInfo" name="additionalInfo" placeholder="Additional information"></textarea>

                        <h2 class="mt-4">Billing Address</h2>
                        <input type="hidden" name="total" value="${param.total}"/>
                        <input type="hidden" name="userID" value="${param.userID}"/>
                        <input type="hidden" name="action" value="CheckOut"/>
                        <button type="submit" class="btn btn-outline-dark mt-4">Submit Order</button>
                    </form>
                </div>

                <div class="col-md-4">
                    <div class="total-box mb-4">
                        <h2 class="mb-3">The total amount of</h2>
                        <div class="d-flex justify-content-between">
                            <label for="amount">Total:</label>
                            <strong> <fmt:formatNumber value="${param.total}" type="currency" /></strong>
                        </div>
                        <div class="d-flex justify-content-between">
                            <span>Shipping</span>
                            <span>Free</span>
                        </div>
                        <hr>
                        <div class="d-flex justify-content-between">
                            <span>The total amount of (including VAT)</span>
                            <span>  <strong> <fmt:formatNumber value="${param.total}" type="currency" /></strong></span>
                        </div>
                        <button class="btn btn-outline-dark mt-3">Proceed to Shipping</button>
                    </div>

                    <div>
                        <h2 class="mb-3">Apply promo code</h2>
                        <div class="input-group" style="align-items: baseline;">
                            <input type="text" class="form-control" placeholder="Promo code">
                            <button class="btn btn-outline-dark" type="button">Apply</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
