package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class Or extends BinaryOperator
{
    public Or(Expression arg1, Expression arg2, SourcePosition position)        
    {
        super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitOr(this, arg);
    }
}

