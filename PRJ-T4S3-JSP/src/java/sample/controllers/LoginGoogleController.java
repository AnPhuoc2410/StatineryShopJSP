/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import sample.utils.Iconstant;
import sample.user.UserGGDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDTO;

/**
 *
 * @author ANPHUOC
 * Handles Google OAuth login.
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private final static String ERROR = "Login.html";
    private final static String SUCCESS = "LoginController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");//Code from request
            if (code != null) {
                String authToken = Iconstant.getToken(code);//getaccessToken
                UserGGDTO user = Iconstant.getUserInfo(authToken);//getUserInfor
                //print User
                System.out.println(user);
                
                String userID = user.getEmail();
                String fullName = user.getGiven_name();
                String roleID = "US";
                String avatar = user.getPicture();
                String password = "1";
                String authType = "Google";
                UserDTO loginUser = new UserDTO(userID, fullName, roleID, password, avatar, authType, 1);
                request.setAttribute("GOOGLE_USER", loginUser);//sent object loginUser
                request.setAttribute("AUTH_TYPE", "Google");//sent message
                url = SUCCESS;
            } else {
                response.sendRedirect(url);
            }

        } catch (Exception e) {
            log("Error at LoginGoogleController: " + e.toString());
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
