package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class VarDeclaration extends SingleInstruction
{
    private IdentifierDeclaration identifier;

    public IdentifierDeclaration Identifier()
    {
        return identifier;      
    }

    private VarType type;

    public VarType Type()
    {
        return type; 
    }

    public VarDeclaration(IdentifierDeclaration identifier, VarType type ,SourcePosition position)        
    {
        super(position);
        this.identifier = identifier;
        this.type = type;
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitVarDeclaration(this, arg);
    }
}

