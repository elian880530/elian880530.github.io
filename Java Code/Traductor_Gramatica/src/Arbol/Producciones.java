package Arbol;

import Compilador.Source_Position;
import java.util.List;

/**
 *
 * @author EGH
 */
public abstract class Producciones extends AST {

    List<Prod> prod;

    public Producciones(Source_Position position, List<Prod> prod) {
        super(position);
        this.prod = prod;
    }
}
