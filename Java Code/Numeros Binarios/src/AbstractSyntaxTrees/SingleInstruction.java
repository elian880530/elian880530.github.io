package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public abstract class SingleInstruction extends Instruction {

    public SingleInstruction(SourcePosition position) {
        super(position);
    }
}
