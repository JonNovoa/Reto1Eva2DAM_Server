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
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author somor
 */
public class DAOImplementation implements DAOInterface{

    final String INSERTuser = "INSERT INTO client(id, login, email, fullname, status, privilege, passwd, lastPasswdChange) VALUES(?,?,?,?,?,?,?,?)";
    final String SELECTlogin = "SELECT login FROM client WHERE login = ? ";
    final String SELECTpasswd = "SELECT passwd FROM client WHERE login = ?";
    final String SELECTemail = "SELECT email FROM client WHERE email = ?";
    final String SELECTid = "SELECT MAX(id) as num  from client";

    private Pool co = null;
    private Connection c;
    private PreparedStatement stmt;

    /*public void openConnection() {
        try {
            conex = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    /*public void closeConnection() throws SQLException {
        if (conex != null) {
            conex.close();
        }
        if (stmt != null) {
            conex.close();
        }
    }*/

    /**
     *
     * @param cliente
     * @param pool
     */


    @Override
    public void insertarUser(Client cliente, Stack pool) {
            Integer good;
            co = new Pool();
        try {
            c = co.createConect();
            //good = comprobarSingUp(cliente);
            
            //if(good == 4){
            stmt = c.prepareStatement(INSERTuser);
            stmt.setInt(1, 1);
            stmt.setString(2, cliente.getLogin());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getFullName());
            stmt.setString(5, cliente.getUsertStatus().toString());
            stmt.setString(6, cliente.getUserPrivilege().toString());
            stmt.setString(7, cliente.getPasswd());
            stmt.setString(8,null);
            
            stmt.executeUpdate();
             co.closeConnection(c);
            //}
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Integer comprobarSingUp(Client cliente) {
        ResultSet rs;
        Boolean log = false;
        Boolean email = false;
        Integer up = 0;
        try {
            
            stmt = c.prepareStatement(SELECTlogin);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();
            if (rs.equals(cliente.getLogin())) {
                log = true;
            }

            stmt = c.prepareStatement(SELECTemail);
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
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return up;
        }
    }
    
    public Boolean comprobarSingIn(Client cliente){
        ResultSet rs;
        Boolean login = false;
        Boolean passwd = false;
        Boolean singUp = false;
        
        try {
            stmt = c.prepareStatement(SELECTlogin);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();
            if(rs.equals(cliente.getLogin())){
                login = true;
            }
            
            stmt = c.prepareStatement(SELECTpasswd);
            stmt.setString(0, cliente.getLogin());
            rs = stmt.executeQuery();
            if(rs.equals(cliente.getPasswd())){
                passwd = true;
            }
            
            if(login == true && passwd == true){
                singUp = true;
                return singUp;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return singUp;
    }

    private int generarId() {
        Integer id = null;
        ResultSet rs = null;
        try {
            stmt = c.prepareStatement(SELECTid);
            rs = stmt.executeQuery();
           
            id = rs.getInt("num") + 1;

            
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
 

}
