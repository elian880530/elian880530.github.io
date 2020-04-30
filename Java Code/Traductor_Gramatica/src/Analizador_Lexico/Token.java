

package Analizador_Lexico;

import Compilador.Source_Position;


/**
 *
 * @author EGH
 */
public class Token {

    private Token_Kind kind;
    private String lexeme;
    private Source_Position position;
    private int entry;
    
    public Token(Token_Kind kind, String lexeme, Source_Position position, int entry)
    {
        this.kind = kind;
        this.lexeme = lexeme;
        this.position = position;
        this.entry = entry;
    }
    
    public Token(Token_Kind kind, String lexeme, Source_Position position)
    {
        this.kind = kind;
        this.lexeme = lexeme;
        this.position = position;
        this.entry = -1;
    }
    
    public Token_Kind getKind()
    {
        return kind;
    }
    
    public String getLexeme()
    {
        return lexeme;
    }
    
    public Source_Position getPosition()
    {
        return position;
    }
    
    public int getEntry()
    {
        return entry;
    }
}
