package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class While extends SingleInstruction
{
    private Expression expression;
    private Instruction _do;

    public Expression Expression()
    {
        return expression;     
    }

    public Instruction Do()
    {
        return _do;        
    }

    public While(Expression expression, Instruction _do, SourcePosition position)        
    {
        super(position);
        this._do = _do;
        this.expression = expression;
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitWhile(this, arg);
    }
}

