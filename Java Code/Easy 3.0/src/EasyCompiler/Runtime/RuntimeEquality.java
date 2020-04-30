package EasyCompiler.Runtime;

public class RuntimeEquality extends RuntimeOperator
{
    public RuntimeEquality()        
    {
        super();
    }

    public void Execute(Context context)
    {
        Object arg2 = context.Stack().pop();
        Object arg1 = context.Stack().pop();
        context.Stack().push(arg1.equals(arg2));
        context.setCurrent(context.Current() + 1);
    }
}

