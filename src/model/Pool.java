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
 *  Creates connections to the database and stores them in a stack
 * @author somor
 */
public class Pool {

    private Connection conect = null;
    private static Stack pool = new Stack();
    //Datos conseguidos en config para poder conectarse a la BD
    private static final String URL = ResourceBundle.getBundle("model.config").getString("url");
    private static final String USER = ResourceBundle.getBundle("model.config").getString("user");
    private static final String PASS = ResourceBundle.getBundle("model.config").getString("pass");
    private static final String NAME = ResourceBundle.getBundle("model.config").getString("driver");

    /**
     * Checks if the stack is empty, if it is empty it creates a 
     * connection and returns it, if not it takes the connection from 
     * the stack and returns it.
     * @return 
     */
    
   

    public Connection getConnection() {
        if (pool.isEmpty()) {
            
            return createConect();

        } else {
            return (Connection) pool.pop();
        }
    }
//Cerramos la Conexion
    /**
     * Pulls the connections out of the stack and closes them one by one.
     * @param connection
     * @throws SQLException 
     */
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
//Abrimos la conexion a la Base de Datos 
    /**
     * Creates connections to the database and returns them.
     * @return 
     */
    public Connection createConect() {
        PoolConecction contador= new PoolConecction();
        contador.pilaContar();
        try {
            conect = DriverManager.getConnection(URL, USER, PASS);
            
        } catch (SQLException ex) {
            Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conect;
    }

    /**
     * Save the connections in the stack
     * @param conec 
     */
    public synchronized void guardarConec(Connection conec) {
        pool.push(conec);
        

    }
    public void closeConexionPool(){
        Connection con;
        while(!pool.isEmpty()){
            con= (Connection) pool.pop();
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
