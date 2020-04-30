package Runtime;

/**
 *
 * @author EGH
 */
import Compilador.InOut;

public class RuntimePrint extends RuntimeOperator {

    public RuntimePrint() {
        super();
    }

    @Override
    public void Execute(Context context) {
        Object value = context.Stack().pop();
        InOut.Write(value.toString(), null);
        context.setCurrent(context.Current() + 1);
    }
}

