/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author andri
 */
public class ShopDB {

    private static MySqlManager sql;

    public static void initialize() {

        sql = new MySqlManager();
        sql.connectDataBase();
    }

    public static void createProductTable() {

        String query = "CREATE TABLE product("
                + "product_id INT AUTO_INCREMENT,"
                + "title VARCHAR(20),"
                + "type VARCHAR(20),"
                + "dateOfProduction DATE,"
                + "price INT,"
                + "shiftTime DATE,"
                + "cart_id INT,"
                + "PRIMARY KEY(product_id),"
                + "FOREIGN KEY(cart_id) REFERENCES carts(cart_id));";
        //System.out.println(query);
        sql.executeUpdate(query);
    }

    public static void createCartsTable() {

        String query = "CREATE TABLE carts("
                + "cart_id INT AUTO_INCREMENT,"
                + "c_title VARCHAR(10),"
                + "PRIMARY KEY(cart_id));";
        sql.executeUpdate(query);
    }

    public static void addProduct(Product p, int cart_id) {

        String query = "INSERT INTO product(title, type, "
                + "dateOfProduction, price, shiftTime,cart_id) VALUES (?,?,?,?,?,?);";
        PreparedStatement prepStmt = sql.createPreparedStatement(query);
        try {
            prepStmt.setString(1, p.getTitle());
            prepStmt.setString(2, p.getType());
            prepStmt.setDate(3, java.sql.Date.valueOf(p.getDateOfProduction()));
            prepStmt.setDouble(4, p.getPrice());
            prepStmt.setDate(5, java.sql.Date.valueOf(p.getShiftTime()));
            prepStmt.setInt(6, cart_id);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        // System.out.println(query);
        sql.executeUpdate(query);
    }

    public static void addCart(ShoppingCart c) {
        String query = "INSERT INTO carts (c_title) VALUES (?);";
        PreparedStatement prepStmt = sql.createPreparedStatement(query);
        try {
            prepStmt.setString(1, c.getTitle());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void deleteCartById(int id) {
        String query = "DELETE FROM carts WHERE cart_id =?;";
        PreparedStatement prepStmt = sql.createPreparedStatement(query);
        try {
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void deleteProductById(int id) {

        String query = "DELETE FROM product WHERE product_id  =?;";

        PreparedStatement prepStmt = sql.createPreparedStatement(query);
        try {
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static int getCountOfProduct(ShoppingCart cart) {

        int count = 0;
        String query = "SELECT (COUNT(product_id)) as count  "
                + "FROM product WHERE product.cart_id=" + "(SELECT cart_id FROM carts WHERE c_title=" + "'" + cart.getTitle() + "');";
        ResultSet res = sql.executeQuery(query);

        try {
            while (res.next()) {
                count = res.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            return count;
        }
    }

    public static int getSumOfCart(ShoppingCart cart) {

        int sum = 0;
        String query = "SELECT (SUM(price)) AS sum "
                + "FROM product WHERE product.cart_id=" + "(SELECT cart_id FROM carts WHERE c_title=" + "'" + cart.getTitle() + "');";
        ResultSet res = sql.executeQuery(query);

        try {
            while (res.next()) {
                sum = res.getInt("sum");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            return sum;
        }

    }

    public static void dropCarts() {
        String query = "DROP table carts;";

        sql.executeUpdate(query);
    }

    public static void dropProduct() {
        String query = "DROP table product;";

        sql.executeUpdate(query);
    }

    public static int getCountOfSameProduct(Product p, ShoppingCart cart) {

        int count = 0;
        String query = "SELECT (COUNT(product_id)) as count  "
                + "FROM product, carts WHERE product.cart_id=carts.cart_id and c_title=" + "'" + cart.getTitle() + "'" + " and product.title=" + "'" + p.getTitle() + "';";
        ResultSet res = sql.executeQuery(query);
        System.out.println(query);
        try {
            while (res.next()) {
                count = res.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            return count;
        }
    }

    public static void outputCart() {

        String query = "SELECT title, type, dateofproduction, price, shifttime, c_title "
                + "FROM product p,carts c WHERE p.cart_id = c.cart_id;";
        ResultSet res = sql.executeQuery(query);
        try {
            while (res.next()) {
                String title = res.getString("title");
                String type = res.getString("type");
                String dateOfProduction = res.getString("dateofproduction");
                int price = res.getInt("price");
                String shiftTime = res.getString("shifttime");
                String c_title = res.getString("c_title");

                System.out.println("\nCart: " + c_title + "\nTitle: " + title + "\nType: " + type
                        + "\nDate of production: " + dateOfProduction + "\nPrice: " + price
                        + "\nShiftTime: " + shiftTime
                        + "\n");
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void close() {
        sql.disconnectDatabase();
    }

}
