package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeAnd extends RuntimeOperator {

    public RuntimeAnd() {
        super();
    }

    @Override
    public void Execute(Context context) {


        context.Stack().push(convert_and(context.Stack().pop().toString(), context.Stack().pop().toString()));
        context.setCurrent(context.Current() + 1);
    }

    private String convert_and(String a, String b) {
        String result = "";
        if (a.length() < b.length()) {
            while (a.length() < b.length()) {
                a = "0" + a;
            }
        }
        if (b.length() < a.length()) {
            while (b.length() < a.length()) {
                b = "0" + b;
            }
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '1' && b.charAt(i) == '1') {
                result += "1";
            } else {
                result += "0";
            }
        }
        return result;

    }
}

