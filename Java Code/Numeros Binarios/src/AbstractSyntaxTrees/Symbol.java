package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */
import Compilador.SourcePosition;

public abstract class Symbol extends Expression {

    private int entry;

    public int Entry() {
        return entry;
    }

    public Symbol(int entry, SourcePosition position) {
        super(position);
        this.entry = entry;
    }
}

