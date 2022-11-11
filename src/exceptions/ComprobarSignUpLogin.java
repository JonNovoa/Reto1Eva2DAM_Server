/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/**
 *
 * @author josue
 */
public class ComprobarSignUpLogin extends Exception{

    /**
     * Creates a new instance of <code>ComprobarSignUpLogin</code> without
     * detail message.
     */
    public ComprobarSignUpLogin() {
    }

    /**
     * Constructs an instance of <code>ComprobarSignUpLogin</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ComprobarSignUpLogin(String msg) {
        super(msg);
    }
}
