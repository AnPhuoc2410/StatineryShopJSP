<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link rel="stylesheet" type="text/css" href="CSS/register.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
    </head>
    <body>
    <c:set var="userError" value="${requestScope.USER_ERROR}"/>
        <div class="container">
            <div class="sign-up">
                <form action="MainController" method="POST">
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http://localhost:8084/PRJ-T4S3-JSP/LoginGoogleController&response_type=code&client_id=236083210339-e34o03qvk4q8i8i8u5frttp2gmkkemvb.apps.googleusercontent.com&approval_prompt=force" class="social" type="submit">
                            <i class="fab fa-google-plus-g"></i>
                        </a>
                        <a href="#" class="social"><i class="fab fa-github"></i></a>
                    </div>
                    <p>or enter your information for registration</p>
                    User ID: <input type="text" name="userID" required="" />
                    <span class="error">${userError.userIDError}</span><br/>
                    Full Name: <input type="text" name="fullName" required="" />
                    <span class="error">${userError.fulllNameError}</span><br/>
                    Role ID: <input type="text" name="roleID" value="US" readonly="" /><br/>
                    Password: <input type="password" name="password" required="" />
                    <span class="error">${userError.passwordError}</span><br/>
                    Confirm: <input type="password" name="confirm" required="" />
                    <span class="error">${userError.confirmError}</span><br/>
                    <button type="submit" name="action" value="Create">Sign Up</button>
                    <button type="reset">Reset</button><br/>
                    <span class="error">${userError.error}</span>
                </form>
            </div>
        </div>
    </body>
</html>
