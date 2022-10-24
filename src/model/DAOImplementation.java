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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author somor
 */
public class DAOImplementation implements DAOInterface{

    private Connection conex;
    private PreparedStatement stmt;
    private ResourceBundle archivoConfig;
    private String url;
    private String usuario;
    private String contraseña;
    private String driver;

    public DAOImplementation() {
        this.archivoConfig = ResourceBundle.getBundle("modelo.config");
        this.url = archivoConfig.getString("Conn");
        this.usuario = archivoConfig.getString("BDUser");
        this.contraseña = archivoConfig.getString("BDPass");
        this.driver = archivoConfig.getString("Driver");
    }

    public void openConnection() {
        try {
            conex = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException {
        if (conex != null) {
            conex.close();
        }
        if (stmt != null) {
            conex.close();
        }
    }

}
