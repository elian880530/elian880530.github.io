package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimePush extends RuntimeOperator {

    public RuntimePush() {
        super();
    }

    public void Execute(Context context) {
        Object arg = ((RuntimeValue) (context.Code().get(context.Current() + 1))).Value();
        context.Stack().push(arg);
        context.setCurrent(context.Current() + 2);
    }
}

