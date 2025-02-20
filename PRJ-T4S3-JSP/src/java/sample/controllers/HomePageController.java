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
import sample.user.UserDTO;
import sample.utils.ImageChecker;

/**
 *
 * @author ANPHUOC
 */
@WebServlet(name = "HomePageController", urlPatterns = {"/HomePageController"})
public class HomePageController extends HttpServlet {

    private static final String USER_PAGE = "ProductController";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String ERROR = "login.jsp";
    private static final String AD = "AD";
    private static final String US = "US";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser != null) {
                String roleID = loginUser.getRoleID();
                String ava = loginUser.getAvatar();
                String path = "E:/VS Studio/PRJ-T4S3-JSP/web/" + ava;
                boolean check = ImageChecker.checkImage(path);

                String avatarURL = (loginUser.getAvatar() != null && check) ? loginUser.getAvatar() : "img/avatar.png";
                if ("Google".equals(loginUser.getAuthType())) {
                    avatarURL = loginUser.getAvatar();
                }
                loginUser.setAvatar(avatarURL);
                session.setAttribute("LOGIN_USER", loginUser);
                if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                } else if (US.equals(roleID)) {
                    url = USER_PAGE;
                }
            } else {
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at HomePageController:" + e.toString());
        } finally {
            response.sendRedirect(url);
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
