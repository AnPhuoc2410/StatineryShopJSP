/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.Product;
import sample.product.ProductDAO;
import sample.user.UserDTO;

/**
 *
 * @author ANPHUOC
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    private static final String ERROR = "error404.jsp";
    private static final String US = "user.jsp";
    private static final String AD = "productManager.jsp";
    private static final String EDIT = "editproduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }

            ProductDAO dao = new ProductDAO();
            List<Product> listProduct = null;

            if ("US".equals(loginUser.getRoleID())) {
                listProduct = dao.getListProduct(search);
                url = US;
                String sort = request.getParameter("sortOrder");
                Comparator<Product> asc = new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return Float.compare(o1.getPrice(), o2.getPrice());
                    }
                };
                Comparator<Product> des = new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return Float.compare(o2.getPrice(), o1.getPrice());
                    }
                };
                if (listProduct.size() > 0) {
                    request.setAttribute("LIST_PRODUCT", listProduct);

                } else {
                    request.setAttribute("NOT_FOUND_PRODUCT", "No product is found in store");
                }

                if (sort != null && !sort.isEmpty()) {
                    if ("asc".equals(sort)) {
                        Collections.sort(listProduct, asc);
                    } else if ("desc".equals(sort)) {
                        Collections.sort(listProduct, des);
                    } else {
                        //default
                    }
                }
            } else if ("AD".equals(loginUser.getRoleID())) {
                url = AD;
                String actionProduct = request.getParameter("actionProduct");
                String action = (String) request.getAttribute("action");
                if (actionProduct == null) {
                    actionProduct = "";
                    if(action == null){
                        action = "";
                    }else{
                        actionProduct = action;
                    }
                }
                switch (actionProduct) {
                    case "Edit":
                        url = EDIT;
                        String productID = request.getParameter("productID");
                        String productName = request.getParameter("name");
                        float productPrice = Float.parseFloat(request.getParameter("price"));
                        int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                        String image = request.getParameter("image");
                        Product product = new Product(productID, productName, productPrice, productQuantity, image);
                        boolean check = dao.updateProduct(product);

                        if (check) {
                            request.setAttribute("UPDATE_MESS", "The " + productID + " Update Success");
                        } else {
                            request.setAttribute("UPDATE_MESS", "The " + productID + " Update Error");
                        }
                        break;
                        
                    case "Delete":
                        productID = request.getParameter("productID");
                        check = dao.deleteProduct(productID);
                        break;
                    case "Add":
                        productID = (String) request.getAttribute("productID");
                         productName = (String) request.getAttribute("name");
                         productPrice = Float.parseFloat((String) request.getAttribute("price"));
                         productQuantity = Integer.parseInt((String) request.getAttribute("quantity"));
                         image = (String) request.getAttribute("image");
                         Product addProduct = new Product(productID, productName, productPrice, productQuantity, image);
                         check = dao.addProduct(addProduct);
                         if(check){
                             request.setAttribute("ADD_MESS", "Add New "+productName);
                         }else{
                             request.setAttribute("ADD_MESS", "Add Error");
                         }
                        break;
                }
                listProduct = dao.getListProductAdmin(search);
                if (listProduct.size() > 0) {
                    request.setAttribute("LIST_PRODUCT", listProduct);

                } else {
                    request.setAttribute("NOT_FOUND_PRODUCT", "No Product In DB");
                }
            }

        } catch (Exception e) {
            log("Error at ProductController: " + e.toString());
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
