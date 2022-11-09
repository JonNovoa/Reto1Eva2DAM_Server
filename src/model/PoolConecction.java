/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author josue
 */
public class PoolConecction {
    private Integer count;
    
    public Integer pilaContar(Integer contador){
        if(count <10){
        this.count=contador++;
        
        }
        else{
            System.out.println("La pila esta llena");
        }
        
        return count;
    }
      
    
    
    
    
}
