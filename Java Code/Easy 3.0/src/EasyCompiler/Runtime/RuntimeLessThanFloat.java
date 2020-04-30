package EasyCompiler.Runtime;

public class RuntimeLessThanFloat extends RuntimeOperator
{
    public RuntimeLessThanFloat()        
    {
        super();
    }

    public void Execute(Context context)
    {
        Float arg2 = (Float)context.Stack().pop();
        Float arg1 = (Float)context.Stack().pop();
        context.Stack().push(arg1 < arg2);
        context.setCurrent(context.Current() + 1);
    }
}

