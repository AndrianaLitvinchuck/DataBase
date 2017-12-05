/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbClasses;

/**
 *
 * @author andri
 */
import java.sql.*;



class MySqlManager {

    private Connection connection;
    private Statement statement;

    public void connectDataBase() {

       
        String url = "jdbc:mysql://localhost:3306/shopDB";
        String uname = "root";
	String pass = "1111";
           

        try {
            connection = DriverManager.getConnection(url, uname, pass);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int executeUpdate(String query) {

        int res = 0;
    
        String url = "jdbc:mysql://localhost:3306/shopDB";
        String uname = "root";
	String pass = "1111";
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
             connection= DriverManager.getConnection(url, uname, pass);
            statement = connection.createStatement();
            res = statement.executeUpdate(query);
           
        } catch (SQLException e) {
            //System.out.println(e);
        } finally {
            return res;
        }
        
    }

        public PreparedStatement createPreparedStatement(String query){
        try {
            return connection.prepareStatement(query);
        } catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }
    public ResultSet executeQuery(String query) {

        ResultSet res = null;
   
        try {
            
            statement = connection.createStatement();
            res = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return res;
        }
    }

    public void disconnectDatabase() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
}
