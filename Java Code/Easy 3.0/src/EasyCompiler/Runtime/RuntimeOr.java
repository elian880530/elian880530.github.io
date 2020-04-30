package EasyCompiler.Runtime;

public class RuntimeOr extends RuntimeOperator
{
    public RuntimeOr()        
    {
        super();
    }

    public void Execute(Context context)
    {
        Boolean arg2 = (Boolean)context.Stack().pop();
        Boolean arg1 = (Boolean)context.Stack().pop();
        context.Stack().push(arg1 || arg2);
        context.setCurrent(context.Current() + 1);
    }
}