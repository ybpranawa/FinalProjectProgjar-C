/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintarserver;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author Fendy
 */
public class DbConnect {
    public Connection conn;
    private PreparedStatement statement;
    public static DbConnect db;
    private DbConnect() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "adupintar";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized DbConnect getDbCon() {
        if ( db == null ) {
            db = new DbConnect();
        }
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query, ArrayList<String>args) throws SQLException{
        statement = (PreparedStatement) db.conn.prepareStatement(query);
        int counter = 1;
        for (String arg : args) {
            statement.setString(counter, arg);
        }
        ResultSet res = statement.executeQuery();
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery, ArrayList<String>args) throws SQLException {
        statement = (PreparedStatement) db.conn.prepareStatement(insertQuery);
        int counter = 1;
        for (String arg : args) {
            statement.setString(counter++, arg);
        }
        int result = statement.executeUpdate();
        return result;
 
    }
}
