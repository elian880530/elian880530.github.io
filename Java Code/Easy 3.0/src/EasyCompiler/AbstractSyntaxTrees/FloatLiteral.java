package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class FloatLiteral extends Symbol
{
    private String lexeme;

    public FloatLiteral(String lexeme, int entry, SourcePosition position)
        
    {
        super(entry,position);
        this.lexeme = lexeme;
    }

    public float Value()
    {                
        return Float.parseFloat(lexeme);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitFloatLiteral(this, arg);
    }
}

