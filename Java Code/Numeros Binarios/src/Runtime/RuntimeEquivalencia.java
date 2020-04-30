package Runtime;

/**
 *
 * @author EGH
 */
public class RuntimeEquivalencia extends RuntimeOperator {

    public RuntimeEquivalencia() {
        super();
    }
    //(! A | B)&(! A | B)

    @Override
    public void Execute(Context context) {
        String arg2 = context.Stack().pop().toString();
        ;
        String arg1 = context.Stack().pop().toString();
        ;

        String noti = convert_Not(arg1);
        String ori = convert_or(noti, arg2);

        String notd = convert_Not(arg2);
        String ord = convert_or(notd, arg1);

        context.Stack().push(convert_and(ori, ord));
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
