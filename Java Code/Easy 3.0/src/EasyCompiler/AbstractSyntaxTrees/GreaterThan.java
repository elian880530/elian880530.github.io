package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class GreaterThan extends BinaryOperator
{
    public GreaterThan(Expression arg1, Expression arg2, SourcePosition position)        
    {
        super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitGreaterThan(this, arg);
    }
}
