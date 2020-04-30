package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class Not extends UnaryOperator {

    public Not(Expression expression, SourcePosition position) {
        super(expression, position);
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitNot(this, arg);
    }
}
