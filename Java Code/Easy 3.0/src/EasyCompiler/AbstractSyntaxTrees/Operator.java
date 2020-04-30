package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public abstract class Operator extends Expression
{
    private EasyTypes operandsType;

    public EasyTypes OperandsType()
    {
        return operandsType; 
    }
    public void setOperandsType(EasyTypes value)
    {
        operandsType = value; 
    }

    public Operator(SourcePosition position)        
    {
        super(position);
        this.operandsType =EasyTypes.Undefined;
    }
}

