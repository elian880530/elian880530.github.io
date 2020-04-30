package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;
import java.util.List;

public class Program extends InstructionBlock {

    public Program(List<SingleInstruction> instructions, SourcePosition position) {
        super(instructions, position);
    }

    @Override
    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitProgram(this, arg);
    }
}

