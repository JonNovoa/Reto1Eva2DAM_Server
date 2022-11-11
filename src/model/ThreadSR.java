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
 * Creates threads
 * @author josue
 */
public class ThreadSR extends Thread {

    private Socket skCliente;
    static final Integer PUERTO= 5000;
    

    public ThreadSR(Socket skCliente) {
        this.skCliente = skCliente;
    }

    /**
     *Checks the message received from the client and depending on 
     * the command in the message performs either a sign up or a log in.
     */
    @Override
    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Message mensaje = new Message();
        AnswerEnumeration RESPUESTA = null;
       DAOInterface dao=DAOFactory.getDAO();
        try {
            
                
            
            ois = new ObjectInputStream(skCliente.getInputStream());
            oos= new ObjectOutputStream(skCliente.getOutputStream());
            mensaje = (Message) ois.readObject();
            if(mensaje.getCerrar().equalsIgnoreCase("exit")){
                  dao.cerrarPila();
            } 
            else{
           
            if (mensaje.getORDER().equals(IN)) {
                RESPUESTA = dao.comprobarSingIn(mensaje.getCliente());
                

            } else if (mensaje.getORDER().equals(UP)) {
                RESPUESTA = dao.insertarUser(mensaje.getCliente());
}
            } 
            
             
            mensaje.setRESPUESTA(RESPUESTA);
        
            oos.writeObject(mensaje);
            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
            }

        }   
       
    }
}
