/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets1;

import clases.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import model.ThreadSR;

/**
 *  Sets the port for the connection between sockets.
 * @author INFORMATICA
 */
public class SocketServer {

    static final Integer PUERTO = Integer.parseInt(ResourceBundle.getBundle("clases.connection").getString("puerto"));

    /**
     * Creates a Thread and executes it.
     */
    public SocketServer() {
        ServerSocket skServidor = null;
        Socket skCliente = null;
        Message mensaje = new Message();
        try {
            
            skServidor = new ServerSocket(PUERTO);
            Logger.getLogger("Escucho el puerto "+PUERTO);
            while(true){
            skCliente = skServidor.accept();
            Logger.getLogger("Cliente Conectado");
            
            ThreadSR hilo = new ThreadSR(skCliente);
            hilo.start();            
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                skServidor.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
