package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class Modulus extends BinaryOperator
{
    public Modulus(Expression arg1, Expression arg2, SourcePosition position)        
    {
        super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitModulus(this, arg);
    }
}

