/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        
        
    }
}
