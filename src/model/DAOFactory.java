/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Creates daos
 * @author somor
 */
public class DAOFactory {

    /**
     * Returns a dao
     * @return 
     */
    public static DAOInterface getDAO() {
        
        return new DAOImplementation();
            
            
        
    }
}
