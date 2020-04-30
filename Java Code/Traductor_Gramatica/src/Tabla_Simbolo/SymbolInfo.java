/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabla_Simbolo;

import Analizador_Lexico.Token_Kind;
import Compilador.Easy_Types;



/**
 *
 * @author trujillo
 */
public class SymbolInfo {
    
    private String lexeme;
    private Token_Kind kind;
    private boolean declared;
    private Easy_Types type;
    private int address;
    
    public SymbolInfo(String lexeme, Token_Kind kind)
    {
        this.lexeme = lexeme;
        this.kind = kind;
        this.declared = false;
        this.type = Easy_Types.Undefined;
        this.address = -1;
    }
    
    public String getLexeme() 
    {
        return lexeme;
    }
    
    public Token_Kind getKind()
    {
        return kind;
    }
    public void setKind(Token_Kind kind)
    {
        this.kind = kind;
    }
    
    public boolean getDeclared()
    {
        return declared;
    }
    public void setDeclared(boolean declared)
    {
        this.declared = declared;
    }
    
    public int getAddress()
    {
        return address;
    }
    public void setAddress(int address)
    {
        this.address = address;
    }
    
    public boolean equals(Object obj)
    {
        String objLexeme = ((SymbolInfo)obj).getLexeme();
        return objLexeme.equals(lexeme);
    }
    
    public int hashCode()
    {
        return lexeme.hashCode();
    }
    
    public void setType(Easy_Types type)
    {
        this.type = type;
    }
    
    public Easy_Types getType()
    {
        return type;
    }
}
