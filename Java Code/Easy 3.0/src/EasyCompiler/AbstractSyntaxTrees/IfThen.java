package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class IfThen extends SingleInstruction
{
    private Expression expression;
    private Instruction then;

    public Expression Expression()
    {
        return expression;        
    }

    public Instruction Then()
    {
        return then;
    }

    public IfThen(Expression expression, Instruction then , SourcePosition position)        
    {
        super(position);
        this.then = then;
        this.expression = expression;
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitIfThen(this, arg);
    }
}

