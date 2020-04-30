package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class IdentifierReference extends Identifier {

    public IdentifierReference(String lexeme, int entry, SourcePosition position) {
        super(lexeme, entry, position);
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitIdentifierReference(this, arg);
    }
}

