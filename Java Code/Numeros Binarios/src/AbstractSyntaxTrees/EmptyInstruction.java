package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class EmptyInstruction extends SingleInstruction {

    public EmptyInstruction(SourcePosition position) {
        super(position);
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitEmptyInstruction(this, arg);
    }
}

