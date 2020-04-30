/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EasyCompiler.LexicalAnalyzer;

import EasyCompiler.SourcePosition;
/**
 *
 * @author DDC Programación, UCI
 */
public class Token {

    private TokenKind kind;
    private String lexeme;
    private SourcePosition position;
    private int entry;
    
    public Token(TokenKind kind, String lexeme, SourcePosition position, int entry) 
    {
        this.kind = kind;
        this.lexeme = lexeme;
        this.position = position;
        this.entry = entry;
    }
    
    public Token(TokenKind kind, String lexeme, SourcePosition position) 
    {
        this.kind = kind;
        this.lexeme = lexeme;
        this.position = position;
        this.entry = -1;
    }
    
    public TokenKind getKind()
    {
        return kind;
    }
    
    public String getLexeme()
    {
        return lexeme;
    }
    
    public SourcePosition getPosition()
    {
        return position;
    }
    
    public int getEntry()
    {
        return entry;
    }
}
