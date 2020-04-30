package EasyCompiler.Runtime;

public class RuntimeSingMinusFloat extends RuntimeOperator
{
    public RuntimeSingMinusFloat()
    {
        super();
    }

    public void Execute(Context context)
    {
        Float arg = (Float)context.Stack().pop();
        context.Stack().push(-arg);
        context.setCurrent(context.Current() + 1);
    }
}

