/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.utils.ImageChecker;

/**
 *
 * @author ANPHUOC
 */
@WebServlet(name = "UploadFileController", urlPatterns = {"/UploadFileController"})
public class UploadFileController extends HttpServlet {

    private final static String ERROR = "error404.jsp";
    private final static String USER_SUCCESS = "profile.jsp";
    private final static String PRODUCT_CONTROLLER = "ProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String uploadType = null;
            String productType = null;
            String uri = "img/error.jpg";
            String productID = null;
            String productName = null;
            String productPrice = null;
            String productQuantity = null;

            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            diskFileItemFactory.setRepository(new File("E:\\VS Studio\\PRJ-T4S3-JSP\\web\\img"));

            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    switch (fileItem.getFieldName()) {
                        case "uploadType":
                            uploadType = fileItem.getString();
                            break;
                        case "productType":
                            productType = fileItem.getString();
                            break;
                        case "productID":
                            productID = fileItem.getString();
                            break;
                        case "name":
                            productName = fileItem.getString();
                            break;
                        case "price":
                            productPrice = fileItem.getString();
                            break;
                        case "quantity":
                            productQuantity = fileItem.getString();
                            break;
                    }
                }
            }

            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    // Process file upload
                    if (fileItem.getFieldName().equals("upfile")) {
                        String imagePath = "";
                        if ("user".equals(uploadType)) {
                            imagePath = "E:\\VS Studio\\PRJ-T4S3-JSP\\web\\img\\" + fileItem.getName();
                            if (!ImageChecker.checkImage(imagePath)) {
                                File file = new File(imagePath);
                                fileItem.write(file);
                                uri = "img/" + fileItem.getName();

                            } else {
                                uri = "img/" + fileItem.getName();
                            }
                        } else if ("product".equals(uploadType)) {
                            imagePath = "E:\\VS Studio\\PRJ-T4S3-JSP\\web\\imgProducts\\" + fileItem.getName();
                            if (!ImageChecker.checkImage(imagePath)) {
                                File file = new File(imagePath);
                                fileItem.write(file);
                                uri = "imgProducts/" + fileItem.getName();
                            } else {
                                uri = "imgProducts/" + fileItem.getName();
                            }

                        }
                    }
                }
            }

            if ("user".equals(uploadType)) {
                url = USER_SUCCESS;
                HttpSession session = request.getSession();
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                loginUser.setAvatar(uri);
                request.setAttribute("MESSAGE", "Update Avatar Success");
                UserDAO dao = new UserDAO();
                dao.uploadAvatar(loginUser);
                session.setAttribute("LOGIN_USER", loginUser);
            } else if ("Add".equals(productType)) {
                url = PRODUCT_CONTROLLER;
                request.setAttribute("productID", productID);
                request.setAttribute("name", productName);
                request.setAttribute("price", productPrice);
                request.setAttribute("quantity", productQuantity);
                request.setAttribute("image", uri);
                request.setAttribute("action", "Add");
            } else if ("Edit".equals(productType)) {
                url = "editproduct.jsp?productID=" + productID + "&image=" + uri + "&name=" + productName + "&price=" + productPrice + "&quantity=" + productQuantity;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at UploadFileController " + e.toString());
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
