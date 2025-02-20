/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author ANPHUOC
 */
public class ProductDAO {

    private static final String GET_LIST_PRODUCT = "SELECT productID,name,price,quantity,image FROM tbl_Products WHERE name LIKE ? AND status = 1";
    private static final String GET_LIST_PRODUCT_ADMIN = "SELECT productID,name,price,quantity,image,status FROM tbl_Products WHERE name LIKE ?";
    private static final String UPDATE_QUANTITY_PRODUCT = "UPDATE  tbl_Products SET quantity = ? ,status = ? WHERE productID = ?";
    private static final String UPDATE_PRODUCT = "UPDATE  tbl_Products SET name = ?,price = ?,quantity = ?,image = ?,status = ? WHERE productID = ?";
    private static final String DELETE_PRODUCT = "UPDATE tbl_Products SET status=0 WHERE productID=?";
    private static final String CHECK_QUANTITY = "SELECT quantity FROM  tbl_Products WHERE productID = ?";
    private static final String ADD_PRODUCT = "INSERT INTO tbl_Products (productID, name, price, quantity,image) VALUES (?,?,?,?,?)";

    public List<Product> getListProduct(String search) throws SQLException, ClassNotFoundException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_PRODUCT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("Name");
                    float productPrice = rs.getFloat("price");
                    int productQuantity = rs.getInt("Quantity");
                    String image = rs.getString("image");
                    listProduct.add(new Product(productID, productName, productPrice, productQuantity, image));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listProduct;
    }

    public List<Product> getListProductAdmin(String search) throws SQLException, ClassNotFoundException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_PRODUCT_ADMIN);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("Name");
                    float productPrice = rs.getFloat("price");
                    int productQuantity = rs.getInt("Quantity");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    listProduct.add(new Product(productID, productName, productPrice, productQuantity, image, status));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listProduct;
    }

    public boolean updateQuantity(String productID, int quantitySold) throws SQLException, ClassNotFoundException {
        int updateQuantity = checkQuanity(productID) - quantitySold;
        String status = "0";
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY_PRODUCT);
                ptm.setInt(1, updateQuantity);
                if (updateQuantity > 0) {
                    status = "1";
                }
                ptm.setString(2, status);
                ptm.setString(3, productID);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int checkQuanity(String productID) throws SQLException, ClassNotFoundException {
        int currentQuanity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_QUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    currentQuanity = rs.getInt("Quantity");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return currentQuanity;
    }

    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            BigDecimal pricelDecimal = new BigDecimal(product.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, product.getName());
                ptm.setBigDecimal(2, pricelDecimal);
                ptm.setInt(3, product.getQuantity());
                ptm.setString(4, product.getImage());
                int status = (product.getQuantity() > 0) ? 1 : 0;
                ptm.setInt(5, status);
                ptm.setString(6, product.getProductID());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean deleteProduct(String productID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT);
                ptm.setString(1, productID);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean addProduct(Product addProduct) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            BigDecimal price = new BigDecimal(addProduct.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT);
                ptm.setString(1, addProduct.getProductID());
                ptm.setString(2, addProduct.getName());
                ptm.setBigDecimal(3, price);
                ptm.setInt(4, addProduct.getQuantity());
                ptm.setString(5, addProduct.getImage());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
