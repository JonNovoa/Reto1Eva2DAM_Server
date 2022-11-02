/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import clases.Client;
import static clases.UserPrivilege.USER;
import static clases.UserStatus.ENABLED;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOImplementation;
import model.Pool;

/**
 *
 * @author somor
 */
public class Application {
    private static Stack pool = null;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client clie = new Client();
        
        clie.setLogin("josue");
        clie.setEmail("josue@gmail.com");
        clie.setFullName("Josue Vargas");
        clie.setPasswd("abcd*1234");
        clie.setUserPrivilege(USER);
        clie.setUsertStatus(ENABLED);
        DAOImplementation d = new DAOImplementation();
        d.insertarUser(clie, pool);
        /*try {
            // TODO code application logic here
            Connection c = Pool.getInstance().getConnection();
            if(c!=null){
                System.out.println("conectado");
                Pool.getInstance().closeConnection(c);
                
                
            }else{
                System.out.println("No conectado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
}
