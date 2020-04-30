package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.*;

public class Print extends SingleInstruction {

    private Expression expression;

    public Expression Expression() {
        return expression;
    }
    private EasyTypes operandsType;

    public EasyTypes OperandsType() {
        return operandsType;
    }

    public void setOperandsType(EasyTypes value) {
        operandsType = value;
    }

    public Print(Expression expression, SourcePosition position) {
        super(position);
        this.expression = expression;
        this.operandsType = EasyTypes.Undefined;
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitPrint(this, arg);
    }
}

