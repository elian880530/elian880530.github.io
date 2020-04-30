package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class IdentifierReference extends Identifier
{
    public IdentifierReference(String lexeme, int entry, SourcePosition position)        
    {
        super(lexeme, entry, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitIdentifierReference(this, arg);
    }
}

