package AnalizadorSintactico;

/**
 *
 * @author EGH
 */
import AnalizadorLexico.TokenKind;

public class CurrentTokenIsNoTypeException extends RuntimeException {

    TokenKind currentTokenKind;

    public CurrentTokenIsNoTypeException(TokenKind currentTokenKind) {
        this.currentTokenKind = currentTokenKind;
    }

    
    public CurrentTokenIsNoTypeException(String msg, TokenKind currentTokenKind) {
        super(msg);
        this.currentTokenKind = currentTokenKind;
    }

    public String getMessage() {
        return currentTokenKind.toString() + " is not a type";
    }
}
