/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import clases.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.AnswerEnumeration.LOGIN;
import static model.AnswerEnumeration.SINGUP;
import static model.AnswerEnumeration.WGMAIL;
import static model.AnswerEnumeration.WLOGIN;
import static model.AnswerEnumeration.WLOGIN_PASSWD;
import static model.AnswerEnumeration.WLOGIN_WGMAIL;

/**
 *
 * @author somor
 */
public class DAOImplementation implements DAOInterface {

    final String INSERTuser = "INSERT INTO client(id, login, email, fullname, status, privilege, passwd, lastPasswdChange) VALUES(?,?,?,?,?,?,?,?)";
    final String SELECTlogin = "SELECT login FROM client WHERE login = ? GROUP BY login";
    final String SELECTpasswd = "SELECT passwd FROM client WHERE login = ? GROUP BY passwd";
    final String SELECTemail = "SELECT email FROM client WHERE email = ? GROUP BY email";
    final String SELECTid = "SELECT MAX(id) as num FROM client";

    private Pool co = null;
    private Connection c;
    private PreparedStatement stmt;

    /**
     *
     * @param cliente
     * @param pool
     * @return
     */
    @Override
    public Enum insertarUser(Client cliente, Stack pool) {
        Integer good;
        Enum Orden;
        co = new Pool();
        try {
            c = co.createConect();
            good = comprobarSingUp(cliente);

            if (good == 4) {
                stmt = c.prepareStatement(INSERTuser);
                stmt.setInt(1, generarId());
                stmt.setString(2, cliente.getLogin());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getFullName());
                stmt.setString(5, cliente.getUsertStatus().toString());
                stmt.setString(6, cliente.getUserPrivilege().toString());
                stmt.setString(7, cliente.getPasswd());
                stmt.setString(8, null);
                stmt.executeUpdate();

                co.closeConnection(c);
                return Orden = SINGUP;
            } else if (good == 1) {
                return Orden = WLOGIN_WGMAIL;
            } else if (good == 2) {
                return Orden = WLOGIN;
            } else if (good == 3) {
                return Orden = WGMAIL;
            }
            co.closeConnection(c);
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Integer comprobarSingUp(Client cliente) {
        ResultSet rs;
        String i;
        Boolean log = false;
        Boolean email = false;
        Integer up = 0;
        try {

            stmt = c.prepareStatement(SELECTlogin);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();
            if (rs.next()) {
                i = rs.getString("login");

                if (i.equals(cliente.getLogin())) {
                    log = true;
                }
            }

            stmt = c.prepareStatement(SELECTemail);
            stmt.setString(1, cliente.getEmail());
            rs = stmt.executeQuery();
            if (rs.next()) {
                i = rs.getString("email");
                if (i.equals(cliente.getEmail())) {
                    email = true;
                }
            }
            if (log == true && email == true) {
                up = 1;
                return up;
            } else if (log == true) {
                up = 2;
                return up;
            } else if (email == true) {
                up = 3;
                return up;
            } else if (email == false && log == false) {
                up = 4;
                return up;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return up;
    }

    @Override
    public Enum comprobarSingIn(Client cliente) {
        ResultSet rs;
        String i;
        Boolean login = false;
        Boolean passwd = false;
        Enum orden;
        co = new Pool();
        try {
            c = co.createConect();
            stmt = c.prepareStatement(SELECTlogin);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                i = rs.getString("login");
                if (i.equals(cliente.getLogin())) {
                    login = true;
                }
            }
            
            stmt = c.prepareStatement(SELECTpasswd);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();
                    
            if (rs.next()) {
                i = rs.getString("passwd");
                if (i.equals(cliente.getPasswd())) {
                    passwd = true;
                }
            }
            if (login == true && passwd == true) {
                orden = LOGIN;
                return orden;
            }

            if (login == false || passwd == false) {
                orden = WLOGIN_PASSWD;
                return orden;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private int generarId() {
        Integer id = null;
        ResultSet rs;
        try {
            PreparedStatement stmnt = null;
            rs = stmt.executeQuery(SELECTid);

            if (rs.next()) {
                id = rs.getInt("num");
                id = id + 1;
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
