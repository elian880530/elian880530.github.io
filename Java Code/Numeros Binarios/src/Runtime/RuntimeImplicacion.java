package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeImplicacion extends RuntimeOperator {

    public RuntimeImplicacion() {
        super();
    }
    //! A | B

    @Override
    public void Execute(Context context) {
        String arg2 = context.Stack().pop().toString();
        String arg1 = context.Stack().pop().toString();

        String a = convert_Not(arg1);

        context.Stack().push(convert_or(a, arg2));
        context.setCurrent(context.Current() + 1);
    }

    private String convert_Not(String a) {
        String result = "";

        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) == '0') {
                result += "1";
            }
            if (a.charAt(i) == '1') {
                result += "0";
            }
        }
        return result;
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

