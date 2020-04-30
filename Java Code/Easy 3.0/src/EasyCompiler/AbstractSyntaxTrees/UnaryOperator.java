package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public abstract class UnaryOperator extends Operator
{
    private Expression expression;

    public Expression Expression()
    {
        return expression;         
    }

    public UnaryOperator(Expression expression, SourcePosition position)        
    {
        super(position);
        this.expression = expression;
    }
}

