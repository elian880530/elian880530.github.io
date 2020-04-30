package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class SingMinus extends UnaryOperator
{
    public SingMinus(Expression expression, SourcePosition position)        
    {
        super(expression, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitSingMinus(this, arg);
    }
}

