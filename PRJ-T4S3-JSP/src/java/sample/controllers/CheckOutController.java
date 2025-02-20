/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.Cart;
import sample.product.Order;
import sample.product.OrderDAO;
import sample.product.OrderDetail;
import sample.product.Product;
import sample.product.ProductDAO;
import sample.utils.SendMail;

/**
 *
 * @author ANPHUOC
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "ViewCart.jsp";
    private static final String SUCCESS = "ProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        boolean checkOrderDetail = false;
        boolean checkOrder = false;
        try {
            OrderDAO orderDAO = new OrderDAO();
            ProductDAO productDAO = new ProductDAO();

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");

            if (cart == null || cart.getCart().isEmpty()) {
                request.setAttribute("ERROR", "Your cart is empty.");
                request.getRequestDispatcher(ERROR).forward(request, response);
                return;
            }

            float total = Float.parseFloat(request.getParameter("total"));
            String userID = request.getParameter("userID");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateOrder = sdf.format(new Date());

            String orderID = UUID.randomUUID().toString();

            Order order = new Order(orderID, userID, total, dateOrder, 1);
            checkOrder = orderDAO.insertOrder(order);

            for (Product products : cart.getCart().values()) {
                String productID = products.getProductID();
                float price = products.getPrice();
                int quantity = products.getQuantity();
                OrderDetail orderDetail = new OrderDetail(orderID, productID, price, quantity, 1);
                checkOrderDetail = orderDAO.inserOrderDetail(orderDetail);
                productDAO.updateQuantity(productID, quantity);
                if (!checkOrderDetail) {
                    break;
                }
            }

            if (checkOrder && checkOrderDetail) {
                session.removeAttribute("CART");
                String email = request.getParameter("email");
                String name = request.getParameter("lastName");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                String htmlContent = SendMail.generateCheckoutHtmlContent(name, orderID, address, phone, total);
                SendMail.send(email, "Thank You For Order", htmlContent);
                url = SUCCESS;
                request.setAttribute("MESSAGE", "Thank You For Order Please Check Your Email");
            }

        } catch (Exception e) {
            log("Errot at CheckoutController:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
