/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import clases.AnswerEnumeration;
import clases.Client;
import java.util.Stack;

/**
 * Contains the methods of DAOImpementation
 * @author somor
 */
public interface DAOInterface {
    
    public AnswerEnumeration insertarUser(Client cliente, Stack pool);
    public Integer comprobarSingUp(Client cliente);
    public AnswerEnumeration comprobarSingIn(Client cliente);
}
