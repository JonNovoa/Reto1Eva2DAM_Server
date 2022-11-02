/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import clases.Client;
import java.util.Stack;

/**
 *
 * @author somor
 */
public interface DAOInterface {
    
    public void insertarUser(Client cliente, Stack pool);
    public Integer comprobarSingUp(Client cliente);
    public Boolean comprobarSingIn(Client cliente);
}
