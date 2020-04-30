package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class Multiplication extends BinaryOperator
{
    public Multiplication(Expression arg1, Expression arg2, SourcePosition position)
    {
       super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitMultiplication(this, arg);
    }
}

