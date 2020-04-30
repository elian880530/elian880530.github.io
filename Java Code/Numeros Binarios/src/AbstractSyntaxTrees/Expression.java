package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public abstract class Expression extends AST {

    public Expression(SourcePosition position) {
        super(position);
    }
}

