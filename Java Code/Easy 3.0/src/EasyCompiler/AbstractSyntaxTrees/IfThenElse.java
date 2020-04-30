package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public class IfThenElse extends IfThen
{
    private Instruction _else;

    public Instruction Else()
    {
        return _else;
    }

    public IfThenElse(Expression expression, Instruction then, Instruction _else, SourcePosition position)        
    {
        super(expression, then, position);
        this._else = _else;
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitIfThenElse(this, arg);
    }
}

