/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import clases.AnswerEnumeration;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write the server's response in the message.
 * @author josue
 */
public class ThreadW {
    private Socket skCliente;
    AnswerEnumeration RESPUESTA;

    ThreadW(Socket skCliente, AnswerEnumeration RESPUESTA) {
        this.skCliente=skCliente;
        this.RESPUESTA=RESPUESTA;      
               
    }
    
    /**
     * Writes a message with the server's response on the socket.
     */
    public void run(){
        ObjectOutputStream out;
        
        try {
            out= new ObjectOutputStream(skCliente.getOutputStream());
           out.writeObject(RESPUESTA);
        } catch (IOException ex) {
            Logger.getLogger(ThreadW.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
        }
        
    }
    
    
}
