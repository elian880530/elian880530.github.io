package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeOr extends RuntimeOperator {

    public RuntimeOr() {
        super();
    }

    @Override
    public void Execute(Context context) {
        context.Stack().push(convert_or(context.Stack().pop().toString(), context.Stack().pop().toString()));
        context.setCurrent(context.Current() + 1);
    }

    private String convert_or(String a, String b) {
        String resutl = "";
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
            if (a.charAt(i) == b.charAt(i)) {
                resutl += a.charAt(i);
            } else {
                resutl += "1";
            }
        }
        return resutl;
    }
}
