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
@WebServlet(name = "AddController", urlPatterns = {"/AddController"})
public class AddController extends HttpServlet {

    private static final String ERROR = "ProductController";
    private static final String SUCCESS = "ProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }

            String cmbProduct = request.getParameter("cmbProduct");
            String[] tmp = cmbProduct.split("-");
            String id = tmp[0];
            String name = tmp[1];
            float price = Float.parseFloat(tmp[2]);
            int quantity = 1;
            String image = tmp[3];
            Product product = new Product(id, name, price, quantity, image);

            ProductDAO productDAO = new ProductDAO();
            int currentQuantity = productDAO.checkQuanity(id);
            Product productTemp = cart.search(id);
            if (productTemp != null) {
                if ((productTemp.getQuantity() + quantity) > currentQuantity) {
                    request.setAttribute("MESSAGE_ERROR", "Product " + name + " In Your Cart Excess Storage");
                    return;
                }
            }

            boolean checkAdd = cart.add(product);
            if (checkAdd) {
                session.setAttribute("CART", cart);
                request.setAttribute("MESSAGE", "ADD SUCCESSFULLY");
                url = SUCCESS;
            }else{
                request.setAttribute("MESSAGE_ERROR", "FAILED TO ADD");
            }

        } catch (Exception e) {
            log("Error at AddController: " + e.toString());
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
