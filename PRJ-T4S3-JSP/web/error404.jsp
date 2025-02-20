<%-- 
    Document   : error404
    Created on : Jul 8, 2024, 10:23:29 AM
    Author     : ANPHUOC
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>404 - Page Not Found</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                text-align: center;
            }
            .error-page {
                max-width: 600px;
                margin: auto;
            }
        </style>
    </head>
    <body>
        <div class="error-page">
            <h1 class="display-1">404</h1>
            <p class="lead">Oops! The page you are looking for does not exist.</p>
            <p class="lead">Please check the URL or return to the <a href="MainController?action=Home">home page</a>.</p>
        </div>
    </body>
</html>

