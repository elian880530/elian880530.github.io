package SimbolosdeTabla;

import AnalizadorLexico.*;
import Compilador.EasyTypes;

/**
 *
 * @author EGH
 */
public class SymbolInfo {

    private String lexeme;
    private TokenKind kind;
    private boolean declared;
    private EasyTypes type;
    private int address;

    public SymbolInfo(String lexeme, TokenKind kind) {
        this.lexeme = lexeme;
        this.kind = kind;
        this.declared = false;
        this.type = EasyTypes.Undefined;
        this.address = -1;
    }

    public String getLexeme() {
        return lexeme;
    }

    public TokenKind getKind() {
        return kind;
    }

    public void setKind(TokenKind kind) {
        this.kind = kind;
    }

    public boolean getDeclared() {
        return declared;
    }

    public void setDeclared(boolean declared) {
        this.declared = declared;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public boolean equals(Object obj) {
        String objLexeme = ((SymbolInfo) obj).getLexeme();
        return objLexeme.equals(lexeme);
    }

    public int hashCode() {
        return lexeme.hashCode();
    }

    public void setType(EasyTypes type) {
        this.type = type;
    }

    public EasyTypes getType() {
        return type;
    }
}
