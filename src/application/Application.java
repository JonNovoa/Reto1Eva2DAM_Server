/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import clases.Client;
import static clases.UserPrivilege.USER;
import static clases.UserStatus.ENABLED;
import java.util.Stack;
import model.DAOImplementation;
import sockets.SocketServer;


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
        SocketServer socketServer = new SocketServer();
        
        /**
        Client clie = new Client();
        clie.setLogin("gonzalo1");
        clie.setEmail("gozanlo123@gmail.com");
        clie.setFullName("Gonzalo Fernandez");
        clie.setPasswd("abcd*1234");
        clie.setUserPrivilege(USER);
        clie.setUsertStatus(ENABLED);
        DAOImplementation d = new DAOImplementation();
        d.insertarUser(clie, pool);
         */
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
