/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import clases.Message;
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
         try {
             mensaje= (Message) this.in.readObject();
         } catch (IOException ex) {
             Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(ThreadSR.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
}
