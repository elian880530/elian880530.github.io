package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class VarType extends AST
{
    private EasyTypes type;

    public VarType(EasyTypes type, SourcePosition position)        
    {
        super(position);
        this.type = type;
    }

    public EasyTypes EasyType()
    {
        return type;     
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitVarType(this, arg);
    }
}

