package Runtime;

/**
 *
 * @author EGH
 */
import Compilador.InOut;

import java.util.List;

public class Interpreter {

    private Context context;

    public Interpreter(List<RuntimeEntity> code) {
        context = new Context(code);
    }

    public void Execute() throws Exception {
        RuntimeEntity r = null;
        InOut.Write("********** Trabajo con Números Binarios **********", null);
        InOut.Write("Se está ejecutando el programa ...", null);
        while (!context.Halt()) {
            r = context.Code().get(context.Current());
            r.Execute(context);
        }
    }
}
