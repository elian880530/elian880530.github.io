package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeNot extends RuntimeOperator {

    public RuntimeNot() {
        super();
    }

    @Override
    public void Execute(Context context) {
        context.Stack().push(convert_Not(context.Stack().pop().toString()));
        context.setCurrent(context.Current() + 1);
    }

    private String convert_Not(String a) {
        String ok = "";

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '0') {
                ok += "1";
            }
            if (a.charAt(i) == '1') {
                ok += "0";
            }
        }
        return ok;
    }
}

