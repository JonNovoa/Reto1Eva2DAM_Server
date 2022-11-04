/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import clases.Client;
import clases.Order;
import static clases.Order.IN;
import static clases.Order.UP;

/**
 *
 * @author somor
 */
public class DAOFactory {

    public static DAOInterface getDAO(Client cliente, Order ORDEN) {
        if (ORDEN.equals(IN)) {
            return new DAOImplementation();
        } else if (ORDEN.equals(UP)) {
            return new DAOImplementation();
        }
        return null;
    }
}
