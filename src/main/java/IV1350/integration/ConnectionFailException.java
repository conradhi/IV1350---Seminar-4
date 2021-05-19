/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IV1350.integration;

/**
 * Exception is thrown when the connection  to the database fails
 * @author Conrad
 */
public class ConnectionFailException extends Exception{
    private String message;
    
    /**
     * Creates a new instance of ConnectionFailException
     * 
     * @param message    The message that will be sent with the exception
     */
    public ConnectionFailException(String message) {
        super(message);
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
}
