/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Saves the number of connections made up to a maximum of 10.
 * @author josue
 */
public class PoolConecction {
    private static Integer count=0;
    
    /**
     * Check if there are 10 connections created, if not add one to the counter.
     */
    public void pilaContar(){
        if(count !=10){
        this.count++;
        

        }
                
    }
      
    
    
    
    
}
