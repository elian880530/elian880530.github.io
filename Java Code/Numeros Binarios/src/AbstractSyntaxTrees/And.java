package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class And extends BinaryOperator {

    public And(Expression arg1, Expression arg2, SourcePosition position) {
        super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitAnd(this, arg);
    }
}
