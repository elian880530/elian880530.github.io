package EasyCompiler.AbstractSyntaxTrees;

import EasyCompiler.*;
import java.util.LinkedList;
import java.util.List;

public class InstructionBlock extends Instruction
{
    private List<SingleInstruction> instructions;

    public InstructionBlock(List<SingleInstruction> instructions, SourcePosition position)
        
    {
        super(position);
        this.instructions = new LinkedList<SingleInstruction>(instructions);
    }

    public List<SingleInstruction> Instructions()
    {
        return instructions;     
    }

    public Object Visit(Visitor visitor, Object arg)
    {
        return visitor.VisitInstructionBlock(this, arg);
    }
}

