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
import sockets1.SocketServer;


/**
 * Execute the server socket
 * @author somor
 */
public class Application {
    private static Stack pool = null;
    
    
    /**
     * Create a new server Socket.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new SocketServer();
        
    }
    
}
