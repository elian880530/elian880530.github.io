package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeLoad extends RuntimeOperator {

    public RuntimeLoad() {
        super();
    }

    @Override
    public void Execute(Context context) {
        String address = ((Address) (context.Code().get(context.Current() + 1))).Value();
        context.Stack().push(context.Memory().get(Integer.parseInt(address)));
        context.setCurrent(context.Current() + 2);
    }
}

