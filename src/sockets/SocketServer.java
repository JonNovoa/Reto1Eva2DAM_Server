/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import clases.Message;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INFORMATICA
 */
public class SocketServer {

    static final int PUERTO = 5000;
    //private persona per = null;
    
    public SocketServer(Message mes) {
        ServerSocket skServidor = null;
        Socket skCliente = null;

        try {
            skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            skCliente = skServidor.accept();
            
            
           

            ObjectOutputStream out = new ObjectOutputStream(skCliente.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(skCliente.getInputStream());
            
            mes = (Message) in.readObject();
          
          
            skCliente.close();
            
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
        
    }
    
}
