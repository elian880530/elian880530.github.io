package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public abstract class Instruction extends AST {

    public Instruction(SourcePosition position) {
        super(position);
    }
}

