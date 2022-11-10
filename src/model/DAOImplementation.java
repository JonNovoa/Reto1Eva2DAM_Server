/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import clases.AnswerEnumeration;
import clases.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import static clases.AnswerEnumeration.LOGIN;
import static clases.AnswerEnumeration.SINGUP;
import static clases.AnswerEnumeration.WGMAIL;
import static clases.AnswerEnumeration.WLOGIN;
import static clases.AnswerEnumeration.WLOGIN_PASSWD;
import static clases.AnswerEnumeration.WLOGIN_WGMAIL;

/**
 * Performs queries against the database
 * @author somor
 */
public class DAOImplementation implements DAOInterface {

    final String INSERTuser = "INSERT INTO client(id, login, email, fullname, status, privilege, passwd, lastPasswdChange) VALUES(?,?,?,?,?,?,?,?)";
    final String SELECTlogin = "SELECT login FROM client WHERE login = ? GROUP BY login";
    final String SELECTpasswd = "SELECT passwd FROM client WHERE login = ? GROUP BY passwd";
    final String SELECTemail = "SELECT email FROM client WHERE email = ? GROUP BY email";
    final String SELECTid = "SELECT MAX(id) as num FROM client";
    final String SELECTcountid = "SELECT count(*) as num FROM signin WHERE id=?";
    final String INSERTsignin = "INSERT INTO signin(id, lastSignIn) VALUES(?,?)";
    final String SELECTidlogin = "SELECT id FROM client WHERE login = ?";
    final String DELETEprimero = "DELETE FROM signin where id=? limit 1";

    private Pool co = null;
    private Connection c;
    private PreparedStatement stmt;

    /**
     * Inserts the users in the database.
     * @param cliente
     * @param pool
     * @return
     */
    @Override
    public AnswerEnumeration insertarUser(Client cliente, Stack pool) {
        ResultSet rs;

        Integer good;
        AnswerEnumeration Orden;
        co = new Pool();
        try {
            c = co.getConnection();
            good = comprobarSingUp(cliente);
            
            /**
             * Depending on the result of the comprobarSingUp method, it will insert 
             * the user in the database and return an AnswerEnumeration or 
             * simply return an AnswerEnumeration.
             */
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

                co.guardarConec(c);
                return Orden = SINGUP;
            } else if (good == 1) {
                co.guardarConec(c);
                return Orden = WLOGIN_WGMAIL;
            } else if (good == 2) {
                co.guardarConec(c);
                return Orden = WLOGIN;
            } else if (good == 3) {
                co.guardarConec(c);
                return Orden = WGMAIL;
            }
            co.guardarConec(c);
        } catch (SQLException ex) {
            co.guardarConec(c);
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Check that the login and gmail entered in the sing up 
     * are not repeated in the database.
     * @param cliente
     * @return 
     */
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
            /**
             * Depending on whether or not the login, password or both are repeated, 
             * it will return one value or another. 
             */
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

    /**
     * Check that the login exists and that the password matches the login 
     * when performing the sing in in the database.
     * @param cliente
     * @return 
     */
    @Override
    public AnswerEnumeration comprobarSingIn(Client cliente) {
        ResultSet rs;
        String i;
        Boolean login = false;
        Boolean passwd = false;
        AnswerEnumeration ORDEN = null;
        co = new Pool();
        try {
            c = co.getConnection();
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
            /**
             * Depending on whether the login exists and the password matches it 
             * returns a command to the Client
             */
            if (login == true && passwd == true) {
                ORDEN = AnswerEnumeration.LOGIN;
                Integer id = obtenerId(cliente);
                System.out.println(id);
                Integer count= contarConexion(id);
                System.out.println(count);
                if(count==10){
                    eliminarPrimero(id);
                    
                }
                insertSignIn(id);
                
            }

            if (login == false || passwd == false) {
                ORDEN = AnswerEnumeration.WLOGIN_PASSWD;
            }
            co.guardarConec(c);
        } catch (SQLException ex) {
            co.guardarConec(c);
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ORDEN;
    }
    
    /**
     * It takes the last id entered in the database and adds 1 to it 
     * to return it and assign it to the next user to be entered.
     * @return 
     */
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

    /**
     * Returns the user id
     * @param cliente
     * @return 
     */
    private Integer obtenerId(Client cliente) {
        ResultSet rs;
        Integer i = null;
        try {
            stmt = c.prepareStatement(SELECTidlogin);
            stmt.setString(1, cliente.getLogin());
            rs = stmt.executeQuery();

            if (rs.next()) {
                i = rs.getInt("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;

    }

    /**
     * Count the number of sing in done
     * @param id
     * @return 
     */
    private Integer contarConexion(Integer id) {
     ResultSet rs;
        Integer i = null;
        try {
            stmt = c.prepareStatement(SELECTcountid);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                i = rs.getInt("num");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    /**
     * Saves the date of the last sign in performed
     * @param id 
     */
    private void insertSignIn(Integer id) {
        try {
            stmt = c.prepareStatement(INSERTsignin);
            stmt.setInt(1, id);   
            stmt.setString(2, null);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * If when you sign in and there are 10 records stored in the database 
     * it will delete the oldest of them all and register the new one.
     * @param id 
     */
    private void eliminarPrimero(Integer id) {
        try {
            stmt = c.prepareStatement(DELETEprimero);   
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
