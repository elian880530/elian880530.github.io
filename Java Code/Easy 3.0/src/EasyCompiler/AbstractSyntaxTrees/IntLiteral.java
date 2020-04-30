package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class IntLiteral extends Symbol
{
    private String lexeme;

    public IntLiteral(String lexeme, int entry, SourcePosition position)        
    {
        super(entry, position);
        this.lexeme = lexeme;
    }

    public int Value()
    {
        
        return Integer.parseInt(lexeme);        
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitIntLireral(this, arg);
    }
}

