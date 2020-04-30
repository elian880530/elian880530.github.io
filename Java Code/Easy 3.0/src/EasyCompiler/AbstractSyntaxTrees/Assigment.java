package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class Assigment extends SingleInstruction
{
    private Expression expression;
    private Identifier identifier;

    public Expression Expression()
    {
        return expression;        
    }

    public Identifier Identifier()
    {
            return identifier;
    }

    public Assigment(Identifier identifier, Expression expression, SourcePosition position)        
    {
        super(position);
        this.identifier = identifier;
        this.expression = expression;
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitAssigment(this, arg);
    }
}

