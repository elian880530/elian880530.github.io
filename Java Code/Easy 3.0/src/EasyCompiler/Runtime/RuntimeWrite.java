package EasyCompiler.Runtime;

import EasyCompiler.InOut;

public class RuntimeWrite extends RuntimeOperator
{
    public RuntimeWrite()
    {
        super();
    }

    public void Execute(Context context)
    {
        Object value = context.Stack().pop();
        InOut.Write(value.toString(), null);
        context.setCurrent(context.Current() + 1);
    }
}

