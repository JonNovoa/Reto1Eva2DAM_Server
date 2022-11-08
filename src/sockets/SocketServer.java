/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import clases.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ThreadSR;

/**
 *
 * @author INFORMATICA
 */
public class SocketServer {

    static final int PUERTO = 5000;

    public SocketServer() {
        ServerSocket skServidor = null;
        Socket skCliente = null;
        Message mensaje = new Message();
        try {
            skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            skCliente = skServidor.accept();
            //in = new ObjectInputStream(skCliente.getInputStream());
            //mensaje = (Message) in.readObject();

            // for (int i = 0; i < 2; i++) {
            //System.out.println(mensaje.getOrden());
            //System.out.println(mensaje.getCliente().getLogin());
           ThreadSR hilo = new ThreadSR(skCliente);
           hilo.start();            
            //}

        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //in.close();
               // skCliente.close();
                skServidor.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
