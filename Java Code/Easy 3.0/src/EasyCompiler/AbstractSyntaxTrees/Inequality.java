package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class Inequality extends BinaryOperator
{
    public Inequality(Expression arg1, Expression arg2, SourcePosition position)        
    {
        super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitInequality(this, arg);
    }
}

