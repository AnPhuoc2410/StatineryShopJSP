<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--Nguon = https://theproviderssolutions.com/public/portfolioviewpage/NKuKVfvS2xH0wq8kMjQ0a1E1Cn9VIl75Sliding%20Login%20Form.html-->
        <title>Login Page</title>
        <link rel="icon" type="image/x-icon" href="img/icon.png" />
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/style.css"/>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div class="container" id="main">
            <div class="sign-up">
                <form action="MainController" method="POST" >
                    <h1>Create Account</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=${url.base}/PRJ-T4S3-JSP/LoginGoogleController&response_type=code&client_id=${oauth2.google.client_id}&approval_prompt=force" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-github"></i></a>
                    </div>
                    <p>or use your email for registration</p> 
                    <input type="text" name="userID" placeholder="UserID" required="">
                    <input type="text" name="fullName" placeholder="FullName" required="">
                    <input type="hidden" name="roleID" value="US" readonly="">
                    <input type="password" name="password" placeholder="Password" required="">
                    <input type="password" name="confirm" placeholder="Confirm Password" required="">
                    <button type="submit" name="action" value="Create">Sign Up</button>
                </form>

            </div>
            <div class="sign-in">
                <form action="MainController" method="POST" id="Login-Form">
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=${url.base}/PRJ-T4S3-JSP/LoginGoogleController&response_type=code&client_id=${oauth2.google.client_id}&approval_prompt=force" class="social" type="submit">
                            <i class="fab fa-google-plus-g"></i>
                        </a>
                        <a href="#" class="social"><i class="fab fa-github"></i></a>
                    </div>
                    <p>or use your account</p>
                    <input type="text" name="userID" id="userID" placeholder="Enter your username" />
                    <input type="password" name="password" placeholder="Password" required="">
                    <a href="#">Forget your Password?</a>
                    <div class="g-recaptcha" data-sitekey="${recaptcha.key}"></div>
                    <div class="error_message" id="error">${requestScope.ERROR}</div>
                    <div class="success_message">${requestScope.MESSAGE}</div>
                    <button type="submit" name="action" value="Login" onclick="checkCaptcha()">Sign in</button>

                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-left">
                        <h1>Wellcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-right">
                        <h1>Hello, Friend</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            const signUpButton = document.getElementById('signUp');
            const signInButton = document.getElementById('signIn');
            const main = document.getElementById('main');

            signUpButton.addEventListener('click', () => {
                main.classList.add("right-panel-active");
            });
            signInButton.addEventListener('click', () => {
                main.classList.remove("right-panel-active");
            });
            function checkCaptcha() {
                const form = document.getElementById("Login-Form");
                const response = grecaptcha.getResponse();
                const error = document.getElementById("error");
                if (!response) {
                    event.preventDefault();
                    error.textContent = "Please verify you are not a robot";
                } else {
                    form.submit();
                }
            }
        </script>
    </body>
</html>