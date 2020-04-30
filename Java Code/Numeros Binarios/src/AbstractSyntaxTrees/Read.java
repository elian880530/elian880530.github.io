package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.EasyTypes;
import Compilador.SourcePosition;

public class Read extends SingleInstruction {

    private Identifier identifier;

    public Identifier Identifier() {
        return identifier;
    }
    private EasyTypes operandsType;

    public EasyTypes OperandsType() {
        return operandsType;
    }

    public void setOperandsType(EasyTypes value) {
        operandsType = value;
    }

    public Read(Identifier identifier, SourcePosition position) {
        super(position);
        this.identifier = identifier;
        this.operandsType = EasyTypes.Undefined;
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitRead(this, arg);
    }
}

