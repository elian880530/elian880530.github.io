package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public abstract class Identifier extends Symbol
{
    private String lexeme;

    public String Lexeme()
    {
        return lexeme; 
    }

    public Identifier(String lexeme, int entry, SourcePosition position)        
    {
        super(entry, position);
        this.lexeme = lexeme;
    }
}

