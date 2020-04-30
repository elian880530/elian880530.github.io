package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public class Equivalencia extends BinaryOperator {

    public Equivalencia(Expression arg1, Expression arg2, SourcePosition position) {
        super(arg1, arg2, position);
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitEquivalencia(this, arg);
    }
}

