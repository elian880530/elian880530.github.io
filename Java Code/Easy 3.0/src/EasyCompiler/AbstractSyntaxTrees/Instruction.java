package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;

public abstract class Instruction extends AST
{
    public Instruction(SourcePosition position)        
    {
        super(position);
    }
}

