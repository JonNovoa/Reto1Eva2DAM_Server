/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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

     final int PUERTO = 5000;

    public SocketServer() {
        ServerSocket skServidor = null;
        Socket skCliente = null;

        try {
            skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            skCliente = skServidor.accept();
            ObjectOutputStream out = new ObjectOutputStream(skCliente.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(skCliente.getInputStream());
           // for (int i = 0; i < 10; i++) {
            ThreadSR hilo= new ThreadSR(out, in);
            hilo.start();
            out.close();
            in.close();
            //}
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                skCliente.close();
                skServidor.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
