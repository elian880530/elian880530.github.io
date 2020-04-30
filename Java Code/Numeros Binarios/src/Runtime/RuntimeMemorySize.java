package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeMemorySize extends RuntimeOperator {

    public RuntimeMemorySize() {
        super();
    }

    public void Execute(Context context) {
        String size = ((IntValue) (context.Code().get(context.Current() + 1))).Value();
        while (context.Memory().size() < Integer.parseInt(size)) {
            context.Memory().add(null);
        }
        context.setCurrent(context.Current() + 2);
    }
}

