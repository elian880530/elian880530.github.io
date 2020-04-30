package AbstractSyntaxTrees;

/**
 *
 * @author EGH
 */

import Compilador.SourcePosition;

public class binaryLiteral extends Symbol {

    private String lexeme;

    public binaryLiteral(String lexeme, int entry, SourcePosition position) {
        super(entry, position);
        this.lexeme = lexeme;
    }

    public int Value() {

        return Integer.parseInt(lexeme);
    }

    public Object Visit(Visitor visitor, Object arg) {
        return visitor.VisitIntLireral(this, arg);
    }
}

