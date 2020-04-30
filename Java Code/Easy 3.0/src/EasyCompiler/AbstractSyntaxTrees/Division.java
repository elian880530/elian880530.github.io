package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class Division extends BinaryOperator
{
    public Division(Expression arg1, Expression arg2, SourcePosition position)        
    {
        super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitDivision(this, arg);
    }
}

