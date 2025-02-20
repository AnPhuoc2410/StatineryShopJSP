# Stationery Shop - JSP Servlet Tomcat

## Overview
This project is a **Stationery Shop** web application built using **JSP, Servlets, and Tomcat**. It follows the **MVC architecture** for a well-structured codebase and integrates:
- **Google Login** (OAuth 2.0)
- **Email Sending** (JavaMail API)
- **Google reCAPTCHA** for security
- **Best UX/UI Practices** for a smooth user experience

## Features
- **User Authentication:**
  - Register/Login via Google OAuth
  - reCAPTCHA validation to prevent bots
- **Product Management:**
  - Browse stationery items
  - Search and filter products
- **Shopping Cart & Checkout:**
  - Add/remove items from the cart
  - Secure checkout with email confirmation
- **Admin Panel:**
  - Manage products, orders, and users
- **Responsive UI:**
  - Modern design with Bootstrap

## Technologies Used
- **Backend:** JSP, Servlets, Tomcat
- **Frontend:** HTML, CSS, JavaScript, Bootstrap
- **Database:** SQL Server
- **Authentication:** Google OAuth 2.0
- **Security:** reCAPTCHA, JavaMail API

## Installation Guide
### Prerequisites
- JDK 8+
- Apache Tomcat 9+
- SQL Server
- Maven (Optional)

### Setup Steps
1. **Clone the repository**
   ```sh
   git clone https://github.com/AnPhuoc2410/StatineryShopJSP.git
   cd stationery-shop
   ```
2. **Configure Database**
   - Create a SQL database: `stationery_shop`
   - Import `database.sql` file
   - Update `db.properties` with your SQL credentials
3. **Configure Google OAuth**
   - Create a Google OAuth project
   - Enable "Google+ API"
   - Generate client ID and secret
   - Update `oauth.properties` with credentials
4. **Set up reCAPTCHA**
   - Register your site on [Google reCAPTCHA](https://www.google.com/recaptcha)
   - Update `captcha.properties` with site key and secret key
5. **Build and Deploy**
   - Copy the project to `webapps` folder in Tomcat
   - Start Tomcat and access `http://localhost:8084/`

## Project Structure
```
stationery-shop/
│── src/
│   ├── controller/      # Servlets (MVC Controllers)
│   ├── dao/             # Database Access Objects
│   ├── model/           # Data Models
│   ├── utils/           # Utilities (Mail, OAuth, reCAPTCHA)
│── web/
│   ├── WEB-INF/views/   # JSP files
│   ├── assets/          # CSS, JS, Images
│── StatineryScrip.sql         # Database Schema
│── README.md
```

## Usage
- Open `http://localhost:8084/`
- Register/Login using Google OAuth
- Browse and shop stationery items
- Admin can manage products and orders

## License
This project is open-source under the [MIT License](LICENSE).

---
Feel free to contribute and enhance this project!

