/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.SintaxAnalyzer;

/**
 *
 * @author tomaso
 */

import EasyCompiler.LexicalAnalyzer.TokenKind;

public class CurrentTokenIsNoTypeException extends RuntimeException {

    /**
     * Creates a new instance of <code>CurrentTokenIsNoTypeException</code> without detail message.
     */
    
    TokenKind currentTokenKind;
    
    public CurrentTokenIsNoTypeException(TokenKind currentTokenKind) {
        this.currentTokenKind = currentTokenKind;
    }


    /**
     * Constructs an instance of <code>CurrentTokenIsNoTypeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CurrentTokenIsNoTypeException(String msg, TokenKind currentTokenKind) {
        super(msg);
        this.currentTokenKind = currentTokenKind;     
    }
    
    public String getMessage()
    {
        return currentTokenKind.toString() + " is not a type"; 
    }
}
