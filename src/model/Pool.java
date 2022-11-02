/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author somor
 */
public class Pool {

   
    private Connection conec = null;
    private static final String URL= ResourceBundle.getBundle("model.config").getString("url");
    private static final String USER= ResourceBundle.getBundle("model.config").getString("user");
    private static final String PASS= ResourceBundle.getBundle("model.config").getString("pass");
    private static final String NAME= ResourceBundle.getBundle("model.config").getString("driver");

    public Connection getConnection(Stack pool) throws SQLException {
        if(pool.isEmpty()){
            return (Connection) pool.push(createConec());
        }else {
            return (Connection) pool.pop();
        }
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
    
    public Connection createConec(){
        try {
            conec = DriverManager.getConnection(URL, USER, PASS);
            
        } catch (SQLException ex) {
            Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conec;
    }

    
}
