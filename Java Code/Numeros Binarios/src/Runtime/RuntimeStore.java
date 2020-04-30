package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeStore extends RuntimeOperator {

    public RuntimeStore() {
        super();
    }

    @Override
    public void Execute(Context context) {
        Object value = context.Stack().pop();
        Integer address = Integer.parseInt(context.Stack().pop().toString());
        context.Memory().set(address, value);
        context.setCurrent(context.Current() + 1);
    }
}

