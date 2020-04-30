package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class BoolLiteral extends Symbol
{
    private Boolean value;

    public BoolLiteral(String lexeme, int entry, SourcePosition position)        
    {
        super(entry, position);
        this.value = Boolean.parseBoolean(lexeme);
    }

    public Boolean Value() 
    {
        return value;  
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitBoolLiteral(this, arg);
    }
}

