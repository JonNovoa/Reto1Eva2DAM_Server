/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import clases.AnswerEnumeration;
import clases.Message;
import static clases.Order.IN;
import static clases.Order.UP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josue
 */
public class ThreadSR extends Thread {

    private Socket skCliente;
    static final Integer PUERTO= 5000;
    

    public ThreadSR(Socket skCliente) {
        this.skCliente = skCliente;
    }

    /**
     *
     */
    @Override
    public void run() {
        ServerSocket skServidor;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Message mensaje = new Message();
        AnswerEnumeration RESPUESTA = null;
        DAOInterface dao=DAOFactory.getDAO();
        try {
            in = new ObjectInputStream(skCliente.getInputStream());
            mensaje = (Message) in.readObject();
            
            System.out.println(mensaje.getCliente().getLogin());
            System.out.println(mensaje.getOrden());
            if (mensaje.getOrden().equals(IN)) {
                RESPUESTA = dao.comprobarSingIn(mensaje.getCliente());
                

            } else if (mensaje.getOrden().equals(UP)) {
                RESPUESTA = dao.insertarUser(mensaje.getCliente(), null);

            }
            System.out.println(RESPUESTA);
          
  
        } catch (IOException ex) {
            Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                // out.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        try {
            skServidor = new ServerSocket(PUERTO);
            skCliente= skServidor.accept();
            ThreadW hilo= new ThreadW(skCliente,RESPUESTA);
        } catch (IOException ex) {
            Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
        }
            

      
        
    }
}
