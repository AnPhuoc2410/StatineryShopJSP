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
import sample.product.ProductDAO;

/**
 *
 * @author ANPHUOC
 */
@WebServlet(name = "EditController", urlPatterns = {"/EditController"})
public class EditController extends HttpServlet {

    private static final String ERROR = "ViewCart.jsp";
    private static final String SUCCESS = "ViewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String updateCart = request.getParameter("updateCart");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                //increase or decrease quantity
                if ("dec".equals(updateCart)) {
                  quantity--;
                } else if ("inc".equals(updateCart)) {
                    quantity++;
                }
                
                ProductDAO productDAO = new ProductDAO();
                int currentQuantity = productDAO.checkQuanity(id);
                if(quantity > currentQuantity){
                    quantity = currentQuantity;
                    request.setAttribute("MESSAGE_PRODUCT", "Sorry the quantity in store is only "+currentQuantity);
                    request.setAttribute("ID", id);
                }
                
                boolean checkEdit = cart.edit(id, quantity);
                if (checkEdit) {
                    session.setAttribute("CART", cart);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at EditController:" + e.toString());
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
