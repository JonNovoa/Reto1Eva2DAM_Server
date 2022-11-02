/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import clases.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author somor
 */
public class DAOImplementation implements DAOInterface{

    final String INSERTuser = "INSER INTO client(id, login, email, fullname, status, privilege, passwd) VALUES(?,?,?,?,?,?,?)";
    final String SELECTlogin = "SELECT login FROM client WHERE login = ? ";
    final String SELECTpasswd = "SELECT passwd FROM client WHERE login = ?";
    final String SELECTemail = "SELECT email FROM client WHERE email = ?";
    final String SELECTid = "SELECT MAX(id) from client";

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

    public void insertarUser(Client cliente) {
        this.openConnection();

        try {
            stmt = conex.prepareStatement(INSERTuser);
            stmt.setInt(1, generarId());
            stmt.setString(2, cliente.getLogin());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getFullName());
            stmt.setString(5, cliente.getUsertStatus().toString());
            stmt.setString(6, cliente.getUserPrivilege().toString());
            stmt.setString(7, cliente.getPasswd());

        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Integer comprobarSingUp(Client cliente) {
        this.openConnection();
        ResultSet rs;
        Boolean log = false;
        Boolean email = false;
        Integer up = 0;
        try {
            stmt = conex.prepareStatement(SELECTlogin);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();
            if (rs.equals(cliente.getLogin())) {
                log = true;
            }

            stmt = conex.prepareStatement(SELECTemail);
            stmt.setString(1, cliente.getEmail());
            rs = stmt.executeQuery();
            if (rs.equals(cliente.getEmail())) {
                email = true;
            }

            if (email == true && log == true) {
                up = 1;
                return up;
            }else if (log = true){
               up = 2;
               return up;
            }else if (email == true) {
                up = 3;
                return up;
            } else if (email == false && log == false) {
                up = 4;
                return up;
            }
            this.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return up;
        }
    }
    
    public Boolean comprobarSingIn(Client cliente){
        this.openConnection();
        ResultSet rs;
        Boolean login = false;
        Boolean passwd = false;
        Boolean singUp = false;
        
        try {
            stmt = conex.prepareStatement(SELECTlogin);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();
            if(rs.equals(cliente.getLogin())){
                login = true;
            }
            
            stmt = conex.prepareStatement(SELECTpasswd);
            stmt.setString(0, cliente.getLogin());
            rs = stmt.executeQuery();
            if(rs.equals(cliente.getPasswd())){
                passwd = true;
            }
            
            if(login == true && passwd == true){
                singUp = true;
                return singUp;
            }
            this.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return singUp;
    }

    private int generarId() {
        Integer id = null;
        ResultSet rs = null;
        try {
            stmt = conex.prepareStatement(SELECTid);
            rs = stmt.executeQuery();

            id = rs.getInt(1) + 1;

        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
