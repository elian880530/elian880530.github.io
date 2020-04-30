package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public abstract class BinaryOperator extends Operator
{
    private Expression arg1;
    private Expression arg2;

    public Expression Arg1()
    {
        return arg1; 
    }

    public Expression Arg2()
    {
        return arg2;
    }    

    public BinaryOperator(Expression arg1, Expression arg2, SourcePosition position)
        
    {
        super(position);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
