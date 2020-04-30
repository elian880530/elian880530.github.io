package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class IdentifierValue extends Identifier
{
    public IdentifierValue(String lexeme, int entry, SourcePosition position)        
    {
        super(lexeme, entry, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitIdentifierValue(this, arg);
    }
}

