/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author somor
 */
public class ServerSocket {
    static final int PUERTO = 5000;

    
    public ServerSocket() {
        try {
            java.net.ServerSocket skServidor = new java.net.ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            for (int i = 0; i < 3; i++) {
                Socket skCliente = skServidor.accept();
                System.out.println("Sirvo al cliente "+i);
                OutputStream aux = skCliente.getOutputStream();
                DataOutputStream flujo = new DataOutputStream(aux);
                flujo.writeUTF("Hola cliente "+i);
                skCliente.close();
            }
            System.out.println("Demasiados clientes por hoy");
        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
