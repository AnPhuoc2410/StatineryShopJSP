/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.Cart;
import sample.product.Product;
import sample.product.ProductDAO;

/**
 *
 * @author ANPHUOC
 */
@WebServlet(name = "CheckCartController", urlPatterns = {"/CheckCartController"})
public class CheckCartController extends HttpServlet {

    private static final String VIEW_CART = "ViewCart.jsp";
    private static final String SUCCESS = "checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = VIEW_CART;
        try {
            ProductDAO productDAO = new ProductDAO();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            String order = request.getParameter("order");
            boolean available = true;
            if (cart != null) {
                for (Product products : cart.getCart().values()) {
                    int currentQuantity = productDAO.checkQuanity(products.getProductID());
                    if (currentQuantity < products.getQuantity()) {
                        available = false;
                        boolean checkRemove = cart.remove(products.getProductID());
                        if (checkRemove) {
                            if (cart.getCart().size() == 0) {
                                cart = null;
                            }
                            request.setAttribute("MESSAGE_REMOVE", "Product " + products.getName() + " Out Of Stock");
                            break;
                        }
                    }
                }

            }
            session.setAttribute("CART", cart);
            if (available && "Order".equals(order)) {
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at CheckCartController: " + e.toString());
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
