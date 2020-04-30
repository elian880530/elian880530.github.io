package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;
import java.util.LinkedList;
import java.util.List;


public class Program extends InstructionBlock
{
    public Program(List<SingleInstruction> instructions, SourcePosition position)        
    {
        super(instructions ,position);
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitProgram(this, arg);
    }

}

