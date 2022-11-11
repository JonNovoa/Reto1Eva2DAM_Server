/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/**
 *
 * @author josue
 */
public class ComProbarSignUpGmail extends Exception{

    /**
     * Creates a new instance of <code>ComProbarSignUpGmail</code> without
     * detail message.
     */
    public ComProbarSignUpGmail() {
    }

    /**
     * Constructs an instance of <code>ComProbarSignUpGmail</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ComProbarSignUpGmail(String msg) {
        super(msg);
    }
}
