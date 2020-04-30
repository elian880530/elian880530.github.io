package Runtime;

/**
 *
 * @author EGH
 */
import Compilador.InOut;

public class RuntimeHalt extends RuntimeOperator {

    public RuntimeHalt() {
        super();
    }

    @Override
    public void Execute(Context context) {
        context.setHalt(true);
        InOut.Write("Program has halted normally.", null);
    }
}

