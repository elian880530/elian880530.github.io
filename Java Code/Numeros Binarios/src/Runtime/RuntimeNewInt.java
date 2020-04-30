package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeNewInt extends RuntimeOperator {

    public RuntimeNewInt() {
        super();
    }

    public void Execute(Context context) {
        String address = ((Address) (context.Code().get(context.Current() + 1))).Value();
        context.Memory().set(Integer.parseInt(address), 0);
        context.setCurrent(context.Current() + 2);
    }
}
