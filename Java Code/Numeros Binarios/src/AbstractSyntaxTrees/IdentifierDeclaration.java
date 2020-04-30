package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class IdentifierDeclaration extends Identifier {

    public IdentifierDeclaration(String lexeme, int entry, SourcePosition position) {
        super(lexeme, entry, position);
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitIdentifierDeclaration(this, arg);
    }
}

