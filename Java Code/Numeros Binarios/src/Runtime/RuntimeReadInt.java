package Runtime;

/**
 *
 * @author EGH
 */
import Compilador.InOut;

public class RuntimeReadInt extends RuntimeOperator {

    public RuntimeReadInt() {
        super();
    }

    @Override
    public void Execute(Context context) {
        Integer address = (Integer) context.Stack().pop();
        String svalue = InOut.Read();
        Integer value;
        try {
            value = Integer.parseInt(svalue);
            context.Memory().set(address, value);
            context.setCurrent(context.Current() + 1);
        } catch (NumberFormatException e) {
            String[] arg = new String[1];
            arg[0] = svalue;
            InOut.Write("Cannot convert '{0}' to 'float'", arg);
            context.setHalt(true);
        }
    }
}
