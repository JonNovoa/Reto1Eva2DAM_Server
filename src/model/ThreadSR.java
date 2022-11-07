/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import clases.Message;
import clases.Order;
import static clases.Order.IN;
import static clases.Order.UP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josue
 */
public class ThreadSR  extends Thread{
     private ObjectOutputStream out;
     private ObjectInputStream in;

    public ThreadSR(ObjectOutputStream out, ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }
    
     
        
    public void run(){
        
        
        
        Message mensaje= new Message();
        Enum RESPUESTA=null;
        try {
            mensaje= (Message) in.readObject();
            System.out.println(mensaje.getOrden());
            System.out.println(mensaje.getCliente().getLogin());
            DAOInterface dao=DAOFactory.getDAO();
            if(mensaje.getOrden().equals(IN)){
                 RESPUESTA=dao.comprobarSingIn(mensaje.getCliente());
                
            }else if(mensaje.getOrden().equals(UP)){
                RESPUESTA=dao.insertarUser(mensaje.getCliente(), null);
                
            }
          
         } catch (IOException ex) {
             Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
}
