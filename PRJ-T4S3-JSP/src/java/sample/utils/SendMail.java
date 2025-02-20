package sample.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    private static final String FROM_EMAIL = ${email.from};
    private static final String EMAIL_PASSWORD = ${email.password};

    // HTML content for login
    public static String generateLoginHtmlContent(String userName) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
                + ".header { background-color: #4CAF50; color: white; text-align: center; padding: 10px; }"
                + ".content { margin: 20px; background-color: #f9f9f9; color: #333; padding: 20px; border-radius: 10px; }"
                + ".footer { background-color: #f1f1f1; color: #666; text-align: center; padding: 10px; margin-top: 20px; border-top: 1px solid #ddd; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='header'>"
                + "<h1>Welcome to Our Service</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<p>Dear " + userName + ",</p>"
                + "<p>Thank you for logging in. We are thrilled to have you on board. Here are some details about our service:</p>"
                + "<ul>"
                + "<li>High quality products</li>"
                + "<li>24/7 customer support</li>"
                + "<li>Easy returns and exchanges</li>"
                + "</ul>"
                + "<p>We hope you have a great experience with us. If you have any questions, feel free to reach out to our support team.</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>&copy; 2024 Phuoc's Store. All rights reserved.</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    // HTML content for checkout success
    public static String generateCheckoutHtmlContent(String userName, String orderId, String address, String phone, float total) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<style>"
                + "body { font-family: Tahoma,Arial, sans-serif; }"
                + ".header { background-color: #4CAF50; color: white; text-align: center; padding: 10px; }"
                + ".content { margin: 20px; background-color: #f9f9f9; color: #333; padding: 20px; border-radius: 10px; }"
                + ".footer { background-color: #f1f1f1; color: #666; text-align: center; padding: 10px; margin-top: 20px; border-top: 1px solid #ddd; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='header'>"
                + "<h1>Order Confirmation</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<h1>Dear " + userName + ",</h1>"
                + "<p>Thank you for your purchase. Your order ID is " + orderId + "</p>"
                + "<p> Your address : " + address + "</p>"
                + "<p> Your Phone number is " + phone + "</p>"
                + "<p> Your Bill is " + total + ". We will process your order soon.</p>"
                + "<p>We hope you enjoy your purchase. If you have any questions, feel free to reach out to our support team.</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>&copy; 2024 Phuoc's Store. All rights reserved.</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    // HTML content for forgot password
    public static String generateForgotPasswordHtmlContent(String userName, String resetLink) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                +"<meta charset='UTF-8'>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
                + ".header { background-color: #4CAF50; color: white; text-align: center; padding: 10px; }"
                + ".content { margin: 20px; background-color: #f9f9f9; color: #333; padding: 20px; border-radius: 10px; }"
                + ".footer { background-color: #f1f1f1; color: #666; text-align: center; padding: 10px; margin-top: 20px; border-top: 1px solid #ddd; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='header'>"
                + "<h1>Password Reset Request</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<p>Dear " + userName + ",</p>"
                + "<p>We received a request to reset your password. Click the link below to reset your password:</p>"
                + "<p><a href='" + resetLink + "'>Reset Password</a></p>"
                + "<p>If you did not request a password reset, please ignore this email.</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>&copy; 2024 Phuoc's Store. All rights reserved.</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    public static void send(String to, String subject, String htmlContent) throws MessagingException {
        //thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");//SMTP HOST
        props.put("mail.smtp.port", "587");//PORT TLS 587 - SSL 465
        props.put("mail.smtp.auth", "true");//login gmail la true 
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
            }
        };

        Session session = Session.getInstance(props, auth);//đăng nhập vào gmail với auth

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM_EMAIL));
        msg.setReplyTo(InternetAddress.parse(FROM_EMAIL, false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        msg.setSentDate(new Date());
        //tieu de
        msg.setSubject(subject);

        // Set HTML content
        msg.setContent(htmlContent, "text/html; charset=UTF-8");

        // Send email
        Transport.send(msg);

        //print sent
        System.out.println("Email sent successfully.");

    }
}
