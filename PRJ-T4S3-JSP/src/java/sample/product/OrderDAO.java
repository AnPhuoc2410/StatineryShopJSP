/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
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
public class OrderDAO {

    private static final String INSERT_ORDER = "INSERT INTO tbl_Order (orderID,userID, total, date) VALUES (?,?,?,?)";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO tbl_OrderDetail (orderID, productID, price, quantity) VALUES (?,?,?,?)";
    private static final String VIEW_ORDER = "SELECT orderID,date,total FROM tbl_Order WHERE userID = ? AND status = 1 ORDER BY date DESC";
    private static final String VIEW_ORDERDETAIL = "SELECT productID,price,quantity FROM tbl_OrderDetail WHERE orderID = ? AND status = 1";

    public boolean insertOrder(Order order) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            BigDecimal totalDecimal = new BigDecimal(order.getTotal()).setScale(2, BigDecimal.ROUND_HALF_UP);
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(INSERT_ORDER);
                ptm.setString(1, order.getOrderID());
                ptm.setString(2, order.getUserID());
                ptm.setBigDecimal(3, totalDecimal);
                ptm.setString(4, order.getDate());
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

    public boolean inserOrderDetail(OrderDetail orderdetails) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            BigDecimal pricelDecimal = new BigDecimal(orderdetails.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER_DETAIL);
                ptm.setString(1, orderdetails.getOrderID());
                ptm.setString(2, orderdetails.getProductID());
                ptm.setBigDecimal(3, pricelDecimal);
                ptm.setInt(4, orderdetails.getQuantity());
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Print the exception stack trace
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

    public List<Order> getListOrders(String userID) throws SQLException, ClassNotFoundException {
        List<Order> listOrder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_ORDER);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String date = rs.getString("date");
                    float total = rs.getFloat("total");
                    listOrder.add(new Order(orderID, userID, total, date, 1));
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
        return listOrder;
    }
    
     public List<OrderDetail> getListOrderDetails(String orderID) throws SQLException, ClassNotFoundException {
        List<OrderDetail> listOrderDetails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_ORDERDETAIL);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    listOrderDetails.add(new OrderDetail(orderID, productID, price, quantity, 1));
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
        return listOrderDetails;
    }
}
