package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class SingPlus extends UnaryOperator
{
    public SingPlus(Expression expression, SourcePosition position)        
    {
        super(expression, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitSingPlus(this, arg);
    }
}

