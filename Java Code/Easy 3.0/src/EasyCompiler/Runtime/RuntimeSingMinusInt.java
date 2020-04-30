package EasyCompiler.Runtime;

public class RuntimeSingMinusInt extends RuntimeOperator
{
    public RuntimeSingMinusInt()
    {
        super();
    }

    public void Execute(Context context)
    {
        Integer arg = (Integer)context.Stack().pop();
        context.Stack().push(-arg);
        context.setCurrent(context.Current() + 1);
    }
}
