package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public abstract class AST
{
    private SourcePosition position;

    public SourcePosition Position()
    {                
        return position; 
    }

    public AST(SourcePosition position)
    {
        this.position = position;
    }

    public abstract Object Visit(Visitor visitor, Object arg);
}
